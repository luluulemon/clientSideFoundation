package vttp.csf.day36server.demo.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp.csf.day36server.demo.models.Game;

@Repository
public class GameRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> getGames(int limit, int offset){

        Query query = new Query().limit(limit).skip(offset);
        List<Document> result = mongoTemplate.find(query, Document.class, "games");
        List<Game> games = new LinkedList<>();

        result.stream().forEach( g -> { games.add(Game.create(g));   });
        
        return games;
    }

    public Game getGameById(int id){

        Query query = Query.query(Criteria.where("gid").is(id) );
        List<Document> result = mongoTemplate.find(query, Document.class, "games");
        return Game.create( result.iterator().next() );
    }
}
