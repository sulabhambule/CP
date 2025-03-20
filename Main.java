import java.io.*;
import java.util.*;

public class Main {

     static FastReader in;
     static PrintWriter out;
     static int MOD = (int) 1e9 + 7;

     static void solve() {
          
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
