package vttp.csf.day36server.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.day36server.demo.models.Comment;

@Repository
public class CommentRepository {
    
    @Autowired
    private MongoTemplate template;

    public Comment[] getCommentsById(){
        return null;
    }
}
