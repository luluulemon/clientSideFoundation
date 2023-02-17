package vttp.csf.workshop39.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.workshop39.server.models.Hero;

@Repository
public class CharacterRepository {
    
    @Autowired @Qualifier("marvel-cache")
    private RedisTemplate<String, Hero> redisTemplate;

    public void cacheCharacter(Hero hero){
        redisTemplate.opsForValue().set(Integer.toString(hero.getId()), hero);
    }

    public boolean checkCharacter(String id){
        return redisTemplate.hasKey(id);
    }

    public Hero retrieveCharacter(String id){
        return redisTemplate.opsForValue().get(id);
    }
}
