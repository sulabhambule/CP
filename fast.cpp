#include <bits/stdc++.h>
using namespace std;

class DSU
{
private:
    vector<int> parent, rank, size;

public:
    DSU(int n)
    {
        parent.resize(n);
        rank.resize(n, 0);
        size.resize(n, 1);
        for (int i = 0; i < n; i++)
        {
            parent[i] = i;
        }
    }

    int find(int x)
    {
        if (parent[x] != x)
        {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    void unite(int u, int v)
    {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV)
            return;

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
    DSU dsu(n + 1);

    for (int i = 0; i < m; i++)
    {
        int k;
        cin >> k;
        if (k > 0)
        {
            int num;
            cin >> num;
            for (int j = 1; j < k; j++)
            {
                int nn;
                cin >> nn;
                dsu.unite(num, nn);
            }
        }
    }

    for (int i = 1; i <= n; i++)
    {
        cout << dsu.getSize(i) << " ";
    }
    cout << endl;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}
