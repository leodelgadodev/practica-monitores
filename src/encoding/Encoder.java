package encoding;

import java.util.ArrayList;

public class Encoder {

    private final Object[] frames;

    private final ArrayList<ArrayList<Object>> encodedPacks;
    private final int compressorFrameRate;

    private final int encodingFrameRate;

    private int firstAvailableFrame;

    private int lastAvailableFrame;

    private final int maxFrames;

    private int currentCompressorThreads;

    // INV. REP: compressorFrameRate <= maxFrameAmount
    // example: max frames = 100, frame rate = 10
    public Encoder(int maxFrameAmount, int compressorFrameRate, int encodingFrameRate) {
        this.frames = new Object[maxFrameAmount+1];
        this.maxFrames = maxFrameAmount;
        this.encodingFrameRate = encodingFrameRate;
        this.compressorFrameRate = compressorFrameRate;
        this.currentCompressorThreads = 0;
        this.encodedPacks = new ArrayList<ArrayList<Object>>();
    }

    public synchronized void putRawFrame(Object frame) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        frames[firstAvailableFrame] = frame;
        firstAvailableFrame = next(firstAvailableFrame);
        notifyAll();
    }

    // returns P raw frames, being P <= M (being M the max frame amount)
    // returns all available raw frames in the encoder buffer
    public synchronized ArrayList<Object> getPack() throws InterruptedException {
        while (!hasFramerate() || maxConcurrentCompressorThreadsReached()) {
            wait();
        }

        currentCompressorThreads++;

        ArrayList<Object> pack = new ArrayList<Object>();

        for (int i = 0; i < compressorFrameRate; i++) {
            Object frame = frames[lastAvailableFrame];
            pack.add(frame);
            lastAvailableFrame = next(lastAvailableFrame);
        }

        notifyAll();
        return pack;
    }


    // INV. REP: can be called only after getPack
    public synchronized void putEncodedPack(ArrayList<Object> encodedPack) throws InterruptedException {
        while (currentCompressorThreads == 0) {
            wait();
        }

        currentCompressorThreads--;
        encodedPacks.add(encodedPack);
        notifyAll();
    }

    private boolean isFull() {
        return next(firstAvailableFrame) == lastAvailableFrame;
    }

    private boolean hasFramerate() {
        return (lastAvailableFrame + compressorFrameRate) % maxFrames > firstAvailableFrame;
    }
    private int next(int i) {
        return (i+1) % maxFrames;
    }

    private boolean maxConcurrentCompressorThreadsReached() {
        return currentCompressorThreads == encodingFrameRate;
    }
}
