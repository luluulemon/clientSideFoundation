package vttp.csf.day36server.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.day36server.demo.models.Game;
import vttp.csf.day36server.demo.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepo;

    public List<Game> getGames(String limit, String offset){
        return gameRepo.getGames(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    public Game getGameById(int id){
        return gameRepo.getGameById(id);
    }
}
