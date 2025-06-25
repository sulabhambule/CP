import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.next();
    int n = s.length();
    int[] pi = new int[n];
    for (int i = 1; i < n; i++) {
      int j = pi[i - 1];
      while (j > 0 && s.charAt(i) != s.charAt(j)) {
        j = pi[j - 1];
      }
      if (s.charAt(i) == s.charAt(j))
        j++;
      pi[i] = j;
    }
    List<Integer> periods = new ArrayList<>();
    int k = pi[n - 1];
    while (k > 0) {
      periods.add(n - k);
      k = pi[k - 1];
    }
    periods.add(n);
    Collections.sort(periods);

    for (int p : periods) {
      System.out.print(p + " ");
    }
    in.close();
  }
}
