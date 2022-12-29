package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    private static final int THREADS_NUMBER = 10000;
   // private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread1.start();

        new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+ ", " + Thread.currentThread().getState());
        }).start();
        System.out.println(thread1.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();

        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(counter);

        new MainConcurrency().inc();
    }

    private synchronized void inc() {
            counter++;
            //notifyAll();
    }
}