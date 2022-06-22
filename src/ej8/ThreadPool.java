package ej8;

import ej3.Buffer;

import java.util.ArrayList;

public class ThreadPool {
    private Buffer buffer;
    private ArrayList<Worker> workers;

    public ThreadPool(int buffersize) {
        this.buffer = new Buffer(buffersize);
        this.workers = new ArrayList<Worker>();
    }

    private void startWorkers(int threads) {
        for (int i = 0; i < threads; i++) {
            Worker worker = new Worker(buffer);
            workers.add(worker);
            worker.run();
        }
    }

    public void launch(int threads, int tasksAmount) throws InterruptedException {
        startWorkers(2);
        int count = tasksAmount;
        while (count > 0) {
            count--;
            buffer.write(new DummyTask());
        }
        stopWorkers();
    }

    public void stopWorkers() throws InterruptedException {
        for (Worker worker:workers) {
            buffer.write(new PoisonPill());
        }
        workers = new ArrayList<Worker>();
        System.exit(200);
    }
}
