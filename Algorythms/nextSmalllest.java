import java.util.*;

// Q : find the next smallest elements index in the array.
// O(N) solution if there is no next element smallest then the index is n.

public class nextSmalllest {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = in.nextInt();
    int[] next = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      next[i] = i + 1;
      while (next[i] != n && arr[i] <= arr[next[i]]) {
        next[i] = next[next[i]];
      }
    }

    for (int i = 0; i < n; i++) {
      System.out.print(next[i] + " ");
    }
    System.out.println();
  }
}
