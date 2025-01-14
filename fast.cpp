#include <bits/stdc++.h>
using namespace std;

#define MOD 1000000007

// Function to perform modular exponentiation
long long modPow(long long base, long long exp, long long mod) {
    long long result = 1;
    base = base % mod;
    while (exp > 0) {
        if (exp % 2 == 1) {
            result = (result * base) % mod;
        }
        base = (base * base) % mod;
        exp /= 2;
    }
    return result;
}

// Function to handle modular arithmetic
long long mod(long long n) {
    return (n % MOD + MOD) % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    
    long long x, k;
    cin >> x >> k;

    // If x is 0, print 0 and return
    if (x == 0) {
        cout << "0\n";
        return 0;
    }

    // If k is 0, print (2 * x) % MOD
    if (k == 0) {
        cout << (2 * x) % MOD << '\n';
        return 0;
    }

    // Compute (2^k+1) % MOD
    long long power2kPlus1 = modPow(2, k + 1, MOD);
    // Compute (2^k) % MOD
    long long power2k = modPow(2, k, MOD);
    
    // Compute the result
    long long result = mod((power2kPlus1 * x) - power2k + 1);

    // Output the result
    cout << result << '\n';

    return 0;
}
