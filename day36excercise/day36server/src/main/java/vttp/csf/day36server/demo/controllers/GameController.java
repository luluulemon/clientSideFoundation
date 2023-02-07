package vttp.csf.day36server.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp.csf.day36server.demo.models.Game;
import vttp.csf.day36server.demo.services.GameService;

@Controller
public class GameController {
    
    @Autowired
    private GameService gameSvc;

    @GetMapping(path="/api/games")
    @ResponseBody
    //@CrossOrigin(origins="*")
    public ResponseEntity<String> getGames(@RequestParam(defaultValue = "10") String limit,
                                @RequestParam(defaultValue = "0") String offset){
        List<Game> games = gameSvc.getGames(limit, offset);
        JsonArrayBuilder gamesJsonArray = Json.createArrayBuilder();
        games.stream().forEach(game -> gamesJsonArray.add(game.toJsonSummary()) );

        return ResponseEntity.ok().body(gamesJsonArray.build().toString());
    }

    @GetMapping(path="/api/game/{id}")
    @ResponseBody
    public ResponseEntity<String> getGameById(@PathVariable int id){
        Game game = gameSvc.getGameById(id);

        return ResponseEntity.ok().body(game.toJson().toString());
    }
}
