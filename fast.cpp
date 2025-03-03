#include <bits/stdc++.h>
using namespace std;

#define MOD 998244353
#define int long long

class FASTIO
{
public:
    FASTIO()
    {
        ios::sync_with_stdio(false);
        cin.tie(nullptr);
    }

    int nextInt()
    {
        int x;
        cin >> x;
        return x;
    }

    long long nextLong()
    {
        long long x;
        cin >> x;
        return x;
    }
};

FASTIO in;

int n, m, sccCount = 0;
vector<vector<int>> adj, revAdj, dag;
vector<long long> coins, sccCoins, dp;
vector<int> sccId, order;
vector<bool> visited;
vector<vector<int>> sccs;

void dfs1(int v)
{
    visited[v] = true;
    for (int u : adj[v])
    {
        if (!visited[u])
            dfs1(u);
    }
    order.push_back(v);
}

void dfs2(int node, vector<int> &comp, int id)
{
    visited[node] = true;
    sccId[node] = id;
    comp.push_back(node);
    sccCoins[id] += coins[node]; // Sum the coins in the SCC
    for (int adjNode : revAdj[node])
    {
        if (!visited[adjNode])
        {
            dfs2(adjNode, comp, id);
        }
    }
}

void buildCondensationGraph(int sccCount)
{
    dag.assign(sccCount, vector<int>());
    for (int i = 0; i < n; i++)
    {
        for (int v : adj[i])
        {
            if (sccId[i] != sccId[v])
            {
                dag[sccId[i]].push_back(sccId[v]);
            }
        }
    }
}

void findSCCs()
{
    visited.assign(n, false);
    order.clear();

    // Step 1: Topo order by finishing time
    for (int i = 0; i < n; i++)
    {
        if (!visited[i])
            dfs1(i);
    }

    // Step 2: Reverse the graph
    for (int i = 0; i < n; i++)
    {
        for (int v : adj[i])
        {
            revAdj[v].push_back(i);
        }
    }

    // Step 3: Process nodes in reverse order
    fill(visited.begin(), visited.end(), false);
    reverse(order.begin(), order.end());
    sccs.clear();
    sccId.assign(n, 0);
    sccCoins.assign(n, 0);

    for (int node : order)
    {
        if (!visited[node])
        {
            vector<int> comp;
            dfs2(node, comp, sccCount++);
            sccs.push_back(comp);
        }
    }

    // Step 4: Build Condensation Graph
    buildCondensationGraph(sccCount);
}

void solve()
{
    n = in.nextInt();
    m = in.nextInt();
    coins.resize(n);
    for (int i = 0; i < n; i++)
    {
        coins[i] = in.nextLong();
    }

    adj.assign(n, vector<int>());
    revAdj.assign(n, vector<int>());

    for (int i = 0; i < m; i++)
    {
        int u = in.nextInt() - 1;
        int v = in.nextInt() - 1;
        adj[u].push_back(v);
    }

    findSCCs();
    dp.assign(sccCount, 0);
    long long maxCoins = 0;

    for (int i = sccCount - 1; i >= 0; i--)
    {
        dp[i] = sccCoins[i];
        for (int adjNode : dag[i])
        {
            dp[i] = max(dp[i], dp[adjNode] + sccCoins[i]);
        }
        maxCoins = max(maxCoins, dp[i]);
    }

    cout << maxCoins << '\n';
}

int32_t main()
{
    solve();
    return 0;
}
