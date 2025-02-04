#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

typedef pair<long long, int> pii;

const long long INF = 1e12;

void dijkstra(int start, vector<long long> &dist, const vector<vector<pair<int, long long>>> &adj)
{
    dist[start] = 0;
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    pq.push({0, start});

    while (!pq.empty())
    {
        long long distance = pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (distance > dist[node])
        {
            continue;
        }

        for (const auto &edge : adj[node])
        {
            int adjNode = edge.first;
            long long edgeWeight = edge.second;

            if (dist[node] + edgeWeight < dist[adjNode])
            {
                dist[adjNode] = dist[node] + edgeWeight;
                pq.push({dist[adjNode], adjNode});
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, long long>>> adj(n + 1);

    // Read the edges
    for (int i = 0; i < m; i++)
    {
        long long a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back({b, c});
        adj[b].push_back({a, c});
    }

    // Initialize distances for both directions
    vector<long long> dist1(n + 1, INF);
    vector<long long> dist2(n + 1, INF);

    // Run Dijkstra's algorithm from node 1 and node n
    dijkstra(1, dist1, adj);
    dijkstra(n, dist2, adj);

    long long ans = INF;

    // Checking each edge for the minimum distance with coupon use
    for (int i = 1; i <= n; i++)
    {
        for (const auto &adJ : adj[i])
        {
            int node = adJ.first;
            long long wght = adJ.second;
            ans = min(ans, dist1[i] + (wght / 2) + dist2[node]);
            ans = min(ans, dist1[node] + (wght / 2) + dist2[i]);
        }
    }

    cout << ans << endl;

    return 0;
}
