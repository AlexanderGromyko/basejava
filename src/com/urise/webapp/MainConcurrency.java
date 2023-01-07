package com.urise.webapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 1000;
    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();
    private int counter;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName());

//        Thread thread0 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
//            }
//        };
//        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

       // System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//
//       List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->
//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println( LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm.ss")));
                }
                latch.countDown();
                return 5;
            });
//            System.out.println(completionService.poll().isDone());
//            System.out.println(future.isDone());
//            System.out.println(future.get());
//            thread.start();
////            threads.add(thread);
        }

//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await();
        executorService.shutdown();
        System.out.println(mainConcurrency.counter);
        System.out.println(mainConcurrency.atomicInteger.get());
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//        lock.lock();
//        try {
        atomicInteger.incrementAndGet();

         //   counter++;
//        } finally {
//            lock.unlock();
//        }

//                wait();
//                readFile
//                ...
//        }
    }
}