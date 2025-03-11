import java.util.*;

public class Main {
    static TreeMap<Integer, Integer> mp = new TreeMap<>();
    static List<Long> list = new ArrayList<>();

    static int BSFUN(List<Long> arr, long target) {
        int left = 0, right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    static void fun(int rad, int center) {
        int left = center - rad, right = center + rad;
        long rsquare = (long) rad * rad;

        for (int i = left; i <= right; i++) {
            long xq = (long) (i - center) * (i - center);
            long maxy = rsquare - xq;
            int cnty = BSFUN(list, maxy);
            mp.put(i, Math.max(mp.getOrDefault(i, 0), cnty));
        }
    }

    static void solve(Scanner sc) {
        int n = sc.nextInt(), m = sc.nextInt();
        mp.clear();
        int[] c = new int[n], rad = new int[n];

        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            rad[i] = sc.nextInt();
            fun(rad[i], c[i]);
        }

        long ans = 0;
        for (var x : mp.entrySet()) {
            if (x.getValue() == 1) {
                ans++;
            } else {
                ans += (x.getValue() * 2 - 1);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i <= 100000; i++) {
            list.add((long) i * i);
        }

        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
        sc.close();
    }
}