package vttp.csf.workshop39.server.controllers;

import java.security.MessageDigest;
import java.util.HexFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.csf.workshop39.server.services.CharacterService;

@Controller
public class HeroController {
    
    @Autowired
    private CharacterService charSvc;
    @ResponseBody
    @GetMapping("/api/characters/{character}")
    public ResponseEntity<String> getCharacter(@PathVariable String character){
        
        System.out.println(">>>>>> CHeck Character: " + character );
        return charSvc.getCharacters(character);

    }
}
