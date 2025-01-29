import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import static java.lang.Math.ceilDiv;
import java.io.*;
import java.util.*;

public class Main {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    // static final int MOD = (int) 1e9 + 7;
    static final int MOD = 998244353;
    static List<List<Integer>> adj;

    public static void main(String[] Hi) {
        int T = in.nextInt();
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    // to minimize the produt of two no. the diff between two no. should be less

    static void solve() {
        String num1 = in.next();
        String num2 = in.next();
        int idx = -1;
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) != num2.charAt(i)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            out.println(num1);
            out.println(num2);
            return;
        }
        String ans1 = "";
        String ans2 = "";
        for (int i = 0; i <= idx; i++) {
            ans1 += num1.charAt(i);
            ans2 += num2.charAt(i);
        }
        boolean f1 = (num1.charAt(idx) > num2.charAt(idx));
        for (int i = idx + 1; i < num1.length(); i++) {
            char c1 = num1.charAt(i);
            char c2 = num2.charAt(i);
            if (f1) {
                if (c1 < c2) {
                    ans1 += c1;
                    ans2 += c2;
                } else {
                    ans1 += c2;
                    ans2 += c1;
                }
            } else {
                if (c1 > c2) {
                    ans1 += c1;
                    ans2 += c2;
                } else {
                    ans1 += c2;
                    ans2 += c1;
                }
            }
        }
        out.println(ans1);
        out.println(ans2);
    }

    /*-------------------------------------------------------------------------------------------------------------- */

    class Pair {
        int first, second;

        Pair(int f, int s) {
            first = f;
            second = s;
        }
    }

    static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    static void reverse(int[] a, int l, int r) {
        while (l < r) {
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++;
            r--;
        }
    }

    static void reverse(long[] a, int l, int r) {
        while (l < r) {
            long t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++;
            r--;
        }
    }

    static long modPow(long b, long e, long mod) {
        long r = 1;
        b = b % mod;
        while (e > 0) {
            if ((e & 1) == 1) {
                r = (r * b) % mod;
            }
            b = (b * b) % mod;
            e >>= 1;
        }
        return r;
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
    }

    static void sort(int[] a) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : a)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < a.length; i++)
            a[i] = ls.get(i);
    }

    static void print(int[][] arr) {
        for (int[] a : arr) {
            for (int i : a)
                out.print(i + " ");
            out.println();
        }
    }

    static void print(long[][] arr) {
        for (long[] a : arr) {
            for (long i : a)
                out.print(i + " ");
            out.println();
        }
    }

    static void print(int[] a) {
        for (int i : a)
            out.print(i + " ");
        out.println();
    }

    static void print(char[] a) {
        for (char i : a)
            out.print(i + " ");
        out.println();
    }

    static void print(long[] a) {
        for (long i : a)
            out.print(i + " ");
        out.println();
    }

    static <T extends Number> void print(ArrayList<T> ls) {
        for (T i : ls)
            out.print(i + " ");
        out.println();
    }

    static int[] inputIntArr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
        return a;
    }

    static long[] inputLongArr(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextLong();
        return a;
    }

    static int[][] inputIntArr(int n, int m) {
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = in.nextInt();
        return a;
    }

    static long[][] inputLongArr(int n, int m) {
        long[][] a = new long[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = in.nextLong();
        return a;
    }

    static ArrayList<Integer> inputIntList(int n) {
        ArrayList<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ls.add(in.nextInt());
        return ls;
    }

    static ArrayList<Long> inputLongList(int n) {
        ArrayList<Long> ls = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ls.add(in.nextLong());
        return ls;
    }
}
