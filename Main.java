import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int[] prefix = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefix[i + 1] = prefix[i] ^ a[i];
            }
            long ans = 0;
            HashSet<Integer> set = new HashSet<>();
            for (int xor = (1 << 8) - 1; xor >= 0; xor--) {
                set = new HashSet<>();
                boolean f = false;
                for (int num : prefix) {
                    if (set.contains(num ^ xor)) {
                        ans = Math.max(ans, xor);
                        f = true;
                        break;
                    }
                    set.add(num);
                }
                if (f) {
                    break;
                }
            }

            System.out.println(ans);
        }
    }
}
