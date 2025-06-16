#include <bits/stdc++.h>
using namespace std;

const int MAX = 1e6 + 5;
vector<pair<int, int>> adj[MAX]; // {neighbor, edge id}
vector<int> path;
bool visited[MAX];

void dfs(int node)
{
    while (!adj[node].empty())
    {
        auto [to, id] = adj[node].back();
        adj[node].pop_back();
        if (visited[id])
            continue;
        visited[id] = true;
        dfs(to);
    }
    path.push_back(node);
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    int edgeId = 0;
    for (int i = 0; i < m; ++i)
    {
        int a, b;
        cin >> a >> b;
        adj[a].emplace_back(b, edgeId);
        adj[b].emplace_back(a, edgeId);
        ++edgeId;
    }

    // Check for odd degree
    for (int i = 1; i <= n; ++i)
    {
        if (adj[i].size() % 2 != 0)
        {
            cout << "IMPOSSIBLE\n";
            return 0;
        }
    }

    dfs(1);

    if (path.size() != m + 1)
    {
        cout << "IMPOSSIBLE\n";
    }
    else
    {
        reverse(path.begin(), path.end());
        for (int node : path)
        {
            cout << node << " ";
        }
        cout << "\n";
    }

    return 0;
}
