#include <bits/stdc++.h>
using namespace std;

const int MOD = 1e9 + 7;

void solve()
{
    int n, x;
    cin >> n >> x;
    vector<int> c(n);
    for (int i = 0; i < n; i++)
    {
        cin >> c[i];
    }

    // dp[i][k] represents the number of ways to make the sum `k` using coins from `i` to `n-1`.
    vector<vector<int>> dp(n + 1, vector<int>(x + 1, 0));

    // Base case: There's exactly 1 way to make sum 0 (by taking no coins).
    for (int i = 0; i <= n; i++)
    {
        dp[i][0] = 1;
    }

    // Fill the dp table from the last coin to the first coin.
    for (int i = n - 1; i >= 0; i--)
    {
        for (int j = 1; j <= x; j++)
        {
            int take = 0;
            int skip = dp[i + 1][j]; // Skip current coin
            if (c[i] <= j)
            {
                take = dp[i][j - c[i]]; // Take current coin
            }
            dp[i][j] = (take + skip) % MOD;
        }
    }

    cout << dp[0][x] << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int testCases = 1;
    while (testCases--)
    {
        solve();
    }
    return 0;
}
