package vttp.csf.workshop39.server.configs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vttp.csf.workshop39.server.models.Hero;

@Configuration
public class RedisConfig {
    
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort; 
    //@Value("${spring.redis.password}") 
    private String redisPassword = System.getenv("REDIS_PASSWORD");

    @Bean("marvel-cache")
    @Scope ("singleton")
    public RedisTemplate<String, Hero> createRedisTemplate()   {
    final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(); 
        config.setPort(redisPort.get());
        config.setHostName(redisHost);      
        config.setPassword(redisPassword);
    final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
    final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
    jedisFac.afterPropertiesSet();
    final RedisTemplate<String, Hero> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<Hero> jackson2JsonJsonSerializer = new Jackson2JsonRedisSerializer<Hero>(Hero.class); 
        template.setValueSerializer(jackson2JsonJsonSerializer); 

        //template.setValueSerializer(new StringRedisSerializer());
    return template; }

}
