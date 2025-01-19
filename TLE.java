import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import java.io.*;
import java.util.*;

public class TLE {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    // static final int MOD = (int) 1e9 + 7;
    static final int MOD = 998244353;

    /*
     * @Sulabh Ambule
     * - Why my rating is not Increasing Codeforces :(
     * - Let's Hope for Best Today !
     */
    public static void main(String[] Hi) {
        int T = in.nextInt();
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {

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

    static void sort(int[] arr) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for (int x : arr)
            ls.add(x);
        Collections.sort(ls);
        for (int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
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

    static void print(int[] arr) {
        for (int i : arr)
            out.print(i + " ");
        out.println();
    }

    static void print(long[] arr) {
        for (long i : arr)
            out.print(i + " ");
        out.println();
    }

    static <T extends Number> void print(ArrayList<T> ls) {
        for (T i : ls)
            System.out.print(i + " ");
        System.out.println();
    }

    static int[] inputIntArr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
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

    static long[] inputLongArr(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextLong();
        return a;
    }

}
