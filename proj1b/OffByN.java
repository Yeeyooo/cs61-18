/** offbyn version.
 * @author yeahooooo
 */
public class OffByN implements CharacterComparator {
    /** difference between two characters.*/
    private int n;

    /**Constructor.
     * @param N difference.
     */
    public OffByN(int N) {
        this.n = N;
    }

    /**
     * function to judge.
     * @param x first character.
     * @param y second charactor.
     * @return return a boolean value.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
