#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<pair<int, ll>>> adj(n + 1);
    for (int i = 0; i < m; i++)
    {
        int a, b;
        ll c;
        cin >> a >> b >> c;
        adj[a].emplace_back(b, c);
    }

    // For each node, store k shortest distances found so far in a max heap
    vector<priority_queue<ll>> dist(n + 1);
    priority_queue<pair<ll, int>, vector<pair<ll, int>>, greater<pair<ll, int>>> pq;

    pq.push({0, 1});
    dist[1].push(0);

    while (!pq.empty())
    {
        auto [cost, node] = pq.top();
        pq.pop();

        // If we already have k shortest distances for node and cost is bigger than
        // the largest stored, we can skip
        if (dist[node].size() == k && cost > dist[node].top())
            continue;

        for (auto &[nextNode, edgeCost] : adj[node])
        {
            ll nextCost = cost + edgeCost;

            if ((int)dist[nextNode].size() < k)
            {
                dist[nextNode].push(nextCost);
                pq.push({nextCost, nextNode});
            }
            else if (dist[nextNode].top() > nextCost)
            {
                dist[nextNode].pop();
                dist[nextNode].push(nextCost);
                pq.push({nextCost, nextNode});
            }
        }
    }

    // Extract k shortest distances to node n (max heap - largest on top)
    vector<ll> ans;
    while (!dist[n].empty())
    {
        ans.push_back(dist[n].top());
        dist[n].pop();
    }
    sort(ans.begin(), ans.end());

    for (ll x : ans)
        cout << x << " ";
    cout << "\n";

    return 0;
}
