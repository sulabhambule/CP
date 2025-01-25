#include <bits/stdc++.h>
using namespace std;

#define int long long
#define MOD 998244353
#define MAX_LOG 20
#define N 200001

vector<int> adj[N];
int par[N][MAX_LOG];
int depth[N];
int diff[N];

/**
 * @Ayush Chavan
 * JAI SHREE RAM
 */

void dfs(int node, int parent)
{
    depth[node] = depth[parent] + 1;
    par[node][0] = parent;
    for (int j = 1; j < MAX_LOG; j++)
    {
        par[node][j] = par[par[node][j - 1]][j - 1];
    }
    for (int adjNode : adj[node])
    {
        if (adjNode != parent)
        {
            dfs(adjNode, node);
        }
    }
}

int LCA(int u, int v)
{
    if (u == v)
        return u;
    if (depth[u] < depth[v])
        swap(u, v);

    int d = depth[u] - depth[v];
    for (int i = MAX_LOG - 1; i >= 0; i--)
    {
        if ((1 << i) & d)
        {
            u = par[u][i];
        }
    }

    if (u == v)
        return u;

    for (int i = MAX_LOG - 1; i >= 0; i--)
    {
        if (par[u][i] != par[v][i])
        {
            u = par[u][i];
            v = par[v][i];
        }
    }
    return par[u][0];
}

void propagate(int node, int parent)
{
    for (int adjNode : adj[node])
    {
        if (adjNode != parent)
        {
            propagate(adjNode, node);
            diff[node] += diff[adjNode];
        }
    }
}

void solve()
{
    int n, m;
    cin >> n >> m;

    for (int i = 0; i <= n; i++)
        adj[i].clear();
    memset(par, 0, sizeof(par));
    memset(diff, 0, sizeof(diff));
    memset(depth, 0, sizeof(depth));

    for (int i = 0; i < n - 1; i++)
    {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1, 0);

    for (int i = 0; i < m; i++)
    {
        int a, b;
        cin >> a >> b;
        diff[a]++;
        diff[b]++;
        int lca = LCA(a, b);
        diff[lca]--;
        if (par[lca][0] != 0)
            diff[par[lca][0]]--;
    }

    propagate(1, 0);

    for (int i = 1; i <= n; i++)
    {
        cout << diff[i] << " ";
    }
    cout << endl;
}

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int T = 1;
    // cin >> T;
    while (T--)
    {
        solve();
    }
    return 0;
}
