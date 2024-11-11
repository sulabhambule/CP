import java.io.*;
import java.util.*;

public class exp {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

    int t = Integer.parseInt(tokenizer.nextToken());

    for (int tc = 0; tc < t; tc++) {
      tokenizer = new StringTokenizer(reader.readLine());
      int n = Integer.parseInt(tokenizer.nextToken());

      int[] a = new int[n];
      int[] p = new int[n];
      int[] q = new int[n];
      int[] pair = new int[n + 1];
      boolean[] v = new boolean[n + 1];
      int[] c = new int[n + 1];

      PriorityQueue<Integer> q1 = new PriorityQueue<>(Collections.reverseOrder());

      Arrays.fill(c, 0);
      Arrays.fill(v, false);

      tokenizer = new StringTokenizer(reader.readLine());
      for (int i = 0; i < n; i++) {
        a[i] = Integer.parseInt(tokenizer.nextToken());
        c[a[i]]++;
      }

      for (int i = 1; i <= n; i++) {
        if (c[i] == 0) {
          q1.add(i);
        }
      }

      int f = 0;

      for (int i = n; i >= 1; i--) {
        if (c[i] == 1) {
          pair[i] = i;
        } else if (c[i] == 2) {
          if (q1.peek() < i) {
            pair[i] = q1.peek();
            pair[q1.peek()] = i;
            q1.poll();
          } else {
            f++;
            break;
          }
        } else if (c[i] > 2) {
          f++;
          break;
        }
      }

      if (f > 0) {
        System.out.println("NO");
        continue;
      }

      System.out.println("YES");

      for (int i = 0; i < n; i++) {
        if (!v[a[i]]) {
          p[i] = a[i];
          q[i] = pair[a[i]];
          v[a[i]] = true;
        } else {
          p[i] = pair[a[i]];
          q[i] = a[i];
        }
      }

      for (int i = 0; i < n; i++) {
        System.out.print(p[i] + " ");
      }
      System.out.println();

      for (int i = 0; i < n; i++) {
        System.out.print(q[i] + " ");
      }
      System.out.println();
    }
  }
}