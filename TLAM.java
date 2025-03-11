import java.io.*;
import java.util.*;

public class TLAM {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    // private static final int MOD = (int) 1e9 + 7;
    private static final int MOD = 998244353;

    public static void main(String[] args) {
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

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instanceof Pair))
                return false;
            Pair pair = (Pair) obj;
            return pair.first == this.first && pair.second == this.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    private static long maxSubarraySum(long[] a, int left, int right) {
        long curr = 0, maxSum = 0;
        for (int i = left; i <= right; i++) {
            curr += a[i];
            maxSum = Math.max(maxSum, curr);
            if (curr < 0) {
                curr = 0;
            }
        }
        return maxSum;
    }

    private static long minSubarraySum(long[] a, int left, int right) {
        long curr = 0, maxSum = 0;
        for (int i = left; i <= right; i++) {
            curr -= a[i];
            maxSum = Math.max(maxSum, curr);
            if (curr < 0) {
                curr = 0;
            }
        }
        return -maxSum;
    }

    private static int lowerBound(long[] arr, long key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] >= key) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int lowerBound(long[] a, int start, int end, long val) {
        int lo = start, hi = end, res = end + 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= val) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    private static int upperBound(long[] a, int start, int end, long val) {
        int lo = start, hi = end, res = start - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] <= val) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    static long modDiv(long x, long y, long mod) {
        // x * y^(MOD-2) % MOD
        return (x * modPow(y, mod - 2, mod)) % mod;
    }

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    static long binpow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                res = res * a;
            a = a * a;
            b >>= 1;
        }
        return res;
    }

    static long binPowMod(long a, long b, long MOD) {
        long result = 1;
        a = a % MOD;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return result;
    }

    static void derangement() {
        int k = 4;
        int[] derangements = new int[k + 1];
        derangements[0] = 1; // D(0) = 1
        if (k > 0)
            derangements[1] = 0; // D(1) = 0
        for (int i = 2; i <= k; i++) {
            derangements[i] = (i - 1) * (derangements[i - 1] + derangements[i - 2]);
        }
    }

    private static void SPF() {
        int N = 100;
        int[] spf = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= N; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= N; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    private static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= (result / i);
            }
        }
        if (n > 1) {
            result -= (result / n);
        }
        return result;
    }

    private static void addAllPrimFact(int x, HashMap<Integer, Integer> map) {
        int i = 2;
        while (i * i <= x) {
            while (x % i == 0) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                x /= i;
            }
            i++;
        }
        if (x > 1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
    }

    static boolean[] isPrime;
    static ArrayList<Integer> primes;

    public static void sieve(int n) {
        isPrime = new boolean[n + 1];
        primes = new ArrayList<>();
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    public static List<Boolean> segmentedSieveNoPreGen(long L, long R) {
        int size = (int) (R - L + 1);
        List<Boolean> isPrime = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            isPrime.add(true);
        }
        long lim = (long) Math.sqrt(R);
        for (long i = 2; i <= lim; i++) {
            long start = Math.max(i * i, (L + i - 1) / i * i);
            for (long j = start; j <= R; j += i) {
                isPrime.set((int) (j - L), false);
            }
        }
        if (L == 1)
            isPrime.set(0, false);
        return isPrime;
    }

    // Find primes in range
    public static List<Boolean> segmentedSieve(long L, long R) {
        long lim = (long) Math.sqrt(R);
        boolean[] mark = new boolean[(int) (lim + 1)];
        List<Long> primes = new ArrayList<>();
        for (long i = 2; i <= lim; i++) {
            if (!mark[(int) i]) {
                primes.add(i);
                for (long j = i * i; j <= lim; j += i) {
                    mark[(int) j] = true;
                }
            }
        }
        List<Boolean> isPrime = new ArrayList<>();
        for (int i = 0; i <= R - L; i++) {
            isPrime.add(true);
        }
        for (long prime : primes) {
            long start = Math.max(prime * prime, (L + prime - 1) / prime * prime);
            for (long j = start; j <= R; j += prime) {
                isPrime.set((int) (j - L), false);
            }
        }
        if (L == 1) {
            isPrime.set(0, false);
        }
        return isPrime;
    }

    public static int countPrimes(int n) {
        final int S = 10000;
        int nsqrt = (int) Math.sqrt(n);
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[nsqrt + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= nsqrt; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = i * i; j <= nsqrt; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int result = 0;
        boolean[] block = new boolean[S];
        for (int k = 0; k * S <= n; k++) {
            Arrays.fill(block, true);
            int start = k * S;
            for (int p : primes) {
                int startIdx = Math.max((start + p - 1) / p, p);
                int j = startIdx * p - start;
                for (; j < S; j += p) {
                    block[j] = false;
                }
            }
            if (k == 0) {
                block[0] = block[1] = false;
            }
            for (int i = 0; i < S && start + i <= n; i++) {
                if (block[i]) {
                    result++;
                }
            }
        }
        return result;
    }

    int flipBit(int n, int j) {
        return n ^ (1 << j);
    }

    private static int computeXOR(int n) {
        if (n % 4 == 0)
            return n;
        if (n % 4 == 1)
            return 1;
        if (n % 4 == 2)
            return n + 1;
        return 0;
    }

    public static int findMSB(long n) {
        int msb = 0;
        while (n > 1) {
            n >>= 1;
            msb++;
        }
        return 1 << msb;
    }

    private static void reverse(int[] array, long left, long right) {
        while (left < right) {
            long temp = array[(int) left];
            array[(int) left] = array[(int) right];
            array[(int) right] = (int) temp;
            left++;
            right--;
        }
    }

    private static long rangeSum(long l, long r) {
        if (l > r) {
            return 0;
        }
        return (l + r) * (r - l + 1) / 2;
    }

    public static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public static void factor(long n) {
        long count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
                if (i != n / i) {
                    count++;
                }
            }
        }
    }

    private static void swap(long[] arr, long i, long j) {
        long t = arr[(int) i];
        arr[(int) i] = arr[(int) j];
        arr[(int) j] = t;
    }

    public static long numberOfDivisors(long num) {
        long total = 1;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                int e = 0;
                while (num % i == 0) {
                    e++;
                    num /= i;
                }
                total *= (e + 1);
            }
        }
        if (num > 1) {
            total *= 2;
        }
        return total;
    }

    public static long sumOfDivisors(long num) {
        long total = 1;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                int e = 0;
                while (num % i == 0) {
                    e++;
                    num /= i;
                }
                long sum = 0, pow = 1;
                while (e-- >= 0) {
                    sum += pow;
                    pow *= i;
                }
                total *= sum;
            }
        }
        if (num > 1) {
            total *= (1 + num);
        }
        return total;
    }

    public static long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    // long[] fact = new long[n + 2];
    // long[] ifact = new long[n + 2];
    // fact[0] = 1;
    // for (int i = 1; i <= n + 1; i++) {
    // fact[i] = (fact[i - 1] * i) % MOD;
    // }

    // ifact[n + 1] = binPow(fact[n + 1], MOD - 2, MOD);
    // for (int i = n; i >= 0; i--) {
    // ifact[i] = (ifact[i + 1] * (i + 1)) % MOD;
    // }

    static long combination(long n, long r, long[] fact, long[] ifact) {
        if (r > n || r < 0)
            return 0;
        return ((fact[(int) n] * ifact[(int) r]) % MOD * ifact[(int) (n - r)] % MOD) % MOD;
    }

    // // This is used when we use Pair inside the map
    // Map<Pair, Integer> map = new HashMap<>();

    // static class Pair {
    // long first, second;

    // Pair(long first, long second) {
    // this.first = first;
    // this.second = second;
    // }

    // @Override
    // public boolean equals(Object o) {
    // if (this == o)
    // return true;
    // if (o == null || getClass() != o.getClass())
    // return false;
    // Pair pair = (Pair) o;
    // return first == pair.first && second == pair.second;
    // }

    // @Override
    // public int hashCode() {
    // return (int) (31 * first + second);
    // }
    // }

    // public static boolean nextPermutation(char[] array) {
    // int n = array.length;
    // int i = n - 2;
    // while (i >= 0 && array[i] >= array[i + 1]) {
    // i--;
    // }
    // if (i < 0) {
    // return false;
    // }
    // int j = n - 1;
    // while (array[j] <= array[i]) {
    // j--;
    // }
    // swap2(array, i, j);
    // reverse2(array, i + 1, n - 1);
    // return true;
    // }

    private static void reverse2(char[] array, int start, int end) {
        while (start < end) {
            swap2(array, start, end);
            start++;
            end--;
        }
    }

    private static void swap2(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static long calculateDigitSum(int n) {
        // to calculate the sum (1 + 2 + .... )
        // (each digit it replaced by there sum of the digit).
        long sum = 0;
        int factor = 1;
        int leftOver = 0;
        while (n > 0) {
            int digit = n % 10;
            int higher = n / 10;
            sum += higher * factor * 45; // Sum of all digits from 0 to 9 is 45
            sum += digit * (digit - 1) / 2 * factor; // Sum of digits within the current group
            sum += digit * leftOver; // Adjust for digits already processed
            leftOver += digit * factor; // Update leftover for next digit position
            factor *= 10;
            n /= 10;
        }
        return sum;
    }
}
