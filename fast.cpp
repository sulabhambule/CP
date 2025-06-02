#include <bits/stdc++.h>
using namespace std;

const int MAXN = 2e5 + 5;

vector<int> adj[MAXN];
int subSize[MAXN];
int N;

// Calculate subtree size
int subtreeSize(int node, int parent)
{
    int res = 1;
    for (int next : adj[node])
    {
        if (next == parent)
            continue;
        res += subtreeSize(next, node);
    }
    return subSize[node] = res;
}

// Find centroid (node such that all subtrees are ≤ N/2)
int getCentroid(int node, int parent)
{
    for (int next : adj[node])
    {
        if (next == parent)
            continue;
        if (subSize[next] * 2 > N)
        {
            return getCentroid(next, node);
        }
    }
    return node;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N;
    for (int i = 0; i < N; ++i)
    {
        adj[i].clear();
        subSize[i] = 0;
    }

    for (int i = 0; i < N - 1; ++i)
    {
        int a, b;
        cin >> a >> b;
        --a;
        --b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    subtreeSize(0, -1);
    int centroid = getCentroid(0, -1);
    cout << (centroid + 1) << "\n";

    return 0;
}
