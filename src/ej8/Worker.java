package ej8;

import ej3.Buffer;

public class Worker implements Runnable {

    private final Buffer buffer;

    public Worker(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        try {
            this.buffer.read();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void interrumpt() {

    }
}
