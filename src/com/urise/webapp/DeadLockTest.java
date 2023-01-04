package com.urise.webapp;

public class DeadLockTest {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " запущен!");
            workOn(object1, object2);
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " запущен!");
            workOn(object2, object1);
        });

        thread1.start();
        thread2.start();
    }

    private static void workOn(Object object1, Object object2) {
        System.out.println(Thread.currentThread().getName() + " ожидает доступность" + object1);
        synchronized (object1) {
            try {
                System.out.println(Thread.currentThread().getName() + " выполняет работу над " + object1);
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " продолжает занимать " + object1);
                System.out.println(Thread.currentThread().getName() + " ожидает доступность" + object2);
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + " выполняет работу над " + object2);
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + " завершил работу над " + object2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
