import java.io.*;
import java.util.*;

public class Simple {
     static FastIO in = new FastIO();
     static PrintWriter out = new PrintWriter(System.out);

     public static void main(String[] args) throws IOException {

          int t = in.nextInt();
          while (t-- > 0) {
               solve();
          }

          out.close();
     }

     static void solve() {

     }
}

class FastIO {
     BufferedReader br;
     StringTokenizer st;

     public FastIO() {
          br = new BufferedReader(new InputStreamReader(System.in));
     }

     String next() throws IOException {
          while (st == null || !st.hasMoreElements()) {
               st = new StringTokenizer(br.readLine());
          }
          return st.nextToken();
     }

     int nextInt() throws IOException {
          return Integer.parseInt(next());
     }
}
