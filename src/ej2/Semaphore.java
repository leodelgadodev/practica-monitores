package ej2;

public class Semaphore {
    private int semaphore;

    public Semaphore(int semaphore) {
        this.semaphore = semaphore;
    }

    public synchronized void acquire() throws InterruptedException {
        while (semaphore == 0) {
            wait();
        }

        semaphore--;
    }

    public synchronized void release() {
        semaphore++;
        notify();
    }
}
