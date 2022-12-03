package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;

    private int state;

    public StrangeBitwiseGenerator (int period) {
        this.state = 0;
        this.period = period;
    }
    public double next() {
        state += 1;
        int weirdState = state & (state >>> 3) % period;
        int moreWeirdState = state & (state >> 3) & (state >> 8) % period;
        return normalize(moreWeirdState % period);
    }

    private double normalize(int value) {
        return -1 + (double)(2 * value) / (period - 1);
    }
}
