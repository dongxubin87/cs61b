import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */

    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] arr = Arrays.copyOf(asciis, asciis.length);


        int R = 257;
        int maxlen = 0;
        for (String x : arr) {
            maxlen = Math.max(maxlen, x.length());
        }

        for (int i = maxlen - 1; i >= 0; i--) {
            int[] counts = new int[R];
            for (String x : arr) {
                int c = 0;
                if (x.length() == 0) {
                    counts[c]++;
                } else {
                    c = x.length() > i ? x.charAt(i) : 0;
                    counts[c + 1]++;
                }
            }

            int[] starts = new int[R];
            int pos = 0;
            for (int j = 0; j < R; j++) {
                starts[j] = pos;
                pos += counts[j];
            }
            String[] sorted = new String[arr.length];
            for (String x : arr) {
                int item = 0;
                if (x.length() > 0) {
                    item = x.length() > i ? x.charAt(i) : 0;
                    item += 1;
                }
                int place = starts[item];
                sorted[place] = x;
                starts[item]++;
            }
            arr = sorted;
        }

        return arr;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
