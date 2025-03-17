import java.io.*;
import java.util.*;

public class Main {
     public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
     static FastReader in = new FastReader();

     public static void main(String[] args) {
          int t = in.nextInt();
          while (t-- > 0) {
               long n = in.nextLong();
               long m = in.nextLong();
               long nn = n;
               int cnt2 = 0, cnt5 = 0;
               while (n > 0 && n % 2 == 0) {
                    cnt2++;
                    n /= 2;
               }
               while (n > 0 && n % 5 == 0) {
                    cnt5++;
                    n /= 5;
               }
               long k = 1;
               while (cnt2 < cnt5 && 2 * k <= m) {
                    cnt2++;
                    k *= 2;
               }
               while (cnt5 < cnt2 && 5 * k <= m) {
                    cnt5++;
                    k *= 5;
               }
               while (k * 10 <= m) {
                    k *= 10;
               }
               if (k == 1) {
                    out.println(m * nn);
               } else {
                    k *= (m / k); // 1 <= m / k < 10
                    out.println(k * nn);
               }
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