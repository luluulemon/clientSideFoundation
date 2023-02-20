package vttp.csf.oldAssess.carousellPage.repositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.oldAssess.carousellPage.models.ItemPost;

@Repository
public class PostRedisRepository {
    
    @Autowired
    private RedisTemplate<String, ItemPost> redisTemplate;

    public void cachePost(ItemPost post){
        redisTemplate.opsForValue().set(post.getId(), post, Duration.ofMinutes(15));
    }

    public boolean checkPost(String id){
        return redisTemplate.hasKey(id);
    }

    public ItemPost getPost(String id){
        ItemPost post = redisTemplate.opsForValue().get(id);
        return post;
    }

    public boolean deletePost(String id){
        return redisTemplate.delete(id);
    }

}
