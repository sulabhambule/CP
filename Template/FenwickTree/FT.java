
import java.util.*;

public class FT {
  static int[] fTree;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      arr[i] = in.nextInt();
    }

    for (int i = 0; i < n; i++) {
      System.out.print(arr[i] + " ");
    }

  }

  // the arr is taked as the 1 based indexing in case of 0 do arr[i - 1].
  static void preProcess(int[] arr) {
    fTree = new int[arr.length];
    for (int i = 1; i <= arr.length; i++) {
      update(i, arr[i]);
    }
  }

  static int query(int l, int r) {
    return prefixSum(r) - prefixSum(l - 1);
  }

  static int prefixSum(int idx) {
    int sum = 0;
    while (idx > 0) {
      sum += fTree[idx];
      idx -= (idx & -idx);
    }
    return sum;
  }

  static void update(int idx, int delta) {
    while (idx < fTree.length) {
      fTree[idx] += delta;
      idx += (idx & -idx);
    }
  }
}
