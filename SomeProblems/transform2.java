package SomeProblems;
// Author : Sulabh Ambule

import java.util.*;

public class transform2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // int cases = in.nextInt();
        // while (cases-- > 0) {
        Accepted(in);
        // }
        in.close();
    }

    // JAI SHREE RAM

    private static void Accepted(Scanner in) {
        int n = in.nextInt();
        int k = in.nextInt();
        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextDouble();
            b[i] = in.nextDouble();
        }
        double low = 0, high = 1e10;
        int iterations = 60;

        for (int i = 0; i < iterations; i++) {
            double mid = (low + high) / 2;
            if (isPossible(mid, a, b, n, k)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        System.out.println(low);
    }

    private static boolean isPossible(double mid, double[] a, double[] b, int n, int k) {
        double[] transform = new double[n];
        for (int i = 0; i < a.length; i++) {
            transform[i] = a[i] - b[i] * mid;
        }
        Arrays.sort(transform);
        double sum = 0;
        for (int i = n - k; i < n; i++) {
            sum += transform[i];
        }
        return sum >= 0;
    }

    // ---------------------------------------------------
    private static final long MOD = (long) (1e9 + 7);

    // static class Pair {
    // int first;
    // int second;

    // Pair(int f, int s) {
    // this.first = f;
    // this.second = s;
    // }
    // }
    // ----------------------------------------------

    // BINARY SEARCH TIP
    // Collections.binarySearch(list, a[i])
    // - if the a[i] exits in list then it return the index of a[i].
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
    // -----------------------------------------------

    // private static int lowerBound(int num, int[] arr) {
    // int n = arr.length;
    // int low = 0;
    // int high = n - 1;
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

    // ----------------------------------------------

    // public static long factorial(long minOp) {
    // long fact = 1;
    // for (int i = 2; i <= minOp; i++) {
    // fact = (fact * i) % MOD;
    // }
    // return fact;
    // }
    // -------------------------------------------------

    // to check in arr[i] the j-th bit set or not.
    // if((arr[i] & (1 << j)) != 0) {
    // count++; this means the j-th bit is set.
    // }
    // ----------------------------------------

    // note: when want to find large 'x' term of division walla things Go for GCD.
    // find any pattern for whom to find gcd.

    // note: if we add 2^(x-1) to num then num will not divisibe by that x again.

    // mex calculate for the arr of permutation
    // long mex = (n * (n + 1) / 2) - sum;
    // ----------------------------------------------

    // PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.first - x.first);
    // ---------------------------------------------------

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

    // private static void reverse(long[] arr, long left, long right) {
    // while (left < right) {
    // long temp = arr[(int) left];
    // arr[(int) left] = arr[(int) right];
    // arr[(int) right] = temp;
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
    // ----------------------------------------------------

    // private static int getPrime(int n) {
    // while(n % 2 == 0) return 2;
    // for(int i = 3; i <= Math.sqrt(n); i += 2) {
    // while (n % i == 0)return i;
    // }
    // if(n > 2) return n;
    // return n;
    // }
    // -----------------------------------------------------

    // public static boolean isPrime(long arr) {
    // if (arr <= 1)
    // return false;
    // for (int i = 2; i <= Math.sqrt(arr); i++) {
    // if (arr[i] % i == 0)
    // return false;
    // }
    // return true;
    // }
    // -----------------------------------------------------

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

    // Arrays.sort(ord, (i, j) -> Integer.compare(a[j], a[i]));
    // Arrays.sort(a, Comparator.comparingInt(p -> p.first));
    // ----------------------------------------------------

    static class Pair implements Comparable<Pair> {
        long first;
        long second;

        Pair(long first, long x) {
            this.first = first;
            this.second = x;
        }

        @Override
        public int compareTo(Pair other) {
            return Long.compare(this.first, other.first);
        }
    }

    // ---------------------------------------------------
    // This is used when we use Pair inside the map

    // Map<Pair, Integer> map = new HashMap<>();
    // static class Pair {
    // long first, second;
    // Pair(long first, long second) {
    // this.first = first;
    // this.second = second;
    // }
    // @Override
    // public boolean equals(Object o) {
    // if (this == o) return true;
    // if (o == null || getClass() != o.getClass()) return false;
    // Pair pair = (Pair) o;
    // return first == pair.first && second == pair.second;
    // }
    // @Override
    // public int hashCode() {
    // return (int) (31 * first + second);
    // }
    // }

    // ------------------------------------------------
    // Removing leading zeros from the StringBuilder
    // String xStr = x.toString().replaceFirst("^0+(?!$)", "");

}
