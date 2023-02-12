package vttp.csf.day39excercise.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Repository
public class TextRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Document> getTexts(){
        List<Document> allPosts = mongoTemplate.find
            (new Query(), Document.class, "postText");

        return allPosts;
    }


    public String saveText(JsonObject textJson){
        Document textToInsert = Document.parse(textJson.toString());
        mongoTemplate.insert(textToInsert, "postText");

        String id = textJson.getString("id");
        logSave(id);

        return id;
    }

    public void logSave(String id){
        List<Document> results = mongoTemplate.find(
                Query.query(Criteria.where("id").is("log")), Document.class, "postTextLog");

        if(results.size()>0)
        {   List<String> idList = results.get(0).getList("IDs", String.class);    
            idList.add(0, id);

            JsonArrayBuilder idBuilder = Json.createArrayBuilder();
            idList.stream().forEach(i -> idBuilder.add(i));
            JsonObjectBuilder IDJson = Json.createObjectBuilder().add("id", "log")
                                    .add("IDs", idBuilder.build());

            Document LogToInsert = Document.parse(IDJson.build().toString());
            mongoTemplate.insert(LogToInsert, "postTextLog");
        }
        else{
            JsonObjectBuilder idBuilder = Json.createObjectBuilder()
                                            .add("id", "log")
                                            .add("IDs", Json.createArrayBuilder().add(id).build());

            Document LogToInsert = Document.parse(idBuilder.build().toString());
            mongoTemplate.insert(LogToInsert, "postTextLog");
        }
    }


    public void addLike(String id){
        Query query = Query.query(Criteria.where("id" ).is(id ));
        Update updateOps = new Update().inc("likes", 1);

        mongoTemplate.updateMulti(query, updateOps, Document.class, "postText");
    }

    public void addDislike(String id){
        Query query = Query.query(Criteria.where("id" ).is(id ));
        Update updateOps = new Update().inc("dislikes", 1);
        mongoTemplate.updateMulti(query, updateOps, Document.class, "postText");
    }
}
