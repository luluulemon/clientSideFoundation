package vttp.csf.day38server.controllers;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;

import jakarta.json.JsonReader;
import vttp.csf.day38server.services.SaveContactService;


@Controller
public class saveContactController {
    
    @Autowired
    private SaveContactService saveSvc;


    @PostMapping(path = "/syncContacts", consumes=MediaType.APPLICATION_JSON_VALUE)
    // @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<String> sync(@RequestBody String payload ){

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray results = reader.readArray();

        // List<Contact> newContacts = new LinkedList<>();
        // results.stream().forEach(c -> 
        //             {   JsonObject newContact = (JsonObject)c;
        //                 newContacts.add( Contact.create(newContact) );
        //              });
        
        // save to Mongo
        saveSvc.saveContacts(results);
        
        return ResponseEntity.ok(results.toString());
    }

    @GetMapping("/fetchAll")
    @ResponseBody
    public ResponseEntity<String> getContacts(){
        
        JsonArray contacts = saveSvc.fetchContacts();

        return ResponseEntity.ok().body(contacts.toString());
    }
}
