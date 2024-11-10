import java.util.*;

public class Binary {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read the length of the array
        int n = in.nextInt();
        long[] arr = new long[n];

        // Read the array elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }

        // Sort the array to enable binary search
        Arrays.sort(arr);

        // Read the number of queries
        int k = in.nextInt();

        int[][] query = new int[k][2];
        for (int i = 0; i < k; i++) {
            query[i][0] = in.nextInt();
            query[i][1] = in.nextInt();
        }

        for (int[] q : query) {
            int l = q[0];
            int r = q[1];

            int left = funL(l, arr);
            int right = funR(r, arr);

            System.out.print((right - left) + " ");
        }

        in.close();
    }

    // Find the first position of an element > r
    private static int funR(long r, long[] arr) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= r) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // Find the first position of an element >= l
    private static int funL(long l, long[] arr) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < l) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
