import java.util.*;

public class Simple {
    static List<Long> ls;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            ls = new ArrayList<>();
            long n = in.nextLong();
            long x = in.nextLong();
            long a = n - x;
            HashSet<Long> set = new HashSet<>();
            factor(a);

            for (long i : ls) {
                if (i % 2 == 0) {
                    set.add((i + 2) / 2);
                }
            }

            long ans = 0;
            a = n + x - 2;
            ls = new ArrayList<>();

            factor(a);
            for (long i : ls) {
                if (i % 2 == 0) {
                    set.add((i + 2) / 2);
                }
            }

            for (long i : set) {
                if (i >= x) {
                    ans++;
                }
            }

            System.out.println(ans);
        }
    }

    public static void factor(long n) {
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                // i -> is the one factor
                ls.add((long) i);
                if (i != n / i) {
                    // n / i -> is the other factor
                    ls.add(n / i);
                }
            }
        }
    }
}
