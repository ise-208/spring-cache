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
        return springCacheService.randomCache().toString();
    }
}
