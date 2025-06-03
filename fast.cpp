#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 2e5 + 5;

vector<int> adj[MAX_N];
set<long long> color[MAX_N];
int ans[MAX_N];

void processColors(int node, int parent)
{
    for (int neighbor : adj[node])
    {
        if (neighbor == parent)
            continue;
        processColors(neighbor, node);

        // Always merge smaller set into larger one
        if (color[node].size() < color[neighbor].size())
        {
            swap(color[node], color[neighbor]);
        }

        for (auto val : color[neighbor])
        {
            color[node].insert(val);
        }
    }
    ans[node] = color[node].size();
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    for (int i = 1; i <= n; ++i)
    {
        long long c;
        cin >> c;
        color[i].insert(c);
    }

    for (int i = 1; i < n; ++i)
    {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    processColors(1, 0);

    for (int i = 1; i <= n; ++i)
    {
        cout << ans[i] << " \n"[i == n];
    }

    return 0;
}
