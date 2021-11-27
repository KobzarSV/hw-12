package main.java.ua.goit.task1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class WaterMolecules {
    static Semaphore semaphoreHydrogen = new Semaphore(2);
    static Semaphore semaphoreOxygen = new Semaphore(1);
    private static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) {

        for (int i = 0; i < 6; i++) {
            Thread hydrogen = new Thread(() -> {
                try {
                    releaseHydrogen();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            hydrogen.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread oxygen = new Thread(() -> {
                try {
                    releaseOxygen();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            oxygen.start();
        }
    }

    public static void releaseHydrogen() throws InterruptedException, BrokenBarrierException {
        semaphoreHydrogen.acquire();
        barrier.await();
        System.out.println("H");
        Thread.sleep(3000);
        semaphoreHydrogen.release();

    }

    public static void releaseOxygen() throws InterruptedException, BrokenBarrierException {
        semaphoreOxygen.acquire();
        barrier.await();
        Thread.sleep(100);
        System.out.println("O");
        Thread.sleep(3000);
        semaphoreOxygen.release();
    }
}
