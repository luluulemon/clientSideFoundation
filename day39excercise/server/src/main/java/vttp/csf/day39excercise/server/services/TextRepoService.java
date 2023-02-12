package vttp.csf.day39excercise.server.services;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.csf.day39excercise.server.models.TextPost;
import vttp.csf.day39excercise.server.repositories.TextRepository;

@Service
public class TextRepoService {
    
    @Autowired
    private TextRepository txtRepo;

    public JsonArray getTexts(){
        List<Document> results = txtRepo.getTexts();
        
        JsonArrayBuilder posts = Json.createArrayBuilder();
        results.stream().forEach( 
            v -> {  posts.add( TextPost.docToJson(v)); });
        
        return posts.build();
    }

    public String saveText(JsonObject textJson){

        // create Object ID for this
        String objID = UUID.randomUUID().toString().substring(0, 8);

        JsonObject text = Json.createObjectBuilder().add("title", textJson.getString("title"))
                        .add("text", textJson.getString("text"))
                        .add("id", objID)
                        .add("likes", 0)
                        .add("dislikes", 0)
                        .build();
        
        return txtRepo.saveText(text);        
    }

    public void addLike(String id){ txtRepo.addLike(id);    }
    public void addDisLike(String id){ txtRepo.addDislike(id);    }
}
