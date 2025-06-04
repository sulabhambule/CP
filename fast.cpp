#include <bits/stdc++.h>
using namespace std;

int n, q, index_ptr = 0;
vector<vector<int>> adj;
vector<int> pos, head, heavy, depth, size, parent;
vector<long long> value, ltree, segTree;

void build(int node, int start, int end)
{
    if (start == end)
    {
        segTree[node] = ltree[start];
        return;
    }
    int mid = (start + end) / 2;
    build(2 * node + 1, start, mid);
    build(2 * node + 2, mid + 1, end);
    segTree[node] = max(segTree[2 * node + 1], segTree[2 * node + 2]);
}

void update(int node, int start, int end, int idx, long long val)
{
    if (start == end)
    {
        segTree[node] = val;
        return;
    }
    int mid = (start + end) / 2;
    if (idx <= mid)
        update(2 * node + 1, start, mid, idx, val);
    else
        update(2 * node + 2, mid + 1, end, idx, val);
    segTree[node] = max(segTree[2 * node + 1], segTree[2 * node + 2]);
}

long long query(int node, int start, int end, int l, int r)
{
    if (r < start || l > end)
        return LLONG_MIN;
    if (l <= start && end <= r)
        return segTree[node];
    int mid = (start + end) / 2;
    return max(query(2 * node + 1, start, mid, l, r),
               query(2 * node + 2, mid + 1, end, l, r));
}

long long pathQuery(int u, int v)
{
    long long res = LLONG_MIN;
    while (head[u] != head[v])
    {
        if (depth[head[u]] < depth[head[v]])
            swap(u, v);
        res = max(res, query(0, 0, index_ptr - 1, pos[head[u]], pos[u]));
        u = parent[head[u]];
    }
    if (depth[u] > depth[v])
        swap(u, v);
    res = max(res, query(0, 0, index_ptr - 1, pos[u], pos[v]));
    return res;
}

void dfs(int u, int p)
{
    parent[u] = p;
    size[u] = 1;
    depth[u] = depth[p] + 1;
    int max_subtree = 0;
    for (int v : adj[u])
    {
        if (v != p)
        {
            dfs(v, u);
            size[u] += size[v];
            if (size[v] > max_subtree)
            {
                max_subtree = size[v];
                heavy[u] = v;
            }
        }
    }
}

void hld(int u, int h)
{
    head[u] = h;
    pos[u] = index_ptr;
    ltree[index_ptr++] = value[u];
    if (heavy[u] != 0)
        hld(heavy[u], h);
    for (int v : adj[u])
    {
        if (v != parent[u] && v != heavy[u])
            hld(v, v);
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> q;

    adj.resize(n + 1);
    pos.resize(n + 1);
    head.resize(n + 1);
    heavy.assign(n + 1, 0);
    depth.resize(n + 1);
    size.resize(n + 1);
    parent.resize(n + 1);
    value.resize(n + 1);
    ltree.resize(n + 1);
    segTree.resize(4 * n); // Safe upper bound for segment tree

    for (int i = 1; i <= n; ++i)
    {
        cin >> value[i];
    }

    for (int i = 1; i < n; ++i)
    {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    dfs(1, 0);
    hld(1, 1);
    build(0, 0, index_ptr - 1);

    vector<long long> results;

    while (q--)
    {
        int type;
        cin >> type;
        if (type == 1)
        {
            int s;
            long long x;
            cin >> s >> x;
            update(0, 0, index_ptr - 1, pos[s], x);
        }
        else
        {
            int a, b;
            cin >> a >> b;
            results.push_back(pathQuery(a, b));
        }
    }

    for (auto x : results)
        cout << x << " ";
    cout << '\n';

    return 0;
}
