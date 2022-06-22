package ej8;

public class PoisonPill implements Runnable {
    @Override
    public void run() {
        throw new PoisonException();
    }
}
