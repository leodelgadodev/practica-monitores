package ej3;

public class MainBuffer {
    private static Buffer buffer;

    public static void start() {
        buffer = new Buffer(2);
        while (true) {
            Consumer consumer = new Consumer(buffer);
            Producer producer = new Producer(buffer);
            consumer.start();
            producer.start();
        }
    }
}
