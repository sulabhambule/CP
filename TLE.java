
// Author : Sulabh Ambule

import java.io.*;
import java.util.*;
import java.lang.*;

public class TLE {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static long MOD = (long) (1e9 + 7);
  // static long MOD = 998244353;
  static FastReader in = new FastReader();

  public static void main(String[] args) throws Exception {
    // int cases = in.nextInt();
    // while (cases-- > 0) {
    Accepted();
    // }

    out.flush();
    out.close();
  }

  /*
   * 
   * 
   * || JAI SHREE RAM ||
   * 
   * 
   */

  private static void Accepted() {
    int n = in.nextInt();
    int m = in.nextInt();

    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt();
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

  // ---------------------------------------------------

  // static class Pair {
  // long first;
  // long second;
  // Pair(long f, long s) {
  // this.first = f;
  // this.second = s;
  // }
  // }
  // ----------------------------------------------

  // BINARY SEARCH TIP
  // Collections.binarySearch(list, a[i])
  // If the a[i] value not found in the list then it return
  // negative value --> { -(insertion point) - 1 }
  // long idx = Arrays.binarySearch(b, stu);
  // if (idx < 0) {
  // idx = -(idx + 1);
  // Adjust for the negative index from binarySearch
  // }

  // if x = 10, then PM are-> 2, 5 added to map.
  // private static void addAllPrimFact(int x, HashMap<Integer, Integer> map) {
  // int i = 2;
  // while (i * i <= x) {
  // while (x % i == 0) {
  // map.put(i, map.getOrDefault(i, 0) + 1);
  // x /= i;
  // }
  // i++;
  // }
  // if (x > 1) {
  // map.put(x, map.getOrDefault(x, 0) + 1);
  // }
  // }
  // ---------------------------------------------

  // private static int lowerBound(int num, int[] arr) {
  // int n = arr.length;
  // int low = 0, high = n - 1;
  // long ans = -1;
  // while (low <= high) {
  // int mid = (low + high) / 2;
  // if (arr[mid] <= num) {
  // ans = mid;
  // low = mid + 1;
  // } else {
  // high = mid - 1;
  // }
  // }
  // return (int) ans;
  // }

  // ------------------------------------------

  // public static long factorial(long minOp) {
  // long fact = 1;
  // for (int i = 2; i <= minOp; i++) {
  // fact = (fact * i) % MOD;
  // }
  // return fact;
  // }
  // --------------------------------------------

  // to check in arr[i] the j-th bit set or not.
  // if((arr[i] & (1 << j)) != 0) {
  // count++; this means the jth bit is set.increase count
  // }
  // int bit = (num >> i) & 1; // extract the i-th bit
  // int flipBit(int n, int j) {
  // return n ^ (1 << j);
  // }

  // ----------------------------------------

  // note: if we add 2^(x-1) to num then num will not divisibe by that x again.

  // mex calculate for the arr of permutation
  // long mex = (n * (n + 1) / 2) - sum;
  // ---------------------------------------------

  // private static int computeXOR(int n) {
  // if (n % 4 == 0) return n;
  // if (n % 4 == 1) return 1;
  // if (n % 4 == 2) return n + 1;
  // return 0;
  // }
  // ---------------------------------------------

  // public static int findMSB(long n) {
  // int msb = 0;
  // while (n > 1) {
  // n >>= 1;
  // msb++;
  // }
  // return 1 << msb;
  // }
  // -----------------------

  // private static void reverse(int[] array, long left, long right) {
  // while (left < right) {
  // long temp = array[(int) left];
  // array[(int) left] = array[(int) right];
  // array[(int) right] = (int) temp;
  // left++;
  // right--;
  // }
  // }
  // ------------------------------------------------

  // private static long rangeSum(long l, long r) {
  // if (l > r) {
  // return 0;
  // }
  // return (l + r) * (r - l + 1) / 2;
  // }
  // --------------------------------------------------

  // public static long gcd(long a, long b) {
  // if (a == 0)
  // return b;
  // return gcd(b % a, a);
  // }
  // --------------------------------------------------

  // public static void factor(long n) {
  // long count = 0;
  // for(int i = 1; i * i <= n; i++) {
  // if(n % i == 0) {
  // // i -> is the one factor
  // count++;
  // if(i != n / i) {
  // // n / i -> is the other factor
  // count++;
  // }
  // }
  // }
  // }

  // private static int getPrime(int n) {
  // while(n % 2 == 0) return 2;
  // for(int i = 3; i <= Math.sqrt(n); i += 2) {
  // while (n % i == 0)return i;
  // }
  // if(n > 2) return n;
  // return n;
  // }
  // ---------------------------------------------------

  // public static boolean isPrime(long arr) {
  // if (arr <= 1)
  // return false;
  // for (int i = 2; i <= Math.sqrt(arr); i++) {
  // if (arr[i] % i == 0)
  // return false;
  // }
  // return true;
  // }
  // ---------------------------------------------------

  // private static void swap(long[] arr, long i, long j) {
  // long t = arr[(int) i];
  // arr[(int) i] = arr[(int) j];
  // arr[(int) j] = t;
  // }
  // ----------------------------------------------------

  // public static long lcm(long a, long b) {
  // return Math.abs(a * b) / gcd(a, b);
  // }
  // ----------------------------------------------------

  // static class Pair implements Comparable<Pair> {
  // long first;
  // long second;

  // Pair(long first, long x) {
  // this.first = first;
  // this.second = x;
  // }

  // @Override
  // public int compareTo(Pair other) {
  // return Long.compare(this.first, other.first);
  // }
  // }

  // ------------------------------------------------
  // Removing leading zeros from the StringBuilder
  // String xStr = x.toString().replaceFirst("^0+(?!$)", "");\
}