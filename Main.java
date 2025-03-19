
import java.io.*;
import java.util.*;

public class Main {

     static FastReader in;
     static PrintWriter out;
     static int MOD = (int) 1e9 + 7;

     static void solve() {
          int n = in.nextInt();
          long[] a = new long[n];
          long min = Long.MAX_VALUE;
          for (int i = 0; i < n; i++) {
               a[i] = in.nextLong();
               min = Math.min(min, a[i]);
          }
          int minCount = 0;
          for (long x : a) {
               if (min == x) {
                    minCount++;
               }
               if ((min & x) != min) {
                    out.println(0);
                    return;
               }
          }
          long fact = 1;
          for (int i = 1; i <= n - 2; i++) {
               fact = (fact * i) % MOD;
          }
          long ans = (1L * minCount * (minCount - 1)) % MOD;
          ans = (ans * fact) % MOD;
          out.println(ans);
     }

     public static void main(String[] args) {
          in = new FastReader();
          out = new PrintWriter(System.out);
          int t = in.nextInt();
          while (t-- > 0) {
               solve();
          }
          out.close();
     }

     static class FastReader {

          BufferedReader br;
          StringTokenizer st;

          public FastReader() {
               br = new BufferedReader(new InputStreamReader(System.in));
          }

          String next() {
               while (st == null || !st.hasMoreElements()) {
                    try {
                         st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
               return st.nextToken();
          }

          int nextInt() {
               return Integer.parseInt(next());
          }

          long nextLong() {
               return Long.parseLong(next());
          }
     }
}
