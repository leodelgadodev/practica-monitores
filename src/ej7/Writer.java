package ej7;

import java.io.Serializable;

public class Writer extends Thread {
    private final RW rw;

    public void serialize(Serializable serial) throws InterruptedException {
        this.rw.beginWrite();
        this.rw.insecurelyWrite(serial);
        this.rw.endWrite();
    }

    public Writer(RW rw) {
        this.rw = rw;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.serialize("xd");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
