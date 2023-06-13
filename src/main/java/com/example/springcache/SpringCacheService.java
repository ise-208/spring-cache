package com.example.springcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SpringCacheService {

    @Cacheable("randomCache")
    public Integer randomCache() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
