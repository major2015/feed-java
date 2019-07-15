package com.xlab.deadlocks;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ConcurrentHashMap;

@RunWith(ConcurrentTestRunner.class)
public class TestConcurrentHashMapCompute {
    private final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    public TestConcurrentHashMapCompute() {
        map.put(1, 1);
        map.put(2, 2);
    }

    @Test
    public void update12() {
        map.compute(1, (k, v) -> {
            map.put(2, 1);
            return v;
        });
    }

    @Test
    public void update21() {
        map.compute(2, (k, v) -> {
            map.put(1, 1);
            return v;
        });
    }
}