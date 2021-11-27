package main.java.ua.goit.task2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(
                1, 3, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        myThreadPoolExecutor.execute(new MyRunnable());

        myThreadPoolExecutor.shutdown();
    }
}
