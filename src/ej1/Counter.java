package ej1;

public class Counter {
    // inv. rep: counter no puede ser < 0
    // var compartida a la que hay que acceder en exclusion mutua
    private  int counter = 0;

    public synchronized void incrementar() {
        counter++;
        notify();
    }

    public synchronized void decrementar() throws InterruptedException {
        // while y no if xq si justo el notify despierta un proceso a decrementar
        // cuando no deberia, el proceso deberia seguir waiteando
        while (counter == 0) {
            wait();
        }

        counter--;
    }
}
