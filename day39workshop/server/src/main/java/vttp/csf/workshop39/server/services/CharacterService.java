package vttp.csf.workshop39.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.csf.workshop39.server.models.Hero;
import vttp.csf.workshop39.server.repositories.CharacterRepository;

import java.io.StringReader;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class CharacterService {

    private String pubKey = "84ee7cb636cbb0ff6d8b0dc5ef171d0d";
    private String priKey = "1dcacd3f4fe7e8172f029c55356bf3a554800c6e";

    @Autowired
    private CharacterRepository charRepo;


    public String getCharacters(String character){
        
        String endPoint = "http://gateway.marvel.com/v1/public/characters";

        String timeStamp = Long.toString( System.currentTimeMillis() );
        String hashString = timeStamp + priKey + pubKey;
        
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hashString.getBytes());
            byte[] digest = md.digest();
            String myHash = HexFormat.of().formatHex(digest);

            String url = UriComponentsBuilder.fromUriString(endPoint)
            .queryParam("ts", timeStamp)
            .queryParam("apikey", pubKey)
            .queryParam("hash", myHash)
            .queryParam("nameStartsWith", character).toUriString(); 
            System.out.println(url);

            RestTemplate template = new RestTemplate();
            RequestEntity req = RequestEntity.get(url).build();
            ResponseEntity<String> resp = template.exchange(req, String.class);


            JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
            JsonObject results = reader.readObject(); 

            List<JsonObject> heroList = new LinkedList<>();
            results.getJsonObject("data").getJsonArray("results")
                                        .stream()
                                        .map(h -> (JsonObject)h )
                                        .forEach( h -> heroList.add( Hero.getDetails(h)));

            return heroList.toString();

        } catch(Exception e)
        {   e.printStackTrace();    }

        return "";
    }


    public String getCharacter(int id){
        // check cache
        if( charRepo.checkCharacter(Integer.toString(id)) )
        {   Hero hero = charRepo.retrieveCharacter(Integer.toString(id));   
            System.out.println(">>>>>>> Retrieved from cache");
            return hero.toJson().toString();
        }


        String endPoint = "http://gateway.marvel.com/v1/public/characters" + "/" + id;

        String timeStamp = Long.toString( System.currentTimeMillis() );
        String hashString = timeStamp + priKey + pubKey;
        
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hashString.getBytes());
            byte[] digest = md.digest();
            String myHash = HexFormat.of().formatHex(digest);

            String url = UriComponentsBuilder.fromUriString(endPoint)
            .queryParam("ts", timeStamp)
            .queryParam("apikey", pubKey)
            .queryParam("hash", myHash)
            .toUriString(); 
            System.out.println(url);

            RestTemplate template = new RestTemplate();
            RequestEntity req = RequestEntity.get(url).build();
            ResponseEntity<String> resp = template.exchange(req, String.class);

            JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
            JsonObject results = reader.readObject(); 

            JsonObject heroJson =  results.getJsonObject("data").getJsonArray("results").getJsonObject(0);
            JsonObject cacheHero = Hero.getDetails(heroJson);
            // convert to Hero Object, and send to cache
            charRepo.cacheCharacter( Hero.create(cacheHero) );

            return cacheHero.toString();

        } catch(Exception e)
        {   e.printStackTrace();    }

        return "";
    }
}
