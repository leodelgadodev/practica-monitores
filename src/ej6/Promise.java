package ej6;

public class Promise implements Future {
    private int result = 0;
    // locks thread till promise is fulfilled (while result is unavailable)
    public synchronized int get() throws InterruptedException {
        while (this.result == 0) {
            wait();
        }

        final int tmp = result;
        this.result = 0;
        return tmp;
    }

    // assigns get result
    public synchronized void set(int result) {
        this.result = result;
        notify();
    }
}
