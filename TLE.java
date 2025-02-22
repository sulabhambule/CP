import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import java.io.*;
import java.util.*;

/* Code Author : Sulabh Ambule */

public class TLE {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static final int MOD = (int) 1e9 + 7;
    // static final int MOD = 998244353;
    static final int inf = (int) 1e9;

    public static void main(String[] Hi) throws IOException {
        int cp = in.nextInt();
        while (cp-- > 0) {
            solve();
        }
        out.close();
    }

    /*
     * :)
     * Read the question again (Carefuly)
     * Integer overFlow
     */

    static void solve() {
        int n = in.nextInt();

    }

    /*-----------------------------------------------------------------------------------------------------------------------*/
    static class Pair implements Comparable<Pair> {
        long first;
        int second;

        Pair(long f, int s) {
            this.first = f;
            this.second = s;
        }

        @Override
        public int compareTo(Pair other) {
            return (int) (this.first - other.first);
        }
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

    }

    /*----------------------------------------------------------------------------------------------------------- */

    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    static boolean isPrime(long arr) {
        if (arr <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(arr); i++) {
            if (arr % i == 0)
                return false;
        }
        return true;
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

    public static long MahantaDist(long x1, long y1, long x2, long y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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

    static int maxA(int[] arr) {
        int m = Integer.MIN_VALUE;
        for (int i : arr)
            m = max(m, i);
        return m;
    }

    static int minA(int[] arr) {
        int m = Integer.MAX_VALUE;
        for (int i : arr)
            m = min(m, i);
        return m;
    }

    static int sum(int[] a) {
        int s = 0;
        for (int i : a)
            s += i;
        return s;
    }

    static void sort_(int[] a) {
        ArrayList<Integer> ls = new ArrayList<>();
        for (int x : a)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < a.length; i++)
            a[i] = ls.get(i);
    }

    static void sortRev(int[] a) {
        ArrayList<Integer> ls = new ArrayList<>();
        for (int x : a)
            ls.add(x);
        Collections.sort(ls, Collections.reverseOrder());
        for (int i = 0; i < a.length; i++)
            a[i] = ls.get(i);
    }

    static void sortRev(long[] a) {
        ArrayList<Long> ls = new ArrayList<>();
        for (long x : a)
            ls.add(x);
        Collections.sort(ls, Collections.reverseOrder());
        for (int i = 0; i < a.length; i++)
            a[i] = ls.get(i);
    }

    static <T extends Comparable<T>> void sort_(ArrayList<T> list) {
        Collections.sort(list);
    }

    static <T extends Comparable<T>> void sortRev(ArrayList<T> list) {
        Collections.sort(list, Collections.reverseOrder());
    }

    static void sort_(long[] a) {
        ArrayList<Long> ls = new ArrayList<>();
        for (long x : a)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < a.length; i++)
            a[i] = ls.get(i);
    }

    static void yes() {
        out.println("YES");
    }

    static void no() {
        out.println("NO");
    }

    static void print(int x) {
        out.print(x);
    }

    static void print(String s) {
        out.print(s);
    }

    static void println(String s) {
        out.println(s);
    }

    static void println(int x) {
        out.println(x);
    }

    static void print(long x) {
        out.print(x);
    }

    static void println(long x) {
        out.println(x);
    }

    static void print(char x) {
        out.print(x);
    }

    static void println(char x) {
        out.println(x);
    }

    static void print(int[][] arr) {
        for (int[] a : arr) {
            for (int i : a)
                out.print(i + " ");
            out.println();
        }
        out.println();
    }

    static void print(long[][] arr) {
        for (long[] a : arr) {
            for (long i : a)
                out.print(i + " ");
            out.println();
        }
        out.println();
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