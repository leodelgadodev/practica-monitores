package ej2;

public class Semaphore {
    private int permission;

    public Semaphore(int semaphore) {
        this.permission = semaphore;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permission == 0) {
            wait();
        }

        permission--;
    }

    public synchronized void release() {
        permission++;
        notify();
    }
}
