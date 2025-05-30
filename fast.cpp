#include <bits/stdc++.h>
using namespace std;

const int MAXN = 2e5 + 5;

int n, timer = 0;
vector<int> adj[MAXN];
int color[MAXN];
int inTime[MAXN], outTime[MAXN];
int nodeAt[2 * MAXN]; // Euler tour order
int answer[MAXN];

struct Query
{
    int l, r, id;
    bool operator<(const Query &other) const
    {
        return r < other.r;
    }
};

void dfs(int u, int p)
{
    inTime[u] = timer;
    nodeAt[timer++] = u;
    for (int v : adj[u])
    {
        if (v == p)
            continue;
        dfs(v, u);
    }
    outTime[u] = timer;
    nodeAt[timer++] = 0; // 0 means exit or dummy
}

struct SegTree
{
    int size;
    vector<int> st;

    void init(int n)
    {
        size = n;
        st.assign(4 * n, 0);
    }

    void update(int node, int l, int r, int idx, int val)
    {
        if (l == r)
        {
            st[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid)
            update(2 * node + 1, l, mid, idx, val);
        else
            update(2 * node + 2, mid + 1, r, idx, val);
        st[node] = st[2 * node + 1] + st[2 * node + 2];
    }

    int query(int node, int l, int r, int ql, int qr)
    {
        if (r < ql || l > qr)
            return 0;
        if (ql <= l && r <= qr)
            return st[node];
        int mid = (l + r) / 2;
        return query(2 * node + 1, l, mid, ql, qr) + query(2 * node + 2, mid + 1, r, ql, qr);
    }
};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 1; i <= n; ++i)
    {
        cin >> color[i];
    }

    for (int i = 0; i < n - 1; ++i)
    {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    dfs(1, 0);

    vector<Query> queries;
    for (int i = 1; i <= n; ++i)
    {
        queries.push_back({inTime[i], outTime[i], i});
    }

    sort(queries.begin(), queries.end());

    SegTree seg;
    seg.init(2 * n);
    unordered_map<int, int> last;
    int idx = 0;

    for (int t = 0; t < 2 * n; ++t)
    {
        int node = nodeAt[t];
        if (node != 0 && inTime[node] == t)
        {
            int c = color[node];
            if (last.count(c))
            {
                seg.update(0, 0, 2 * n - 1, last[c], 0);
            }
            seg.update(0, 0, 2 * n - 1, t, 1);
            last[c] = t;
        }
        while (idx < n && queries[idx].r == t)
        {
            answer[queries[idx].id] = seg.query(0, 0, 2 * n - 1, queries[idx].l, queries[idx].r);
            ++idx;
        }
    }

    for (int i = 1; i <= n; ++i)
    {
        cout << answer[i] << (i < n ? ' ' : '\n');
    }

    return 0;
}
