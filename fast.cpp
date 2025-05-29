#include <bits/stdc++.h>
using namespace std;

const int MOD = 1e9 + 7;
const int INF = 1e9;
const long long LINF = 1e18;

vector<vector<int>> adj;
vector<vector<int>> dp;

void dfs(int node, int parent)
{
    for (int adjNode : adj[node])
    {
        if (adjNode != parent)
        {
            dfs(adjNode, node);
        }
    }

    int total = 0;
    for (int adjNode : adj[node])
    {
        if (adjNode != parent)
        {
            total += max(dp[adjNode][0], dp[adjNode][1]);
        }
    }
    dp[node][0] = total;

    for (int adjNode : adj[node])
    {
        if (adjNode == parent)
            continue;

        int current = 1 + dp[adjNode][0];
        for (int v : adj[node])
        {
            if (v == parent || v == adjNode)
                continue;
            current += max(dp[v][0], dp[v][1]);
        }

        dp[node][1] = max(dp[node][1], current);
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    adj.assign(n + 1, vector<int>());
    dp.assign(n + 1, vector<int>(2, 0));

    for (int i = 0; i < n - 1; ++i)
    {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1, 0);

    cout << max(dp[1][0], dp[1][1]) << '\n';

    return 0;
}
