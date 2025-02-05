#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <tuple>
#include <algorithm>

using namespace std;

const long long INF = LLONG_MAX;

struct Edge
{
    int to;
    long long weight;
};

vector<long long> dijkstra(int start, int n, const vector<vector<Edge>> &adj)
{
    vector<long long> dist(n + 1, INF);
    dist[start] = 0;

    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    pq.push({0, start});

    while (!pq.empty())
    {
        long long d = pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (d > dist[node])
            continue;

        for (const auto &edge : adj[node])
        {
            int next = edge.to;
            long long weight = edge.weight;

            if (dist[node] + weight < dist[next])
            {
                dist[next] = dist[node] + weight;
                pq.push({dist[next], next});
            }
        }
    }

    return dist;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n, m;
    cin >> n >> m;

    vector<vector<Edge>> adj(n + 1), revAdj(n + 1);
    vector<tuple<int, int, long long>> edges;

    for (int i = 0; i < m; ++i)
    {
        int a, b;
        long long c;
        cin >> a >> b >> c;
        adj[a].push_back({b, c});
        revAdj[b].push_back({a, c});
        edges.push_back(make_tuple(a, b, c)); // Using make_tuple here
    }

    vector<long long> dist1 = dijkstra(1, n, adj);
    vector<long long> distN = dijkstra(n, n, revAdj);

    long long minCost = dist1[n];

    for (const auto &edge : edges)
    {
        int u = get<0>(edge), v = get<1>(edge);
        long long w = get<2>(edge);

        if (dist1[u] != INF && distN[v] != INF)
        {
            minCost = min(minCost, dist1[u] + (w / 2) + distN[v]);
        }
    }

    cout << minCost << "\n";

    return 0;
}
