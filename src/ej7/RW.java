package ej7;

import java.io.Serializable;

public class RW {
    Serializable serial;
    private int readers = 0;
    private int writers = 0;
    private int awaitingWriters = 0;

    public synchronized void beginRead() throws InterruptedException {
        while(writers > 0 || awaitingWriters > 0) {
            wait();
        }

        this.readers++;
    }

    public synchronized void endRead() {
        this.readers--;
        if (readers == 0) notifyAll();
    }

    public synchronized void beginWrite() throws InterruptedException {
        this.awaitingWriters++;
        while (readers != 0 && writers != 0) {
            wait();
        }
        this.awaitingWriters--;
        this.writers++;
    }

    public synchronized void endWrite() {
        this.writers--;
        if (writers == 0) notifyAll();
    }

    public void insecurelyWrite(Serializable serial) {
        this.serial = serial;
    }
}
