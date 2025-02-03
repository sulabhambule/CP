import java.util.*;

public class Simple {
    static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            solve(in);
        }
    }

    /*
     * Read the question carefully, Integer Overflow, Think in terms of Bit.
     */

    private static void solve(Scanner in) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        
    }
}