#include <bits/stdc++.h>
using namespace std;

#define int long long
const int INF = 1e9;

struct Edge
{
    int u, v, w;
    bool operator<(const Edge &other) const
    {
        return w < other.w;
    }
};

struct DSU
{
    vector<int> parent, rank, size;

    DSU(int n)
    {
        parent.resize(n);
        rank.resize(n, 0);
        size.resize(n, 1);
        for (int i = 0; i < n; ++i)
            parent[i] = i;
    }

    int find(int x)
    {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    bool unite(int u, int v)
    {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV)
            return false;

        if (rank[rootU] > rank[rootV])
        {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        }
        else if (rank[rootU] < rank[rootV])
        {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        }
        else
        {
            parent[rootV] = rootU;
            rank[rootU]++;
            size[rootU] += size[rootV];
        }
        return true;
    }

    int getSize(int x)
    {
        return size[find(x)];
    }
};

void solve()
{
    int n, m;
    cin >> n >> m;
    vector<Edge> edges(m);
    for (int i = 0; i < m; ++i)
    {
        cin >> edges[i].u >> edges[i].v >> edges[i].w;
    }

    sort(edges.begin(), edges.end());

    DSU dsu(n + 1); // if 1-based indexing
    int comp = n;
    long long total = 0;

    for (auto &e : edges)
    {
        if (dsu.unite(e.u, e.v))
        {
            total += e.w;
            comp--;
        }
    }

    if (comp > 1)
        cout << "IMPOSSIBLE\n";
    else
        cout << total << '\n';
}

int32_t main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}
