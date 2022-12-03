package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    /** period.*/
    private int period;

    /** state.*/
    private int state;

    /**
     * class constructor.
     * @param period period
     */
    public SawToothGenerator (int period) {
        this.state = 0;
        this.period = period;
    }

    /** interface method.
     * @return return normalized new state
     */
    public double next() {
        state += 1;
        return normalize(state % period);
    }

    /**
     * normalize a given integet number to minus1 to positive 1
     * @param value given value
     * @return normalized value
     */
    private double normalize(int value) {
        return -1 + (double)(2 * value) / (period - 1);
    }
}
