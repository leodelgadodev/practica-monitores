package ej7;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainRW {

    public void runWithReadersPriority() {
        RW rw = new RW();
        ArrayList<Reader> readers = this.getReaders(rw);
        ArrayList<Writer> writers = this.getWriters(rw);

        for (Reader r: readers) {
            r.start();
        }

        for (Writer w: writers) {
            w.start();
        }
        // ? which type has priority?
    }

    public void runWithWritersPriority() {
     // no fucking clue
    }

    public ArrayList<Reader> getReaders(RW rw) {
        ArrayList<Reader> readers = new ArrayList<Reader>();
        for (int i = 0; i < 4; i++) {
            readers.add(new Reader(rw));
        }
        return readers;
    }

    public ArrayList<Writer> getWriters(RW rw) {
        ArrayList<Writer> writers = new ArrayList<Writer>();
        for (int i = 0; i < 4; i++) {
            writers.add(new Writer(rw));
        }
        return writers;
    }

}
