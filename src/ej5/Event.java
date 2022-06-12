package ej5;

public class Event {

    // notifies all subscribers, publishes only one subscribed
    public synchronized void publish() {
        notifyAll();
    }

    // wait for event publish
    public synchronized void subscribe() throws InterruptedException {
        wait();
    }
}
