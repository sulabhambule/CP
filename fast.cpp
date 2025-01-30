#include <bits/stdc++.h>
using namespace std;
#define INF 1e15

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n, m, q;
    cin >> n >> m >> q;

    vector<vector<long long>> dp(n + 1, vector<long long>(n + 1, INF));

    for (int i = 1; i <= n; i++)
    {
        dp[i][i] = 0;
    }

    for (int i = 0; i < m; i++)
    {
        int a, b;
        long long c;
        cin >> a >> b >> c;
        dp[a][b] = min(dp[a][b], c);
        dp[b][a] = min(dp[b][a], c);
    }

    // Floyd-Warshall Algorithm
    for (int k = 1; k <= n; k++)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j]);
            }
        }
    }

    while (q--)
    {
        int x, y;
        cin >> x >> y;
        cout << (dp[x][y] == INF ? -1 : dp[x][y]) << '\n';
    }

    return 0;
}
