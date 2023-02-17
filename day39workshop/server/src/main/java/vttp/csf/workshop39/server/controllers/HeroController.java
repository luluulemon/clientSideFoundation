package vttp.csf.workshop39.server.controllers;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.workshop39.server.services.CharacterService;
import vttp.csf.workshop39.server.services.CommentService;

@Controller
public class HeroController {
    
    @Autowired
    private CharacterService charSvc;

    @Autowired
    private CommentService commentSvc;

    
    @GetMapping("/api/characters/{character}")
    @ResponseBody
    public ResponseEntity<String> getCharacters(@PathVariable String character){
        
        System.out.println(">>>>>> CHeck Character: " + character );

        String heroList = charSvc.getCharacters(character);

        return ResponseEntity.ok(heroList);
    }

    @GetMapping(path = "/api/character/{id}", produces="application/json")
    @ResponseBody
    public ResponseEntity<String> getHero(@PathVariable int id){

        return ResponseEntity.ok(charSvc.getCharacter(id));
    }

    @PostMapping(path = "/api/character/{id}")
    @ResponseBody
    public ResponseEntity<String> saveComment(@RequestBody String value, @PathVariable int id){
        
        JsonReader reader = Json.createReader(new StringReader(value));
        JsonObject commentsJson = reader.readObject(); 
        commentSvc.saveComment(commentsJson);

        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/api/character/{id}/comments")
    @ResponseBody
    public ResponseEntity<String> getComments(@PathVariable int id){
        List<JsonObject> comments = commentSvc.getComments(id);
        return ResponseEntity.ok(comments.toString());
    }
}
