#include <bits/stdc++.h>
using namespace std;

#define int long long
#define MOD 1000000007
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

void dfs(int curr, int parent, vector<vector<int>> &edges, vector<int> &dist) {
    if (parent == -1) {
        dist[curr] = 0;
    } else {
        dist[curr] = dist[parent] + 1;
    }

    for (int neighbor : edges[curr]) {
        if (neighbor != parent) {
            dfs(neighbor, curr, edges, dist);
        }
    }
}

int farthestNode(int n, vector<int> &dist) {
    int farthest = 1; // Nodes are numbered from 1
    for (int i = 1; i <= n; i++) {
        if (dist[i] > dist[farthest]) {
            farthest = i;
        }
    }
    return farthest;
}

void solve() {
    int n;
    cin >> n;
    vector<vector<int>> edges(n + 1);

    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        edges[u].push_back(v);
        edges[v].push_back(u);
    }

    vector<int> distX(n + 1, -1);
    vector<int> distY(n + 1, -1);

    // First DFS from an arbitrary node to find the farthest node
    dfs(1, -1, edges, distX);
    int y = farthestNode(n, distX);

    // Second DFS from the farthest node found in the first DFS
    dfs(y, -1, edges, distY);
    int z = farthestNode(n, distY);

    // The diameter of the tree is the distance to the farthest node in distY
    cout << distY[z] << endl;
}

int32_t main() {
    FAST_IO;
    int T = 1;
    // cin >> T; // Uncomment this line if there are multiple test cases
    while (T--) {
        solve();
    }
    return 0;
}
