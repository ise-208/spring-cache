package com.example.springcache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SpringCacheService {

    private final LoadingCache<String, Integer> c = Caffeine
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .recordStats()
            .build(k -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return new Random().nextInt(100);
            });

    @Cacheable("randomCache")
    public Integer randomCacheableCache() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public Integer randomLoadCache() {
        return c.get("key");
    }
}
