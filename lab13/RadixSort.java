/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        if (asciis.length == 0 || asciis.length == 1) {
            return asciis;
        }
        int maxminium = 0;
        for (String string : asciis) {
            maxminium = Math.max(maxminium, string.length());
        }
        String[] afterAppend = appendZero(asciis, maxminium);
        for (int i = maxminium - 1; i >= 0; i--) {
            sortHelperLSD(afterAppend, i);
        }
        return deleteZero(afterAppend);
    }

    private static String[] appendZero(String[] asciis, int maxminium) {
        String[] result = new String[asciis.length];
        int k = 0;
        for (String string : asciis) {
            StringBuilder newstring = new StringBuilder(string);
            if (string.length() < maxminium) {
                for (int i = 0; i < maxminium - string.length(); i++) {
                    newstring.append("0");
                }
            }
            result[k++] = newstring.toString();
        }
        return result;
    }

    private static String[] deleteZero(String[] afterAppend) {
        String[] result = new String[afterAppend.length];
        int k = 0;
        for (String string : afterAppend) {
            StringBuilder newstring = new StringBuilder(string);
            for (int i = newstring.length() - 1; i >= 0 && newstring.charAt(i) == '0'; i--) {
                newstring.deleteCharAt(i);
            }
            result[k++] = newstring.toString();
        }
        return result;
    }


    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] count = new int[256 + 1];
        String[] aux = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            count[asciis[i].charAt(index) + 1]++;
        }
        for (int r = 0; r < 256; r++) {
            count[r + 1] += count[r];
        }
        for (int i = 0; i < asciis.length; i++) {
            aux[count[asciis[i].charAt(index)]++] = asciis[i];
        }
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = aux[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
