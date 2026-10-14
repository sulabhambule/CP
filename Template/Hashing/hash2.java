public class hash2 {

}

class DoubleHash {
  long[] hash1, hash2, pow1, pow2;
  long base = 131;
  long mod1 = 1_000_000_007;
  long mod2 = 1_000_000_009;

  DoubleHash(String s) {
    int n = s.length();
    hash1 = new long[n + 1];
    hash2 = new long[n + 1];
    pow1 = new long[n + 1];
    pow2 = new long[n + 1];

    pow1[0] = pow2[0] = 1;
    for (int i = 0; i < n; i++) {
      pow1[i + 1] = (pow1[i] * base) % mod1;
      pow2[i + 1] = (pow2[i] * base) % mod2;

      hash1[i + 1] = (hash1[i] * base + (s.charAt(i) - 'a' + 1)) % mod1;
      hash2[i + 1] = (hash2[i] * base + (s.charAt(i) - 'a' + 1)) % mod2;
    }
  }

  long[] gethash(int l, int r) {
    long x1 = (hash1[r + 1] - hash1[l] * pow1[r - l + 1] % mod1 + mod1) % mod1;
    long x2 = (hash2[r + 1] - hash2[l] * pow2[r - l + 1] % mod2 + mod2) % mod2;
    // 10 9 8 7 6 5 4 3 2 1 0
    //  3 2 1 0
    long combined = (x1 << 32) ^ x2;
    return new long[] { x1, x2 };
  }
}
