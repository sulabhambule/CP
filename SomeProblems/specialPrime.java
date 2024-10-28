
import java.io.*;
import java.util.*;

public class specialPrime {
  static final int MAXN = 10000000;
  static boolean[] isPrime = new boolean[MAXN + 1];
  static List<Integer> specialPrimes = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    sieve();
    solve(MAXN);
    Collections.sort(specialPrimes);
    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      int n = Integer.parseInt(br.readLine());
      out.println(binarySearchLessThanOrEqual(n) + 1);
    }

    out.flush();
    out.close();
  }

  static void sieve() {
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;

    for (int i = 2; i < MAXN; i++) {
      if (isPrime[i]) {
        for (int j = i * 2; j < MAXN; j += i) {
          isPrime[j] = false;
        }
      }
    }
  }

  static void solve(int n) {
    boolean[] addedPrime = new boolean[MAXN + 1];

    for (int x = 1; x <= 10000; x++) {
      for (int y = 0; y <= 100; y++) {
        int num = (x * x) + (y * y * y * y);
        if (num > n)
          break;
        if (isPrime[num] && !addedPrime[num]) {
          specialPrimes.add(num);
          addedPrime[num] = true;
        }
      }
    }
  }

  static int binarySearchLessThanOrEqual(int n) {
    int left = 0, right = specialPrimes.size() - 1;
    int result = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (specialPrimes.get(mid) <= n) {
        result = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return result;
  }
}
