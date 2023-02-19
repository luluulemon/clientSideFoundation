package vttp.csf.oldAssess.carousellPage.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.oldAssess.carousellPage.models.ItemPost;

@Repository
public class PostRedisRepository {
    
    @Autowired
    private RedisTemplate<String, ItemPost> redisTemplate;

    public void cachePost(ItemPost post){
        redisTemplate.opsForValue().set(post.getId(), post);
    }
}
