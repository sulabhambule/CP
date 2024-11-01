
public class Utility {

  public static void main(String[] args) {

  }

  /*
   * 
   * 
   * BINARY SEARCH TIP
   * Collections.binarySearch(list, a[i])
   * If the a[i] value not found in the list then it return
   * negative value --> { -(insertion point) - 1 }
   * long idx = Arrays.binarySearch(b, stu);
   * if (idx < 0) {
   * idx = -(idx + 1);
   * Adjust for the negative index from binarySearch
   * }
   * 
   * for calculating the mex
   * int[] leftMex = new int[n];
   * boolean[] present = new boolean[n + 1];
   * int currentMex = 0;
   * for (int i = 0; i < n; i++) {
   * present[a[i]] = true;
   * while (currentMex <= n && present[currentMex]) {
   * currentMex++;
   * }
   * leftMex[i] = currentMex;
   * }
   * 
   * 
   * private static int lowerBound(long[] arr, long key) {
   * int lo = 0, hi = arr.length;
   * while (lo < hi) {
   * int mid = lo + (hi - lo) / 2;
   * if (arr[mid] >= key) {
   * hi = mid;
   * } else {
   * lo = mid + 1;
   * }
   * }
   * return lo;
   * }
   * 
   * Binary search for the first index where x >= val
   * private static int lowerBound(long[] a, int start, int end, long val) {
   * int lo = start, hi = end, res = end + 1;
   * while (lo <= hi) {
   * int mid = lo + (hi - lo) / 2;
   * if (a[mid] >= val) {
   * res = mid;
   * hi = mid - 1;
   * } else {
   * lo = mid + 1;
   * }
   * }
   * return res;
   * }
   * 
   * Binary search for the last index where x <= val
   * private static int upperBound(long[] a, int start, int end, long val) {
   * int lo = start, hi = end, res = start - 1;
   * while (lo <= hi) {
   * int mid = lo + (hi - lo) / 2;
   * if (a[mid] <= val) {
   * res = mid;
   * lo = mid + 1;
   * } else {
   * hi = mid - 1;
   * }
   * }
   * return res;
   * }
   * 
   * 
   * long long binpow(long long a, long long b) {
   * long long res = 1;
   * while (b > 0) {
   * if (b & 1)
   * res = res * a;
   * a = a * a;
   * b >>= 1;
   * }
   * return res;
   * }
   * 
   * static long binPowMod(long a, long b, long MOD) {
   * long result = 1;
   * a = a % MOD; // in case 'a' is larger than MOD
   * 
   * while (b > 0) {
   * if ((b & 1) == 1) {
   * result = (result * a) % MOD;
   * }
   * a = (a * a) % MOD;
   * b >>= 1;
   * }
   * return result;
   * }
   * 
   * 
   * private static int phi(int n) {
   * int result = n;
   * for (int i = 2; i * i <= n; i++) {
   * if (n % i == 0) {
   * while (n % i == 0) {
   * n /= i;
   * }
   * result -= (result / i);
   * }
   * }
   * if (n > 1) {
   * result -= (result / n);
   * }
   * return result;
   * }
   * 
   * 
   * if x = 10, then PM are-> 2, 5 added to map.
   * private static void addAllPrimFact(int x, HashMap<Integer, Integer> map) {
   * int i = 2;
   * while (i * i <= x) {
   * while (x % i == 0) {
   * map.put(i, map.getOrDefault(i, 0) + 1);
   * x /= i;
   * }
   * i++;
   * }
   * if (x > 1) {
   * map.put(x, map.getOrDefault(x, 0) + 1);
   * }
   * }
   * 
   * public static long factorial(long minOp) {
   * long fact = 1;
   * for (int i = 2; i <= minOp; i++) {
   * fact = (fact * i) % MOD;
   * }
   * return fact;
   * }
   * 
   * 
   * to check in arr[i] the j-th bit set or not.
   * if((arr[i] & (1 << j)) != 0) {
   * count++; this means the jth bit is set.increase count
   * }
   * int bit = (num >> i) & 1; // extract the i-th bit
   * int flipBit(int n, int j) {
   * return n ^ (1 << j);
   * }
   * 
   * // note: if we add 2^(x-1) to num then num will not divisibe by that x again.
   * 
   * mex calculate for the arr of permutation
   * long mex = (n * (n + 1) / 2) - sum;
   * 
   * 
   * private static int computeXOR(int n) {
   * if (n % 4 == 0) return n;
   * if (n % 4 == 1) return 1;
   * if (n % 4 == 2) return n + 1;
   * return 0;
   * }
   * 
   * public static int findMSB(long n) {
   * int msb = 0;
   * while (n > 1) {
   * n >>= 1;
   * msb++;
   * }
   * return 1 << msb;
   * }
   * 
   * private static void reverse(int[] array, long left, long right) {
   * while (left < right) {
   * long temp = array[(int) left];
   * array[(int) left] = array[(int) right];
   * array[(int) right] = (int) temp;
   * left++;
   * right--;
   * }
   * }
   * 
   * private static long rangeSum(long l, long r) {
   * if (l > r) {
   * return 0;
   * }
   * return (l + r) * (r - l + 1) / 2;
   * }
   * 
   * public static long gcd(long a, long b) {
   * if (a == 0)
   * return b;
   * return gcd(b % a, a);
   * }
   * 
   * public static void factor(long n) {
   * long count = 0;
   * for(int i = 1; i * i <= n; i++) {
   * if(n % i == 0) {
   * // i -> is the one factor
   * count++;
   * if(i != n / i) {
   * // n / i -> is the other factor
   * count++;
   * }
   * }
   * }
   * }
   * 
   * 
   * private static int getPrime(int n) {
   * while(n % 2 == 0) return 2;
   * for(int i = 3; i <= Math.sqrt(n); i += 2) {
   * while (n % i == 0)return i;
   * }
   * if(n > 2) return n;
   * return n;
   * }
   * 
   * 
   * public static boolean isPrime(long arr) {
   * if (arr <= 1)
   * return false;
   * for (int i = 2; i <= Math.sqrt(arr); i++) {
   * if (arr[i] % i == 0)
   * return false;
   * }
   * return true;
   * }
   * 
   * 
   * private static void swap(long[] arr, long i, long j) {
   * long t = arr[(int) i];
   * arr[(int) i] = arr[(int) j];
   * arr[(int) j] = t;
   * }
   * 
   * public static long lcm(long a, long b) {
   * return Math.abs(a * b) / gcd(a, b);
   * }
   * 
   * // This is used when we use Pair inside the map
   * 
   * Map<Pair, Integer> map = new HashMap<>();
   * static class Pair {
   * long first, second;
   * Pair(long first, long second) {
   * this.first = first;
   * this.second = second;
   * }
   * 
   * @Override
   * public boolean equals(Object o) {
   * if (this == o) return true;
   * if (o == null || getClass() != o.getClass()) return false;
   * Pair pair = (Pair) o;
   * return first == pair.first && second == pair.second;
   * }
   * 
   * @Override
   * public int hashCode() {
   * return (int) (31 * first + second);
   * }
   * }
   * 
   * // Removing leading zeros from the StringBuilder
   * // String xStr = x.toString().replaceFirst("^0+(?!$)", "");
   * 
   * // Method to generate the next lexicographical permutation
   * public static boolean nextPermutation(char[] array) {
   * int n = array.length;
   * int i = n - 2;
   * while (i >= 0 && array[i] >= array[i + 1]) {
   * i--;
   * }
   * if (i < 0) {
   * return false;
   * }
   * int j = n - 1;
   * while (array[j] <= array[i]) {
   * j--;
   * }
   * swap2(array, i, j);
   * reverse2(array, i + 1, n - 1);
   * return true;
   * }
   * 
   * private static void reverse2(char[] array, int start, int end) {
   * while (start < end) {
   * swap2(array, start, end);
   * start++;
   * end--;
   * }
   * }
   * 
   * private static void swap2(char[] array, int i, int j) {
   * char temp = array[i];
   * array[i] = array[j];
   * array[j] = temp;
   * }
   * 
   * standard problem with prefix sums: if two prefix sums are equal, then the
   * subarray between them has sum 0
   * ; otherwise, no subarray has sum 0
   * .
   * 
   */
}
