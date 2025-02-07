import java.util.*;

public class Simple {
    public static void solve(Scanner in) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        Arrays.sort(a);
        int i = 0, j = n - 1;
        long ans = 0;
        long left = 0, right = a[n - 1];
        while (i <= j) {
            if (i == j) {
                ans += (right - left + 1) / 2;
                if (left != 0 || a[i] > 1) {
                    ans++;
                }
                break;
            }

            if (left + a[i] > right) {
                ans++;
                j--;
                long diff = right - left;
                a[i] -= diff;
                right = a[j];
                left = 0;
                ans += diff;
            } else if (left == right) {
                j--;
                left = 0;
                right = a[j];
                ans++;
            } else {
                left += a[i];
                ans += a[i];
                i++;
            }
        }
        System.out.println(ans);
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
