import java.util.*;

// One Night At Freddy's

public class prob1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();

    while (t-- > 0) {

      int n = sc.nextInt();
      int m = sc.nextInt();
      int l = sc.nextInt();

      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = sc.nextInt();
      }

      int[] lvls = new int[m];
      int curr = n;

      for (int i = 0; i < l; i++) {

        int idx = Math.min(m, curr + 1) - 1;
        lvls[idx]++;

        Arrays.sort(lvls);
        reverse(lvls);

        if (curr > 0 && a[n - curr] - 1 == i) {
          lvls[0] = 0;

          Arrays.sort(lvls);
          reverse(lvls);

          curr--;
        }
      }

      System.out.println(lvls[0]);
    }

    sc.close();
  }

  static void reverse(int[] arr) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
      int temp = arr[l];
      arr[l] = arr[r];
      arr[r] = temp;
      l++;
      r--;
    }
  }
}