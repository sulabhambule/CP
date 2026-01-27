#include <bits/stdc++.h>
using namespace std;

struct Combinatorics
{
    const int MOD;
    vector<long long> fact, invFact;

    // Constructor
    Combinatorics(int maxN, int mod) : MOD(mod), fact(maxN + 1), invFact(maxN + 1)
    {
        precompute(maxN);
    }

    // Function to perform modular exponentiation: a^b % MOD
    long long modpow(long long a, long long b) const
    {
        long long res = 1;
        while (b)
        {
            if (b & 1)
                res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    // Precomputing factorials and modular inverses
    void precompute(int maxN)
    {
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++)
        {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[maxN] = modpow(fact[maxN], MOD - 2); // Fermat's Little Theorem
        for (int i = maxN - 1; i >= 0; i--)
        {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }

    // Function to calculate nCk % MOD
    long long nCk(int n, int k) const
    {
        if (k > n || k < 0)
            return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    // Function to calculate nPk % MOD
    long long nPk(int n, int k) const
    {
        if (k > n || k < 0)
            return 0;
        return fact[n] * invFact[n - k] % MOD;
    }

    // Function to calculate n! % MOD
    long long factorial(int n) const
    {
        return fact[n];
    }
};

int main()
{
    int maxN = 1e6 + 5; // Maximum n value for factorials and combinations
    int mod = 1e6 + 3;  // Modulus for calculations

    // Initialize combinatorics when needed
    Combinatorics comb(maxN, mod);

    // Example usage
    int n = 1, k = 3;
    cout << "10C3 = " << comb.nCk(n, k) << '\n';   // Output: 120
    cout << "10P3 = " << comb.nPk(n, k) << '\n';   // Output: 720
    cout << "10! = " << comb.factorial(n) << '\n'; // Output: 3628800
    cout << "In = " << comb.invFact[n] << '\n';    // Output: 3628800

    return 0;
}