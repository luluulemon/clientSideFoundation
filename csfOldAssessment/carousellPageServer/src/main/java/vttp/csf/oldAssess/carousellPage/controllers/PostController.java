package vttp.csf.oldAssess.carousellPage.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.csf.oldAssess.carousellPage.models.ItemPost;
import vttp.csf.oldAssess.carousellPage.repositories.PostRedisRepository;
import vttp.csf.oldAssess.carousellPage.repositories.PostSQLRepository;
import vttp.csf.oldAssess.carousellPage.services.S3Service;

@Controller
public class PostController {
    
    @Autowired
    private S3Service s3Svc;

    @Autowired
    private PostRedisRepository postRepo;

    @Autowired
    private PostSQLRepository SQLRepo;

    @PostMapping(path="/api/posting", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> savePic(@RequestPart MultipartFile image, @RequestPart String name,
            @RequestPart String title, @RequestPart String description, @RequestPart String phone,
            @RequestPart String email)
    {

        try{
            String id = s3Svc.uploadImage(image);
            // create ItemPost Obj and put in Redis Cache
            ItemPost post = ItemPost.create(name, email, title, description, id, phone);
            System.out.println("Check post Item returned: " + post.toString());
            postRepo.cachePost(post);
            return ResponseEntity.ok( post.toJson().toString());
        }
        catch(IOException IOe){ IOe.printStackTrace(); } // to handle exception  

        return ResponseEntity.ok(null);
    }

    @PutMapping(path="/api/posting/{postId}")
    public ResponseEntity<String> savePost(@PathVariable String postId){
        
        // check if it exists
        if( !postRepo.checkPost(postId))
        {   JsonObject errorJson = Json.createObjectBuilder()
                                    .add("message", "post Id %s not found".formatted(postId))
                                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson.toString());
        }

        // save item to SQL, delete Redis Entry, return message
        ItemPost item = postRepo.getPost(postId);
        if(SQLRepo.savePostSQL(item))
        {   postRepo.deletePost(postId);    

            return ResponseEntity.ok( Json.createObjectBuilder()
                                    .add("message", "Accepted %s".formatted(postId))
                                    .build().toString());
        }

        // return SQL error
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body( Json.createObjectBuilder()
                            .add("message", "Error inserting to SQL")
                            .build().toString());
    }

}
