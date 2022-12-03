package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;

    private int state;

    private final double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.state = 0;
        this.period = period;
        this.factor = factor;
    }

    public double next() {
        if (state < period) {
            state += 1;
            return normalize(state % period);
        }
        state = 0;
        period = (int)(period * factor);
        return normalize(0);
    }

    private double normalize(int value) {
        return -1 + (double) (2 * value) / (period - 1);
    }
}
