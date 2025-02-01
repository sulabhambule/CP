import java.util.*;

public class Simple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] a = new long[n];
            long max = 0, min = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
                max = Math.max(max, a[i]);
                min = Math.min(min, a[i]);
            }
            int count = 0;
            List<Integer> ls = new ArrayList<>();
            while (max != min) {
                count++;
                if (min % 2 == 0) {
                    max /= 2;
                    min /= 2;
                    ls.add(0);
                } else {
                    max = (max + 1) / 2;
                    min = (min + 1) / 2;
                    ls.add(1);
                }
            }
            if (n == 1) {
                System.out.println(0);
            } else if (count > n) {
                System.out.println(count);
            } else {
                System.out.println(count);
                for (int i : ls) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }
}