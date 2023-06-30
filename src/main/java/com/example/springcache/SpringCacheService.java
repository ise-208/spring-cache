package com.example.springcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SpringCacheService {
    private Emp emp;

    public SpringCacheService(Emp emp) {
        this.emp = emp;
    }


    private final LoadingCache<String, Integer> c1 = Caffeine
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

    private final Cache<String, Emp> c2 = Caffeine
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .recordStats()
            .build();


    @Cacheable("randomCache")
    public Integer randomCacheableCache() {
        Random random = new Random();
        return random.nextInt(100);
    }

    @Cacheable(value = "randomCache", condition = "#cacheables.contains")
    public Integer randomCacheableCacheCondition() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public Integer randomLoadCache() {
        return c1.get("key");
    }

    public Integer randomLoadCache2() {
        c2.get("key",f -> {
            return null;
        });
        return c1.get("key");
    }
}
