
import java.util.*;

public class Simple {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int t = in.nextInt();
    while (t-- > 0) {
      int n = in.nextInt();
      long h = in.nextLong();
      long[] a = new long[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextLong();
      }
      long ans = -1;
      long low = 0, high = (long) 1e18;
      while (low <= high) {
        long mid = (low + high) / 2;
        long points = 0;
        for (int i = 1; i < n; i++) {
          long a1 = a[i] - a[i - 1];
          points += Math.min(mid, a1);
        }
        points += mid;
        if (points >= h) {
          ans = mid;
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      System.out.println(ans);
    }
    in.close();
  }
}
