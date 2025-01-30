#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

struct Pair
{
    int node;
    long long weight;
    Pair(int a, long long c) : node(a), weight(c) {}
    bool operator>(const Pair &p) const
    {
        return weight > p.weight;
    }
};

void dijstra(int start, const vector<vector<Pair>> &adj, vector<long long> &dist)
{
    priority_queue<Pair, vector<Pair>, greater<Pair>> pq;
    dist[start] = 0;
    pq.push(Pair(start, 0));

    while (!pq.empty())
    {
        Pair p = pq.top();
        pq.pop();
        int node = p.node;
        long long edgeW = p.weight;

        if (edgeW > dist[node])
        {
            continue;
        }

        for (const Pair &neighbour : adj[node])
        {
            int nextNode = neighbour.node;
            long long weight = neighbour.weight;

            if (weight + dist[node] < dist[nextNode])
            {
                dist[nextNode] = weight + dist[node];
                pq.push(Pair(nextNode, dist[nextNode]));
            }
        }
    }
}

int main()
{
    int n, m;
    cin >> n >> m;

    vector<vector<Pair>> adj(n + 1);

    for (int i = 0; i < m; i++)
    {
        int a, b;
        long long c;
        cin >> a >> b >> c;
        adj[a].push_back(Pair(b, c));
    }

    vector<long long> dist(n + 1, LLONG_MAX);
    dijstra(1, adj, dist);

    for (int i = 1; i <= n; i++)
    {
        cout << dist[i] << " ";
    }
    cout << endl;

    return 0;
}
