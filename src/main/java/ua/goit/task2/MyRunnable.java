package main.java.ua.goit.task2;

@Repeat(6)
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello!");
    }
}
