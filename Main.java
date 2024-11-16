  import java.io.*;
  import java.util.*;

  public class Main {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();

    public static void main(String[] args) throws Exception {
      int cf = in.nextInt();
      while (cf-- > 0) {
        Accepted();
      }
      out.close();
    }

    private static void Accepted() {
      int n = in.nextInt();
      int k = in.nextInt();
      long[] a = new long[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextLong();
      }
      Arrays.sort(a);
      long ans1 = 0, ans2 = 0;
      for (int i = 0; i < k - 1; i++) {
        ans1 += a[i];
      }
      ans1 += a[n - k - 1];
      int t = 0;
      for (int i = n - 2; i >= 0; i -= 2) {
        if (t == k)
          break;
        ans2 += a[i];
        t++;
      }
      System.out.println(ans1 + " " + ans2);
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

      double nextDouble() {
        return Double.parseDouble(next());
      }

      String nextLine() {
        String str = "";
        try {
          str = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return str;
      }
    }
  }
