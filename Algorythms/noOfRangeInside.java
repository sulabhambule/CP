
import java.util.*;
import java.io.*;

// Another way of doing the same algo by using the treeset and inbuild functions

public class noOfRangeInside {

  static FastReader in;
  static PrintWriter out;
  static int MOD = (int) 1e9 + 7;

  static void solve() {
    int n = in.nextInt();
    List<Pair> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int second = in.nextInt();
      int first = in.nextInt();
      arr.add(new Pair(first, second));
    }
    Collections.sort(arr);
    long ans = 0;
    List<Integer> ls = new ArrayList<>();
    for (Pair p : arr) {
      int count = BS(ls, p.second);
      ans += count;
      int insertIndex = binarySearchInsert(ls, p.second);
      ls.add(insertIndex, p.second);
    }
    out.println(ans);
  }

  static int BS(List<Integer> ls, int key) {
    if (ls.isEmpty())
      return 0;
    int low = 0, high = ls.size() - 1;
    int ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (ls.get(mid) > key) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return (ans == -1) ? 0 : (ls.size() - ans);
  }

  // Binary search to find correct insertion index
  static int binarySearchInsert(List<Integer> ls, int key) {
    int low = 0, high = ls.size();
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (ls.get(mid) >= key) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
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

class Pair implements Comparable<Pair> {
  int first, second;

  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public int compareTo(Pair o) {
    return Integer.compare(this.first, o.first);
  }
}
