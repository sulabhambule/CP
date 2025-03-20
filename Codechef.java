import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import java.io.*;
import java.util.*;

class Codechef {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FASTIO in = new FASTIO();
    static final int MOD = (int) 1e9 + 7;
    // static final int MOD = 998244353;

    public static void main(String[] Hi) throws java.lang.Exception {
        int cf = in.nextInt();
        while (cf-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {
        int n = in.nextInt();
        int[] a = inputIntArr(n);
        HashMap<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = a[i];
            map.putIfAbsent(num, new int[] { i, i });
            map.get(num)[1] = i;
        }

        List<int[]> seg = new ArrayList<>(map.values());
        for (int[] ind : map.values()) {
            if (ind[0] != -1 && ind[1] != -1) {
                seg.add(ind);
                // println(ind[0] + " " + ind[1]);
            }
        }
        int ans = fun(seg);
        println(ans);
    }

    public static int fun(List<int[]> intervals) {
        intervals.sort((a, b) -> (a[0] == b[0]) ? (b[1] - a[1]) : (a[0] - b[0]));
        int prevRight = -1;
        int count = 0;
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (r <= prevRight) {
                continue;
            }
            count++;
            prevRight = r;
        }

        return count;
    }

    static class SegmentTree {
        int[] tree;
        int[] lazy;
        int n;

        SegmentTree(int size) {
            n = size;
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }

        void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = 1; // Each index initially contributes 1 distinct segment
            } else {
                int mid = (start + end) / 2;
                build(2 * node, start, mid);
                build(2 * node + 1, mid + 1, end);
                tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }

        void propagate(int node, int start, int end) {
            if (lazy[node] == 1) {
                tree[node] = 0; // Merge all elements in range
                if (start != end) {
                    lazy[2 * node] = 1;
                    lazy[2 * node + 1] = 1;
                }
                lazy[node] = 0;
            }
        }

        void update(int node, int start, int end, int l, int r) {
            propagate(node, start, end);
            if (start > r || end < l)
                return; // Out of range
            if (start >= l && end <= r) {
                lazy[node] = 1;
                propagate(node, start, end);
                return;
            }
            int mid = (start + end) / 2;
            update(2 * node, start, mid, l, r);
            update(2 * node + 1, mid + 1, end, l, r);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        int query(int node, int start, int end, int l, int r) {
            propagate(node, start, end);
            if (start > r || end < l)
                return 0;
            if (start >= l && end <= r)
                return tree[node];
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, l, r) + query(2 * node + 1, mid + 1, end, l, r);
        }
    }

    public static int minDistinctElements(int[] A) {
        int n = A.length;
        Map<Integer, Integer> leftMost = new HashMap<>();
        Map<Integer, Integer> rightMost = new HashMap<>();

        for (int i = 0; i < n; i++) {
            rightMost.put(A[i], i);
            leftMost.putIfAbsent(A[i], i);
        }

        List<int[]> segments = new ArrayList<>();
        for (int key : leftMost.keySet()) {
            segments.add(new int[] { leftMost.get(key), rightMost.get(key) });
        }

        // Sort by left endpoint
        segments.sort(Comparator.comparingInt(a -> a[0]));

        SegmentTree segTree = new SegmentTree(n);
        segTree.build(1, 0, n - 1);

        for (int[] seg : segments) {
            segTree.update(1, 0, n - 1, seg[0], seg[1]);
        }

        return segTree.query(1, 0, n - 1, 0, n - 1);

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

    static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static long lcm(long a, long b) {
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

    public static long mahantaDist(long x1, long y1, long x2, long y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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