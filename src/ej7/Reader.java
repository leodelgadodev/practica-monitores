package ej7;

public class Reader extends Thread {
    public RW rw;

    public Reader(RW rw) {
        this.rw = rw;
    }
    public void deserialize() throws InterruptedException {
        this.rw.beginRead();
        System.out.println(this.rw.serial.toString());
        this.rw.endRead();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.deserialize();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
