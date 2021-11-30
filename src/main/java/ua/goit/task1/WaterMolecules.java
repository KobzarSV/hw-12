package main.java.ua.goit.task1;

import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class WaterMolecules {
    static Semaphore semaphoreHydrogen = new Semaphore(2);
    static Semaphore semaphoreOxygen = new Semaphore(1);
    private static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce hydrogen (H) or oxygen (O) molecules to form H2O: ");

        String string = scanner.nextLine().toUpperCase(Locale.ROOT);

        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch == 'H') {
                Thread hydrogen = new Thread(() -> {
                    try {
                        releaseHydrogen();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                });
                hydrogen.start();
            } else if (ch == 'O') {
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
        scanner.close();
    }

    public static void releaseHydrogen() throws InterruptedException, BrokenBarrierException {
        semaphoreHydrogen.acquire();
        barrier.await();
        System.out.println("H");
        Thread.sleep(2000);
        semaphoreHydrogen.release();
    }

    public static void releaseOxygen() throws InterruptedException, BrokenBarrierException {
        semaphoreOxygen.acquire();
        barrier.await();
        Thread.sleep(100);
        System.out.println("O");
        Thread.sleep(2000);
        semaphoreOxygen.release();
    }
}
