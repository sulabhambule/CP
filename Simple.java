import java.io.*;
import java.util.*;

public class Simple {
     public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
     static FASTIO in = new FASTIO();

     public static void main(String[] args) throws IOException {
          int t = in.nextInt();
          while (t-- > 0) {
               solve();
          }
          out.close();
     }

     static void solve() {
          String s = in.next();
          int buffer = 0, cnt = 0;
          for (char x : s.toCharArray()) {
               if (x == ')')
                    cnt++;
               else if (x == '(')
                    cnt--;
               else
                    buffer++;
          }
          // open - close = cnt
          // open + close = buffer
          int open = (buffer + cnt) / 2;
          int close = (buffer - cnt) / 2;
          if (open == 0 || close == 0) {
               out.println("YES");
               return; 
          }
          StringBuilder check = new StringBuilder();
          for (int i = 0; i < open - 1; i++)
               check.append('(');
          check.append(')');
          check.append('(');
          for (int i = 0; i < close - 1; i++)
               check.append(')');
          int ptr = 0;
          char[] arr = s.toCharArray();
          for (int i = 0; i < arr.length; i++) {
               if (arr[i] == '?') {
                    arr[i] = check.charAt(ptr++);
               }
          }
          cnt = 0;
          for (char x : arr) {
               if (x == '(')
                    cnt++;
               else
                    cnt--;
               if (cnt < 0) {
                    out.println("YES");
                    return;
               }
          }
          out.println("NO");
     }

     static class FASTIO {
          BufferedReader br;
          StringTokenizer st;

          public FASTIO() {
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
     }
}
