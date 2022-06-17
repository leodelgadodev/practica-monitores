package ej3;

import java.util.Random;

public class Consumer extends Thread {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(this.buffer.read());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
