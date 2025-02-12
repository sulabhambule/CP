import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);

    static void subh() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long[] pref = new long[n + 1];
        long[] suff = new long[n + 1];
        long[] wierdSum = new long[n + 1];
        long[] wierdSum2 = new long[n + 1];
        pref[0] = a[0];
        for (int i = 1; i < n; i++) {
            pref[i] += (a[i] + pref[i - 1]);
        }
        for (int i = n - 1; i >= 0; i--) {
            suff[i] += (a[i] + suff[i + 1]);
        }
        wierdSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            wierdSum[i] = (i + 1) * a[i];
            wierdSum[i] += wierdSum[i - 1];
        }
        wierdSum2[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            wierdSum2[i] = (n - (i)) * a[i];
            wierdSum2[i] += wierdSum2[i + 1];
        }
        int q = in.nextInt();
        while (q-- > 0) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int k = in.nextInt();

            long ans = 0;
            if (k == r - l + 1) {
                System.out.println(pref[r] - (l > 0 ? pref[l - 1] : 0));
                continue;
            }
            if (k == 1) {
                ans += (pref[r] - (l > 0 ? pref[l - 1] : 0));
            } else {
                long sum1 = pref[r] - (l > 0 ? pref[l - 1] : 0);
                sum1 *= k;
                // l se l + k - 2 tkk minus
                long sum2 = pref[l + k - 2] - (l > 0 ? pref[l - 1] : 0);
                long sum3 = pref[r] - (r - (k - 2) > 0 ? pref[r - k + 2 - 1] : 0);
                sum1 -= ((sum2 * k) + (sum3 * k));
                long weird1 = wierdSum[l + k - 2] - (l > 0 ? wierdSum[l - 1] : 0);
                long weird1Remove = l * (pref[l + k - 2] - (l > 0 ? pref[l - 1] : 0));
                weird1 -= weird1Remove;
                long weird2 = wierdSum2[r - k + 2] - (r < n - 1 ? wierdSum2[r + 1] : 0);
                long weird2Remove = (n - (r + 1)) * (suff[r - k + 2] - (r < n - 1 ? suff[r + 1] : 0));
                // System.out.println(weird2Remove);
                weird2 -= weird2Remove;

                sum1 += (weird1 + weird2);
                ans += sum1;
            }
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            subh();
        }
        in.close();
    }
}
