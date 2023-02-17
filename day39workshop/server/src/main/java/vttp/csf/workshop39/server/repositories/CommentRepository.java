package vttp.csf.workshop39.server.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;


@Repository
public class CommentRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveComment(JsonObject comment){
        Document toInsert = Document.parse(comment.toString());
        Document newDoc = mongoTemplate.insert( toInsert, "marvelComments" );
    }

    public Optional<List<Document>> getComment(int id){
        List<Document> results = 
            mongoTemplate.find(Query.query(Criteria.where("id").is(id)), Document.class, "marvelComments");
        
        if(results.size()==0){  return Optional.empty(); }

        return Optional.of(results);
    }

}
