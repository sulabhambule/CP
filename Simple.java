import java.util.*;

public class Simple {

    public static void solve(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();

        long or = 0;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
            or ^= a[i];
        }

        long[] b = new long[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextLong();
        }

        long mn = or, mx = or;

        for (int i = 0; i < m; i++) {
            long num = b[i];
            for (int j = 0; j < 30; j++) {
                if ((num & (1L << j)) != 0) {
                    if (n % 2 == 0) {
                        mn &= ~(1L << j);
                    } else {
                        mx |= (1L << j);
                    }
                }
            }
        }

        System.out.println(mn + " " + mx);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            solve(in);
        }
        in.close();
    }
}
