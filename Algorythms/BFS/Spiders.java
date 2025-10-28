
import java.io.*;
import java.util.*;

// CF link : https://codeforces.com/contest/1775/problem/D

public class Spiders {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int MOD = 998244353;
  static final int mod = (int) 1e9 + 7;
  static final int inf = (int) 1e9;
  static final long INF = (long) 1e18;

  static List<List<Integer>> adj;

  public static void main(String[] args) {
    int t = 1;
    while (t-- > 0)
      solve();
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    int[] a = new int[n + 1];
    int max = 0;
    for (int i = 1; i <= n; i++) {
      a[i] = in.nextInt();
      max = Math.max(max, a[i]);
    }
    int s = in.nextInt(), t = in.nextInt();
    if (s == t) {
      out.println(1);
      out.println(s);
      out.flush();
      return;
    }
    int[] spf = new int[max + 1];
    for (int i = 0; i <= max; i++)
      spf[i] = i;
    for (int i = 2; i * i <= max; i++) {
      if (spf[i] == i) {
        // i is the prime num
        for (int j = i * i; j <= max; j += i) {
          if (spf[j] == j)
            spf[j] = i;
        }
      }
    }

    // Build bipartite graph: number nodes 1..n, prime nodes n+1..n+maxVal
    int totalNode = n + max + 1;
    adj = new ArrayList<>();
    for (int i = 0; i < totalNode; i++)
      adj.add(new ArrayList<>());

    for (int i = 1; i <= n; i++) {
      if (a[i] == 1)
        continue;
      List<Integer> ls = getPrimeFactors(a[i], spf);
      for (int p : ls) {
        adj.get(i).add(n + p);
        adj.get(n + p).add(i);
      }
    }

    // BFS
    Queue<Integer> queue = new LinkedList<>();
    int[] parent = new int[totalNode];
    int[] dist = new int[totalNode];
    Arrays.fill(dist, -1);

    queue.offer(s);
    dist[s] = 0;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      if (node == t)
        break;
      for (int adjNode : adj.get(node)) {
        if (dist[adjNode] == -1) {
          dist[adjNode] = dist[node] + 1;
          parent[adjNode] = node;
          queue.offer(adjNode);
        }
      }
    }

    if (dist[t] == -1) {
      out.println(-1);
      return;
    }
    List<Integer> path = new ArrayList<>();
    int currNode = t;
    while (currNode != 0) { // when the parent[s] == 0 the while loop breaks.
      if (currNode <= n)
        path.add(currNode);
      currNode = parent[currNode];
    }
    Collections.reverse(path);
    out.println(path.size());
    for (int i : path) {
      out.print(i + " ");
    }
    out.println();
  }

  static List<Integer> getPrimeFactors(int num, int[] spfArr) {
    List<Integer> factors = new ArrayList<>();
    while (num > 1) {
      int p = spfArr[num];
      factors.add(p);
      while (num % p == 0)
        num /= p;
    }
    return factors;
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

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        st = null;
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }
}
