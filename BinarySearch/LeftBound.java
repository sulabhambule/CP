import java.util.*;

// closest ti the left
public class LeftBound {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        long[] arr = new long[n];
        long[] queries = new long[k];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }

        for (int i = 0; i < k; i++) {
            queries[i] = scanner.nextLong();
        }

        scanner.close();

        // Process each query
        for (int i = 0; i < k; i++) {
            int index = binarySearch(arr, queries[i], n);
            System.out.println(index);
        }
    }

    private static int binarySearch(long[] arr, long target, int n) {
        int l = -1;
        int r = n;

        while (l + 1 < r) {
            int m = (r + l) / 2;
            if (arr[m] <= target) {
                l = m;
            } else {
                r = m;
            }
        }

        return l + 1; // Converting 0-based index to 1-based index
    }
}
