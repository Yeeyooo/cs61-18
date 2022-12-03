package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;

    private int state;

    public SawToothGenerator (int period) {
        this.state = 0;
        this.period = period;
    }
    public double next() {
        state += 1;
        return normalize(state % period);
    }

    private double normalize(int value) {
        return -1 + (double)(2 * value) / (period - 1);
    }
}
