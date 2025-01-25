#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

const int MOD = 998244353;
vector<vector<int>> adj;

long long modPow(long long base, long long exp, int mod)
{
    long long result = 1;
    base %= mod;
    while (exp > 0)
    {
        if (exp & 1)
        {
            result = (result * base) % mod;
        }
        base = (base * base) % mod;
        exp >>= 1;
    }
    return result;
}

bool dfs(int node, int color, vector<bool> &visited, vector<int> &colors, vector<int> &counts)
{
    visited[node] = true;
    colors[node] = color;
    counts[color]++;

    for (int neighbor : adj[node])
    {
        if (!visited[neighbor])
        {
            if (!dfs(neighbor, color ^ 1, visited, colors, counts))
            {
                return false;
            }
        }
        else if (colors[neighbor] == colors[node])
        {
            return false;
        }
    }
    return true;
}

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n, m;
        cin >> n >> m;

        adj.assign(n + 1, vector<int>());

        for (int i = 0; i < m; i++)
        {
            int u, v;
            cin >> u >> v;
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        vector<int> colors(n + 1, -1);
        vector<bool> visited(n + 1, false);

        bool isBipartite = true;
        long long result = 1;

        for (int i = 1; i <= n; i++)
        {
            if (!visited[i])
            {
                vector<int> counts(2, 0);
                if (!dfs(i, 0, visited, colors, counts))
                {
                    isBipartite = false;
                    break;
                }
                long long partResult = (modPow(2, counts[0], MOD) + modPow(2, counts[1], MOD)) % MOD;
                result = (result * partResult) % MOD;
            }
        }

        cout << (isBipartite ? result : 0) << endl;
    }
    return 0;
}
