package ej4;

// INV: N await calls in our code is exactly == N barrier size
public class Barrier {
    private int size;

    public Barrier(int size) {
        this.size = size;
    }

    public synchronized void await() throws InterruptedException {
        size--;

        if (size == 0) {
            notifyAll();
        } else if (size > 0) { // prevents more await calls to barrier than
            // its size from generating accidental deadlocks
            wait();
        }
    }
}
