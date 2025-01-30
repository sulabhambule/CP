import java.io.*;
import java.lang.*;
import java.util.*;

class Codechef {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();

    static long MOD = (long) 1e9 + 7;

    public static void main(String[] args) throws java.lang.Exception {
        int cf = in.nextInt();
        while (cf-- > 0) {
            Accepted();
        }
        // out.flush();
        out.close();
    }

    private static void Accepted() {
        int n = in.nextInt();
        long k = in.nextLong();
        long[] a = inputLongArr(n);

        long ans = 1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            for (int i = 0; i < n; i++) {
                ans = (ans * k) % MOD;
                k--;
            }
            out.println(ans);
            return;
        }
        int count = 0;
        for (long i : a) {
            if (i == 0) {
                count++;
            }
        }
        if (n % 2 == 0) {
            boolean f = true;
            for (int i = 0; i <= n / 2; i++) {
                if (a[i] != a[n - 1 - i]) {
                    f = false;
                }
            }

            if (f) {
                long k1 = k;
                for (int i = 0; i < n; i++) {
                    if (a[i] == 0) {
                        ans = (ans * k1) % MOD;
                        k1--;
                    }
                }
                out.println(ans);
                return;
            }
            long k1 = k;
            while (count-- > 0) {
                ans = (ans * k1) % MOD;
            }
            out.println(ans);
            return;

        } else {
            boolean f = true;
            for (int i = 0; i < n / 2; i++) {
                if (a[i] != a[n - i - 1]) {
                    f = false;
                    break;
                }
            }

            if (f) {
                long k1 = k;
                for (int i = 0; i < n; i++) {
                    if (a[i] == 0) {
                        ans = (ans * k1) % MOD;
                        k1--;
                    }
                }
                out.println(ans);
                return;
            } else {
                long k1 = k;
                while (count-- > 0) {
                    ans = (ans * k1) % MOD;
                }
                out.println(ans);
                return;
            }
        }
    }

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
