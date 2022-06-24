package encoding;

public class FrameCompressor extends Thread {

    private final Encoder encoder;

    public FrameCompressor(Encoder e) {
        this.encoder = e;
    }
    @Override
    public void run() {
        super.run();
    }
}
