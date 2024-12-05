package org.vetau.infrastructure.cache.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class RedisInfrasServiceImpl implements RedisIfrasService{


    @Resource
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisInfrasServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public String getString(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key))
                .map(String::valueOf)
                .orElse(null);
    }

    @Override
    public void setString(String key, String value) {

        if(!StringUtils.hasLength(key)){
            return;
        }
        try{
            redisTemplate.opsForValue().set(key, value);
        }catch (Exception e){
            log.error("set String error: {}", e.getMessage());
        }
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        Object result = redisTemplate.opsForValue().get(key);
        log.info("get Cache: {}", result);
        if(result == null){
            return null;
        }

        if(result instanceof String){
            try{
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue((String)result, clazz);
            }catch (JsonProcessingException e){
                log.error("Error Deserialize JSON to object: {}", e.getMessage());
            }

        }

        if(result instanceof Map){
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.convertValue(result, clazz);
            }catch (IllegalArgumentException e){
                log.error("error converting linkedhashmap: {}", e.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override
    public void setObject(String key, Object value) {
        if(!StringUtils.hasLength(key)){
            return;
        }

        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (RuntimeException e) {
            log.error("set Object error: {}", e.getMessage());
        }
    }
}
