package com.wm.app.sourcecode.demo;

import com.wm.app.solutions.SolutionBase;

public class ThreadLocalDemo extends SolutionBase {
    public ThreadLocal<Integer> localValue = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public void solve() {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(() -> {
                int x = localValue.get();
                x += 5;
                localValue.set(x);
                print("Thread " + Thread.currentThread().getName() + " " + localValue.get());
            });
        }
        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }
    }
}