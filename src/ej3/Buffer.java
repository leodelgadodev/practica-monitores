package ej3;

public class Buffer {
    private int[] buffer;
    private int begin;
    private int end;
    private int size;

    public Buffer(int size) {
        this.size = size;
    }

    public synchronized void fill(int element) {

    }

    public synchronized int empty() {
        return 666;
    }

}
