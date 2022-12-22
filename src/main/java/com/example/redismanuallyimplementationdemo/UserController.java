package com.example.redismanuallyimplementationdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    RedisTemplate<String,User> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;
    //=============================FOR STORING SIMPLE VALUE===========================================
    @PostMapping("/add-value")
    public void addValue(@RequestParam("key") String key, @RequestBody() User user) {

        redisTemplate.opsForValue().set(key,user);
    }

    @GetMapping("/get-value")
    public User getValue(@RequestParam("key") String key) {

        return redisTemplate.opsForValue().get(key);
    }
    //===============================FOR STORING LIST =========================================
    @PostMapping("/add-to-list")
    public void addToList(@RequestParam("key") String key, @RequestBody() User user) {

        redisTemplate.opsForList().leftPush(key,user);
    }

    @GetMapping("/get-list")
    public List<User> getList(@RequestParam("key") String key){

        return redisTemplate.opsForList().range(key,0,-1);
    }
    //============================FOR STORING HASHMAP=============================================
    @PostMapping("/hm-set")
    public void setHashValue(@RequestParam("key") String key,
                             @RequestParam("hashKey") String hashKey,
                             @RequestParam("hashValue")String hashValue) {

        redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }

    @GetMapping("/hm-get")
    public String getHashValue(@RequestParam("key") String key, @RequestParam("hashKey") String hashKey) {
        return (String) redisTemplate.opsForHash().get(key,hashKey);
    }

    @PostMapping("/add-to-map")
    public void addToMap(@RequestParam("key") String key, @RequestBody() User user) {

        Map map = objectMapper.convertValue(user,Map.class);

        redisTemplate.opsForHash().putAll(key,map);

    }

    @GetMapping("/get-map")
    public User getHashValue(@RequestParam("key") String key) {

        Map map = redisTemplate.opsForHash().entries(key);

        User user = objectMapper.convertValue(map,User.class);


        return user;
    }
}
