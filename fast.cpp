#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

bool dfs(int node, int color, vector<vector<int>> &adj, vector<bool> &vis, vector<int> &c)
{
    vis[node] = true;
    c[node] = color;

    for (int neighbor : adj[node])
    {
        if (!vis[neighbor])
        {
            if (!dfs(neighbor, color ^ 1, adj, vis, c))
            {
                return false;
            }
        }
        else if (c[neighbor] == c[node])
        {
            return false;
        }
    }

    return true;
}

int main()
{
    int n, m;
    cin >> n >> m;

    vector<vector<int>> adj(n + 1);
    for (int i = 0; i < m; i++)
    {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    vector<bool> vis(n + 1, false);
    vector<int> c(n + 1, -1);

    bool isBipartite = true;
    for (int i = 1; i <= n; i++)
    {
        if (!vis[i])
        {
            isBipartite &= dfs(i, 0, adj, vis, c);
        }
    }

    if (!isBipartite)
    {
        cout << "IMPOSSIBLE" << endl;
    }
    else
    {
        for (int i = 1; i <= n; i++)
        {
            cout << (c[i] + 1) << " ";
        }
        cout << endl;
    }

    return 0;
}
