#include <bits/stdc++.h>
using namespace std;

int n;
vector<vector<int>> graph;

int bfs(int start)
{
    vector<int> visited(n + 1, 0);
    queue<int> q;
    q.push(start);
    visited[start] = 1;
    int level = 0;

    while (!q.empty())
    {
        int size = q.size();
        for (int i = 0; i < size; i++)
        {
            int node = q.front();
            q.pop();

            for (int neighbor : graph[node])
            {
                if (!visited[neighbor])
                {
                    visited[neighbor] = 1;
                    q.push(neighbor);
                }
                else if (neighbor == start)
                {
                    return level + 1;
                }
            }
        }
        level++;
    }
    return -1;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    graph.resize(n + 1);

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            int x;
            cin >> x;
            if (x == 1)
            {
                graph[i].push_back(j);
            }
        }
    }

    for (int i = 1; i <= n; i++)
    {
        int ans = bfs(i);
        if (ans == -1)
            cout << "NO WAY\n";
        else
            cout << ans << "\n";
    }

    return 0;
}
