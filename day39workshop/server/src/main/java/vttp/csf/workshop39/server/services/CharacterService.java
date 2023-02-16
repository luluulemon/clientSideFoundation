package vttp.csf.workshop39.server.services;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.MessageDigest;
import java.util.HexFormat;

@Service
public class CharacterService {

    private String pubKey = "84ee7cb636cbb0ff6d8b0dc5ef171d0d";
    private String priKey = "1dcacd3f4fe7e8172f029c55356bf3a554800c6e";

    public ResponseEntity<String> getCharacters(String character){
        
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

            return resp;
        } catch(Exception e)
        {   e.printStackTrace();    }

        return ResponseEntity.ok(null);

    }
}
