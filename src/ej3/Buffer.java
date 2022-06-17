package ej3;

public class Buffer {
    private final Object[] buffer;
    private int begin;
    private int end;

    public Buffer(int size) {
        this.buffer = new Object[size+1];
    }

    public synchronized Object read() throws InterruptedException {
        while (isEmpty()) {
                wait();
        }
        Object element = buffer[end];
        end = next(end);
        notifyAll();
        return element;
    }
    public synchronized void write(Object o) throws InterruptedException {
        while (isFull()) {
                wait();
        }
        buffer[begin] = o;
        begin = next(begin);
        notifyAll();
    }

    private boolean isEmpty() {
        return begin == end;
    }

    private boolean isFull() {
        return next(begin) == end;
    }

    private int next(int i) {
        return (i+1) % buffer.length;
    }

}
