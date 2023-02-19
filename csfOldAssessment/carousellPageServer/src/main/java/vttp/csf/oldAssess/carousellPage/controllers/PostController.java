package vttp.csf.oldAssess.carousellPage.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import vttp.csf.oldAssess.carousellPage.models.ItemPost;
import vttp.csf.oldAssess.carousellPage.repositories.PostRedisRepository;
import vttp.csf.oldAssess.carousellPage.services.S3Service;

@Controller
public class PostController {
    
    @Autowired
    private S3Service s3Svc;

    @Autowired
    private PostRedisRepository postRepo;

    @PostMapping(path="/api/posting", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> savePic(@RequestPart MultipartFile image,
        @RequestPart String name, @RequestPart String email, @RequestPart String title,
        @RequestPart String description, @RequestPart int phone){

        
        try{
            String id = s3Svc.uploadImage(image);
            ItemPost post = ItemPost.create(name, email, title, description, id, phone);
            postRepo.cachePost(post);
            return ResponseEntity.ok( Json.createObjectBuilder().add("id", id).build().toString());
        }
        catch(IOException IOe){ IOe.printStackTrace(); } // to handle exception  

        return ResponseEntity.ok(null);
    }
}
