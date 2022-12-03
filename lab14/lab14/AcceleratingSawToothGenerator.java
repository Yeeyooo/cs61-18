package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    /** period.*/
    private int period;

    /** state.*/
    private int state;

    /** adjust factor.*/
    private final double factor;

    /**
     * class constructor.
     * @param period period
     * @param factor adjust factor
     */
    public AcceleratingSawToothGenerator(int period, double factor) {
        this.state = 0;
        this.period = period;
        this.factor = factor;
    }

    /**
     * interface method;
     * @return new state
     */
    public double next() {
        if (state < period) {
            state += 1;
            return normalize(state % period);
        }
        state = 0;
        period = (int)(period * factor);
        return normalize(0);
    }

    /** helper method.
     * @param value value to be normalized
     * @return normalized value
     */
    private double normalize(int value) {
        return -1 + (double) (2 * value) / (period - 1);
    }
}
