package com.fpiacentini.challenge.ratelimit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BucketManagerTests {

    @Test
    void givenBucketWith10tokensAndRefillSeconds1_whenTryConsume_ShouldReturnTrue(){
        BucketManager bucketManager = new BucketManager(10, 1);
        assertTrue(bucketManager.tryConsume());
    }

    @Test
    void givenBucketWith3tokensAndRefillSeconds1800_whenTryConsume4Times_ShouldReturnFalseTheLastTime(){
        BucketManager bucketManager = new BucketManager(3, 1800);
        assertTrue(bucketManager.tryConsume());
        assertTrue(bucketManager.tryConsume());
        assertTrue(bucketManager.tryConsume());
        assertFalse(bucketManager.tryConsume());
    }

}
