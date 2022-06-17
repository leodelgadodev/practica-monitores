package ej3;

import java.util.Random;

public class Producer extends Thread {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            Random rand = new Random();
            int randomNum = rand.nextInt((10 - 1) + 1) + 1;
            try {
                this.buffer.write(randomNum);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
