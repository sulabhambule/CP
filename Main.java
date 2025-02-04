import java.util.*;

public class Main {
    public static void solve(Scanner in) {
        int n = in.nextInt();

        int[] x = new int[n];
        int[] t = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
        }

        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(x[i] + t[i]);
            a.add(x[i] - t[i]);
        }

        int mn = Collections.min(a);
        int mx = Collections.max(a);

        int sum = mn + mx;
        if (sum % 2 == 0) {
            System.out.println(sum / 2);
        } else {
            System.out.println((sum / 2) + ".5");
        }
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