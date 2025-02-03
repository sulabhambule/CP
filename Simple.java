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

    private static void solve(Scanner in) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        if (k == n) {
            List<Long> list = new ArrayList<>();
            for (int i = 1; i < n; i += 2) {
                list.add(a[i]);
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != (i + 1)) {
                    System.out.println(i + 1);
                    return;
                }
            }
            System.out.println(1 + (n / 2));
        } else {
            if (a[1] != 1 || a[2] != 1) {
                System.out.println(1);
                return;
            }
            int index = n - k + 1;
            for (int i = 1; i < index; i++) {
                if (a[i] != 1) {
                    System.out.println(1);
                    return;
                }
            }
            if (a[index] != 1) {
                System.out.println(1);
            } else {
                if (a[1] == 1 && a[2] != 2) {
                    System.out.println(2);
                    return;
                }

                int idx2 = index;
                int p = 1;
                for (int i = idx2; i < n; i += 2) {
                    if (a[i] != p) {
                        System.out.println(p);
                        return;
                    }
                    p++;
                }
                System.out.println(p);
            }
        }
    }
}