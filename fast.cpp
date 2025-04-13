#include <bits/stdc++.h>
using namespace std;

#define int long long
const int MOD = 1e9 + 7;

vector<vector<int>> adj;
vector<long long> segTree;
int timeCounter = 0;

// Segment Tree functions
void build(int node, int start, int end, const vector<long long> &euler)
{
    if (start == end)
    {
        segTree[node] = euler[start];
        return;
    }
    int mid = (start + end) / 2;
    build(2 * node + 1, start, mid, euler);
    build(2 * node + 2, mid + 1, end, euler);
    segTree[node] = segTree[2 * node + 1] + segTree[2 * node + 2];
}

void update(int node, int start, int end, int idx, long long value)
{
    if (start == end)
    {
        segTree[node] = value;
        return;
    }
    int mid = (start + end) / 2;
    if (idx <= mid)
        update(2 * node + 1, start, mid, idx, value);
    else
        update(2 * node + 2, mid + 1, end, idx, value);
    segTree[node] = segTree[2 * node + 1] + segTree[2 * node + 2];
}

long long query(int node, int start, int end, int l, int r)
{
    if (r < start || l > end)
        return 0;
    if (l <= start && end <= r)
        return segTree[node];
    int mid = (start + end) / 2;
    return query(2 * node + 1, start, mid, l, r) +
           query(2 * node + 2, mid + 1, end, l, r);
}

// DFS for Euler Tour
void dfs(int node, int parent, vector<int> &inTime, vector<int> &outTime)
{
    inTime[node] = timeCounter++;
    for (int neighbor : adj[node])
    {
        if (neighbor != parent)
        {
            dfs(neighbor, node, inTime, outTime);
        }
    }
    outTime[node] = timeCounter++;
}

int32_t main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n, q;
    cin >> n >> q;
    vector<long long> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];

    adj.assign(n + 1, {});
    for (int i = 0; i < n - 1; i++)
    {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    vector<long long> euler(2 * n);
    vector<int> inTime(n + 1), outTime(n + 1);
    timeCounter = 0;
    dfs(1, -1, inTime, outTime);

    for (int i = 1; i <= n; i++)
    {
        euler[inTime[i]] = v[i - 1];
        euler[outTime[i]] = -v[i - 1];
    }

    segTree.assign(8 * n, 0);
    build(0, 0, 2 * n - 1, euler);

    while (q--)
    {
        int type;
        cin >> type;
        if (type == 1)
        {
            int s;
            long long x;
            cin >> s >> x;
            update(0, 0, 2 * n - 1, inTime[s], x);
            update(0, 0, 2 * n - 1, outTime[s], -x);
        }
        else
        {
            int s;
            cin >> s;
            cout << query(0, 0, 2 * n - 1, 0, inTime[s]) << "\n";
        }
    }

    return 0;
}
