package vttp.csf.workshop39.server.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.csf.workshop39.server.models.Comments;
import vttp.csf.workshop39.server.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepo;

    public void saveComment(JsonObject comment){
        System.out.println("Check JsonObject: " + comment);
        JsonObject commentObj = Json.createObjectBuilder()
                                .add("id", comment.getInt("id"))
                                .add("comments", comment.getString("comments"))
                                .add("timeStamp", System.currentTimeMillis())
                                .build();

        commentRepo.saveComment(commentObj);
    }

    public List<JsonObject> getComments(int id){
        List<JsonObject> commentJson = new LinkedList<>();
        if(commentRepo.getComment(id).isEmpty()){   return commentJson; }
        
        commentRepo.getComment(id).get()
                                .stream()
                                .forEach(commentDoc -> commentJson.add( Comments.docToJson(commentDoc) ) );

        return commentJson;
    }
}
