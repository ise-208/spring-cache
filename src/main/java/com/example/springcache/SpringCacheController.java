package com.example.springcache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCacheController {
    SpringCacheService springCacheService;

    public SpringCacheController(SpringCacheService springCacheService) {
        this.springCacheService = springCacheService;
    }

    @GetMapping("/hello")
    public String hello() {
        return springCacheService.randomCacheableCache().toString();
    }

    @GetMapping("/hello2")
    public String hello2() {
        return springCacheService.randomLoadCache().toString();
    }
}
