import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {

  }

  static class FastScanner {
    private final BufferedReader br;
    private StringTokenizer st;

    FastScanner(InputStream is) {
      br = new BufferedReader(new InputStreamReader(is));
    }

    String next() throws IOException {
      while (st == null || !st.hasMoreElements()) {
        String line = br.readLine();
        if (line == null)
          return null;
        st = new StringTokenizer(line);
      }
      return st.nextToken();
    }

    int nextInt() throws IOException {
      return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
      return Long.parseLong(next());
    }
  }
}
