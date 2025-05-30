#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int dx[] = {-1, 0, +1, 0};
int dy[] = {0, +1, 0, -1};
char dirChar[] = {'U', 'R', 'D', 'L'};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n, m;
    cin >> n >> m;
    vector<string> grid(n);
    for (int i = 0; i < n; i++)
    {
        cin >> grid[i];
    }

    vector<vector<int>> dist(n, vector<int>(m, INF));
    queue<tuple<int, int, int>> q;

    // Monster BFS
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 'M')
            {
                q.emplace(i, j, 0);
            }
        }
    }

    while (!q.empty())
    {
        auto [x, y, t] = q.front();
        q.pop();
        if (dist[x][y] <= t)
            continue;
        dist[x][y] = t;
        for (int d = 0; d < 4; d++)
        {
            int nx = x + dx[d], ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '.')
            {
                q.emplace(nx, ny, t + 1);
            }
        }
    }

    // Player BFS
    vector<vector<bool>> visited(n, vector<bool>(m, false));
    vector<vector<int>> parent(n, vector<int>(m, -1));
    int startX = -1, startY = -1, endX = -1, endY = -1;

    for (int i = 0; i < n && startX == -1; i++)
    {
        for (int j = 0; j < m && startY == -1; j++)
        {
            if (grid[i][j] == 'A')
            {
                startX = i, startY = j;
                q.emplace(i, j, 0);
                visited[i][j] = true;
                break;
            }
        }
    }

    while (!q.empty())
    {
        auto [x, y, t] = q.front();
        q.pop();
        if (x == 0 || y == 0 || x == n - 1 || y == m - 1)
        {
            endX = x, endY = y;
            break;
        }

        for (int d = 0; d < 4; d++)
        {
            int nx = x + dx[d], ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                grid[nx][ny] == '.' && !visited[nx][ny] && t + 1 < dist[nx][ny])
            {
                visited[nx][ny] = true;
                parent[nx][ny] = d;
                q.emplace(nx, ny, t + 1);
            }
        }
    }

    if (endX == -1)
    {
        cout << "NO\n";
    }
    else
    {
        cout << "YES\n";
        string path;
        int x = endX, y = endY;
        while (x != startX || y != startY)
        {
            int d = parent[x][y];
            path += dirChar[d];
            x -= dx[d];
            y -= dy[d];
        }
        reverse(path.begin(), path.end());
        cout << path.size() << '\n'
             << path << '\n';
    }

    return 0;
}
