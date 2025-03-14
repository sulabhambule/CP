import java.io.*;
import java.util.*;

public class Main {
     public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
     static FastReader in = new FastReader();

     public static void main(String[] args) {
          int t = in.nextInt();
          while (t-- > 0) {
               int n = in.nextInt();
               long x = in.nextLong();
               long y = in.nextLong();
               long[] a = new long[n];
               int count = 0;
               long alice = x;
               long bob = x + 3;
               for (int i = 0; i < n; i++) {
                    a[i] = in.nextLong();
                    if (a[i] % 2 == 1) {
                         count++;
                    }
               }
               String ans = "";
               if (count % 2 == 0) {
                    if (alice % 2 != y % 2) {
                         ans = "Bob";
                    } else {
                         ans = "Alice";
                    }
               } else {
                    if (alice % 2 == y % 2) {
                         ans = "Bob";
                    } else {
                         ans = "Alice";
                    }
               }
               out.println(ans);
          }
          out.flush();
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