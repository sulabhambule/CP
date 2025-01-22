#include <bits/stdc++.h>
using namespace std;

#define MAX_LOG 20
#define N 200001
#define MOD 998244353
#define ll long long
#define all(v) v.begin(), v.end()

vector<int> adj[N];
int par[N][MAX_LOG];
int depth[N];

void dfs(int node, int parent)
{
    depth[node] = depth[parent] + 1;
    par[node][0] = parent;
    for (int j = 1; j < MAX_LOG; j++)
    {
        par[node][j] = par[par[node][j - 1]][j - 1];
    }
    for (int neighbor : adj[node])
    {
        if (neighbor != parent)
        {
            dfs(neighbor, node);
        }
    }
}

int LCA_(int nodeA, int nodeB)
{
    if (depth[nodeA] < depth[nodeB])
        swap(nodeA, nodeB);

    int nodeDiff = depth[nodeA] - depth[nodeB];
    for (int j = MAX_LOG - 1; j >= 0; j--)
    {
        if ((1 << j) & nodeDiff)
        {
            nodeA = par[nodeA][j];
        }
    }

    if (nodeA == nodeB)
        return nodeA;

    for (int j = MAX_LOG - 1; j >= 0; j--)
    {
        if (par[nodeA][j] != par[nodeB][j])
        {
            nodeA = par[nodeA][j];
            nodeB = par[nodeB][j];
        }
    }
    return par[nodeA][0];
}

void dfs1(int curr, int parent, vector<int> &level)
{
    level[curr] = (parent == -1) ? 0 : level[parent] + 1;
    for (int neighbor : adj[curr])
    {
        if (neighbor != parent)
        {
            dfs1(neighbor, curr, level);
        }
    }
}

int farthestNode(int n, vector<int> &dist)
{
    int farthest = 1;
    for (int i = 1; i <= n; i++)
    {
        if (dist[i] > dist[farthest])
        {
            farthest = i;
        }
    }
    return farthest;
}

void solve()
{
    int n;
    cin >> n;
    for (int i = 0; i <= n; i++)
    {
        adj[i].clear();
    }
    for (int i = 0; i < n - 1; i++)
    {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // Initialize the depth and parent arrays
    dfs(1, 0);

    vector<int> distX(n + 1, -1), distY(n + 1, -1);

    int x = 1;
    dfs1(x, -1, distX);
    int y = farthestNode(n, distX);

    dfs1(y, -1, distY);
    int z = farthestNode(n, distY);

    for (int i = 1; i <= n; i++)
    {
        int distance1 = depth[i] + depth[y] - 2 * depth[LCA_(i, y)];
        int distance2 = depth[i] + depth[z] - 2 * depth[LCA_(i, z)];
        cout << max(distance1, distance2) << " ";
    }
    cout << endl;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    int T = 1;
    while (T--)
    {
        solve();
    }

    return 0;
}
