public class hash1 {

}

class StringHash {
  long[] hash;
  long[] pow;
  long base = 31; // can use 31 / 37 / 131
  long mod = 1_000_000_007;

  StringHash(String s) {
    int n = s.length();
    hash = new long[n + 1];
    pow = new long[n + 1];

    pow[0] = 1;

    for (int i = 0; i < n; i++) {
      pow[i + 1] = (pow[i] * base) % mod;
      hash[i + 1] = (hash[i] * base + (s.charAt(i) - 'a' + 1)) % mod;
    }
  }

  // get hash of substring [l, r]
  long getHash(int l, int r) {
    long res = (hash[r + 1] - (hash[l] * pow[r - l + 1]) % mod + mod) % mod;
    return res;
  }
}
