package com.flower.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 存储String类型数据到redis
     *
     * @param key   存储的键
     * @param value 存储的值
     * @return 存储成功返回true，否则返回false
     */
    public boolean set(String key, String value) {
        boolean reslut = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            reslut = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslut;
    }

    public boolean set(String key, String value, int minute) {
        boolean reslut = false;
        try {
            redisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES); // 时间单位位分钟
            reslut = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslut;
    }

    /**
     * 根据键取得redis上的内容
     *
     * @param key 键
     * @return 结果
     */
    public String get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
