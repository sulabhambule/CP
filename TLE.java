
  import java.io.*;
  import java.util.*;

  // Author : Sulabh Ambule
  public class TLE {

    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static long MOD = (long) (1e9 + 7);
    // static long MOD = 998244353;
    static FastReader in = new FastReader();

    public static void main(String[] args) throws Exception {
      // int cf = in.nextInt();
      // while (cf-- > 0) {
      Accepted();
      // }
      out.flush();
      out.close();
    }
    /*
    * 
    * 
    * || जय श्री राम ||
    * 
    * 
    */
    private static void Accepted() {
      int n = in.nextInt();
      long k = in.nextLong();
      int[][] arr = new int[n][3];
      for (int i = 0; i < n; i++) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        arr[i][0] = a;
        arr[i][1] = b;
        arr[i][2] = c;
      }
      int low = 0;
      int high = (int) 1e6;
      int ans = 0;

      while (low <= high) {
        int mid = (low + high) / 2;
        if (isPossible(mid, arr, k)) {
          ans = mid;
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }

      out.println(ans);
    }

    private static boolean isPossible(int mid, int[][] arr, long k) {
      // Store {cost to receive (c), number of batteries needed}
      PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));

      for (int i = 0; i < arr.length; i++) {
        if (arr[i][0] < mid) {
          pq.offer(new Pair(arr[i][2], mid - arr[i][0]));
        }
      }

      long total = 0;
      for (int i = 0; i < arr.length; i++) {
        if (arr[i][0] > mid) {
          long req = arr[i][0] - mid;
          total += (req * arr[i][1]);
          while (!pq.isEmpty() && req > 0) {
            Pair pile = pq.poll();
            if (req >= pile.batNeed) {
              req -= pile.batNeed;
              total += pile.cost * pile.batNeed;
            } else {
              total += pile.cost * req;
              pile.batNeed -= req;
              pq.offer(pile);
              req = 0;
            }
            if (total > k) {
              return false;
            }
          }
          if (req > 0 || total > k) {
            return false;
          }
        }
      }

      return true;
    }

    static class Pair {
      long cost; 
      long batNeed; 

      Pair(long cost, long batNeed) {
        this.cost = cost;
        this.batNeed = batNeed;
      }
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

    // static class Pair {
    // long first;
    // long second
    // Pair(long f, long s) {
    // this.first = f;
    // this.second = s;
    // }
    // }

    // static class Pair implements Comparable<Pair> {
    //   long first;
    //   long second;

    //   Pair(long first, long x) {
    //     this.first = first;
    //     this.second = x;
    //   }

    //   @Override
    //   public int compareTo(Pair other) {
    //     return Long.compare(this.second, other.second);
    //   }
    // }

  }
