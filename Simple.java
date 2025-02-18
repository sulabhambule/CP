import java.util.*;

public class Simple {
    public static void solve(Scanner in) {
        int n = in.nextInt();
        long[] c = new long[n];
        for (int i = 0; i < n; i++) {
            c[i] = in.nextLong();
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
        sc.close();
    }
}
