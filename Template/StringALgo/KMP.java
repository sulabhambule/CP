
import java.io.*;
import java.util.*;

// when there is mismatch happened string index is i and pattern index is j then we ask from 0 to j - 1 what length of longest prefix mathches with the suffix of the pattern. ex s = abababd , pattern = ababd so when mismatch happend at d then instead of starting form the start we see ab and ab is the longest prefix suffix match so we start with lenght j - 2

// so LPS[j] = (0 to j) longest prefix suffix equal length

// note : the border mean the string which is the prefix also the suffix , so the perios is n - border 
// if border = x then s[0 … x-1] == s[n-x … n-1]
// A period k means: for all i ≥ k → s[i] = s[i - k]

public class KMP {
  static final long INF = Long.MAX_VALUE / 2;
  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static int mod = (int) 1e9 + 7;
  // static int mod = 998244353;

  public static void main(String[] args) throws IOException {
    int t = 1;
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    String s = in.next(), pattern = in.next();
    // first we need to calculate the LPS for pattern
    int m = pattern.length(), n = s.length();
    int[] LPS = new int[m];
    LPS[0] = 0;
    int i = 1;
    int len = 0;
    while (i < m) {
      if (pattern.charAt(i) == pattern.charAt(len)) {
        len++;
        LPS[i] = len;
        i++;
      } else {
        if (len != 0) {
          len = LPS[len - 1];
        } else {
          LPS[i] = 0;
          i++;
        }
      }
    }

    // now we run KMP
    List<int[]> occ = new ArrayList<>();
    int ans = 0;

    i = 0;
    int j = 0;
    while (i < n) {
      if (s.charAt(i) == pattern.charAt(j)) {
        i++;
        j++;
      }

      if (j == m) {
        ans++;
        // the pattern has ended so add this
        int startIndex = i - j; // 0 based
        int start = i - m, end = i - 1;
        occ.add(new int[] { start, end });
        j = LPS[j - 1];
      } else if (i < n && s.charAt(i) != pattern.charAt(j)) {
        if (j != 0) {
          j = LPS[j - 1];
        } else {
          // j is 0 means is new start then i++;
          i++;
        }
      }
    }

    out.println(ans);
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
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
  }
}
