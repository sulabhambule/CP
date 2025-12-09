import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
  public static void main(String[] args) throws java.lang.Exception {
    // your code goes here
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while (t-- > 0) {
      int n = in.nextInt();
      int[] p = new int[n];
      for (int i = 0; i < n; i++)
        p[i] = in.nextInt();
      List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
      int count = 0;
      for (int i = 0; i < n / 2; i++) {
        if (p[i] <= n / 2) {
          count++;
        }
      }
      if (count == 0 || count == n / 2) {
        System.out.println(1);
        System.out.print(n + " ");
        for (int i : p) {
          System.out.print(i + " ");
        }
        System.out.println();
        continue;
      }
      System.out.println(2);
      for (int i = 0; i < n; i++) {
        if (p[i] <= n / 2 && i < n / 2) {
          left.add(p[i]);
        }
        if (p[i] > n / 2 && i >= n / 2) {
          left.add(p[i]);
        }
        if (p[i] > n / 2 && i < n / 2) {
          right.add(p[i]);
        }
        if (p[i] <= n / 2 && i >= n / 2) {
          right.add(p[i]);
        }
      }
      // System.out.print(n + " ");
      System.out.print(left.size() + " ");
      for (int i : left) {
        System.out.print(i + " ");
      }
      System.out.println();
      System.out.print(right.size() + " ");
      for (int i : right) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}