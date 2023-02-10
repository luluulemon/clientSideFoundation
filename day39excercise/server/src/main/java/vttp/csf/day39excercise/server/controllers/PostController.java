package vttp.csf.day39excercise.server.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.day39excercise.server.services.S3Service;
import vttp.csf.day39excercise.server.services.TextRepoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@Controller
public class PostController {
    
    @Autowired
    private S3Service s3svc;

    @Autowired
    private TextRepoService txtRepoSvc;

    @GetMapping(path="/getPosts")
    @ResponseBody
    @CrossOrigin(origins="*")
    public ResponseEntity<String> getPosts(){
        JsonArray allPosts = txtRepoSvc.getTexts();
        return ResponseEntity.ok(allPosts.toString());
    }


    @PostMapping(path="/newPostText", consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins="*")
    public ResponseEntity<String> savePostText(@RequestBody String mainText){

        JsonReader reader = Json.createReader(new StringReader(mainText));
        JsonObject textJson = reader.readObject(); 
        
        String id = txtRepoSvc.saveText(textJson);

        return ResponseEntity.ok(
            Json.createObjectBuilder().add("id", id).build().toString()
        );
    }



    @PostMapping(path="/newPost", consumes=MediaType.MULTIPART_FORM_DATA_VALUE )
    @ResponseBody
    @CrossOrigin(origins="*")
    public ResponseEntity<String> savePost(@RequestPart MultipartFile file,
                @RequestPart String id) throws IOException{

        
        String fileName = file.getName();
        String originalName = file.getOriginalFilename();
        String mediaType = file.getContentType();

        System.out.printf("check file upload: %s, %s, %s", fileName, originalName, mediaType);

        try{  
            String key = s3svc.upload(file, id); 
            return ResponseEntity.ok(Json.createObjectBuilder().add("key", key).build().toString());
        }
        catch(IOException e){   e.printStackTrace();    }
        catch(Exception e){ e.printStackTrace();    }


        return ResponseEntity.ok("test picture");
    }
}
