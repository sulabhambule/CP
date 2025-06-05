#include <bits/stdc++.h>
using namespace std;

struct Edge
{
    int source, neighbor, c, i;
};

static const int INF = 1e9;

vector<vector<Edge>> adj;
vector<int> depth, heavy, sz, parentArr, valueArr, linear_tree, head, edgeToNode, pos;
vector<int> segTree;
int curIndex;

void build(int node, int start, int end)
{
    if (start == end)
    {
        segTree[node] = linear_tree[start];
        return;
    }
    int mid = (start + end) / 2;
    build(2 * node + 1, start, mid);
    build(2 * node + 2, mid + 1, end);
    segTree[node] = max(segTree[2 * node + 1], segTree[2 * node + 2]);
}

void update(int node, int start, int end, int idx, int val)
{
    if (start == end)
    {
        segTree[node] = val;
        return;
    }
    int mid = (start + end) / 2;
    if (idx <= mid)
    {
        update(2 * node + 1, start, mid, idx, val);
    }
    else
    {
        update(2 * node + 2, mid + 1, end, idx, val);
    }
    segTree[node] = max(segTree[2 * node + 1], segTree[2 * node + 2]);
}

int query(int node, int start, int end, int l, int r)
{
    if (l > end || r < start)
        return -INF;
    if (l <= start && end <= r)
        return segTree[node];
    int mid = (start + end) / 2;
    return max(
        query(2 * node + 1, start, mid, l, r),
        query(2 * node + 2, mid + 1, end, l, r));
}

void dfs(int u, int p)
{
    sz[u] = 1;
    parentArr[u] = p;
    depth[u] = depth[p] + 1;
    int maxSub = 0;
    for (auto &e : adj[u])
    {
        int v = e.neighbor;
        if (v == p)
            continue;
        edgeToNode[e.i] = v;
        valueArr[v] = e.c;
        dfs(v, u);
        sz[u] += sz[v];
        if (sz[v] > maxSub)
        {
            maxSub = sz[v];
            heavy[u] = v;
        }
    }
}

void hld(int u, int h)
{
    head[u] = h;
    linear_tree[curIndex] = valueArr[u];
    pos[u] = curIndex++;
    if (heavy[u] != -1)
    {
        hld(heavy[u], h);
    }
    for (auto &e : adj[u])
    {
        int v = e.neighbor;
        if (v == parentArr[u] || v == heavy[u])
            continue;
        hld(v, v);
    }
}

int pathQuery(int a, int b)
{
    int res = -INF;
    while (head[a] != head[b])
    {
        if (depth[head[a]] > depth[head[b]])
        {
            swap(a, b);
        }
        int start = pos[head[b]];
        int end = pos[b];
        res = max(res, query(0, 0, curIndex - 1, start, end));
        b = parentArr[head[b]];
    }
    if (depth[a] > depth[b])
    {
        swap(a, b);
    }
    // We stored edge-values at child positions, so skip pos[a] itself
    if (pos[a] + 1 <= pos[b])
    {
        res = max(res, query(0, 0, curIndex - 1, pos[a] + 1, pos[b]));
    }
    return res;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;

        // Allocate / reinitialize all arrays
        adj.assign(n + 1, vector<Edge>());
        depth.assign(n + 1, 0);
        heavy.assign(n + 1, -1);
        sz.assign(n + 1, 0);
        parentArr.assign(n + 1, 0);
        valueArr.assign(n + 1, 0);
        linear_tree.assign(n, 0);
        head.assign(n + 1, 0);
        edgeToNode.assign(n, 0);
        pos.assign(n + 1, 0);
        segTree.assign(4 * n, 0);
        curIndex = 0;

        // Read edges
        for (int i = 1; i < n; i++)
        {
            int v, u, c;
            cin >> v >> u >> c;
            adj[v].push_back({v, u, c, i});
            adj[u].push_back({u, v, c, i});
        }

        // Run DFS to compute sizes, depth, parent, and heavy child
        dfs(1, 0);

        // Decompose the tree
        hld(1, 1);

        // Build segment tree over linear_tree[0..curIndex-1]
        if (curIndex > 0)
        {
            build(0, 0, curIndex - 1);
        }

        // Process commands
        while (true)
        {
            string cmd;
            cin >> cmd;
            if (cmd == "DONE")
            {
                break;
            }
            if (cmd == "CHANGE")
            {
                int iEdge, newVal;
                cin >> iEdge >> newVal;
                int node = edgeToNode[iEdge];
                update(0, 0, curIndex - 1, pos[node], newVal);
            }
            else if (cmd == "QUERY")
            {
                int a, b;
                cin >> a >> b;
                int ans = pathQuery(a, b);
                cout << ans << "\n";
            }
        }
    }

    return 0;
}
