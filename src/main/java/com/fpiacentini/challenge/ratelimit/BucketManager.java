package com.fpiacentini.challenge.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Scope("singleton")
public class BucketManager {

    private final static int TOKENS_CAPACITY = 3;
    private Bucket bucket;

    public BucketManager(){
        Refill refill = Refill.intervally(TOKENS_CAPACITY, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(TOKENS_CAPACITY, refill);
        Bucket bucket = Bucket.builder().addLimit(limit).build();
        this.bucket = bucket;
    }

    public boolean tryConsume(){
        return bucket.tryConsume(1);
    }
}
