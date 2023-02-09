package vttp.csf.day38server.repositories;


import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.csf.day38server.models.Contact;


@Repository
public class ContactRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    public void saveContacts(JsonArray contacts){
        List<Document> contactDocs = new LinkedList<>();
        contacts.stream().forEach(c -> {
            JsonObject cJSON = (JsonObject)c;
            contactDocs.add(Document.parse(cJSON.toString()));
        } );

        mongoTemplate.insert( contactDocs, "contacts" );

    }


    public JsonArray fetchContacts(){
        List<Document> results = mongoTemplate.find(new Query(), Document.class, "contacts");
        
        JsonArrayBuilder contactsArray = Json.createArrayBuilder();
        results.stream().forEach(c -> contactsArray.add( Contact.docToJson(c) ) );

        return contactsArray.build();
    }
}
