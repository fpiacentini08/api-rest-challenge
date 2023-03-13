package com.fpiacentini.challenge.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Scope("singleton")
public class BucketManager {

    private final Bucket bucket;

    public BucketManager(@Value("${ratelimit.refill.tokens:3}") int tokensCapacity, @Value("${ratelimit.refill.time.seconds:60}") long refillSeconds) {
        Refill refill = Refill.intervally(tokensCapacity, Duration.ofSeconds(refillSeconds));
        Bandwidth limit = Bandwidth.classic(tokensCapacity, refill);
        Bucket bucket = Bucket.builder().addLimit(limit).build();
        this.bucket = bucket;
    }

    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }
}
