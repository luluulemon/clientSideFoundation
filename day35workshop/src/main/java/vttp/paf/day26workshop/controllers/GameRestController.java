package vttp.paf.day26workshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp.paf.day26workshop.repositories.GameRepository;

@Controller
public class GameRestController {
    
    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/games")
    public ResponseEntity<String> getGames
        (@RequestParam(required = false) Integer limit, @RequestParam(required=false) Integer offset)
    {
        if(limit == null)
        {   limit = 25;   }
        if(offset == null)
        {   offset = 0; }

        JsonObject resp = gameRepo.getGame(limit, offset);
        
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resp.toString());
    }

    @GetMapping("games-ng")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> getGamesNg
    (@RequestParam(required = false) Integer limit, @RequestParam(required=false) Integer offset)
    {   
        // params are 0 if nothing is entered. Use default limit as 10
        if(limit == 0)
        {   limit = 10;   }


        JsonArray resp = gameRepo.getGame(limit, offset, "ng");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                                    .body(resp.toString());
    }


    @GetMapping("/games/ranking")
    public ResponseEntity<String> getGamesRanking
        (@RequestParam(required = false) Integer limit, @RequestParam(required=false) Integer offset)
    {
        if(limit == null)
        {   limit = 25;   }
        if(offset == null)
        {   offset = 0; }

        JsonObject resp = gameRepo.getRank(limit, offset);

        return ResponseEntity.ok().body(resp.toString());
    }


    @GetMapping("/game/{gid}")
    public ResponseEntity<String> getGameDetails(@PathVariable int gid){

        Optional<JsonObject> game = gameRepo.getGameDetails(gid);
        if(game.isEmpty() )
        {   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);       }

        return ResponseEntity.ok().body(game.get().toString());
    }
}
