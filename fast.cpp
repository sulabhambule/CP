#include <iostream>
#include <vector>
using namespace std;

void dfs(int node, int parent, vector<vector<int>> &edges, vector<int> &subtreeSize)
{
  // Initialize the subtree size to 1 (counting the current node)
  subtreeSize[node] = 1;
  for (int neighbour : edges[node])
  {
    if (neighbour != parent)
    { // Avoid revisiting the parent node
      dfs(neighbour, node, edges, subtreeSize);
      // Add the subtree size of the child to the current node's subtree size
      subtreeSize[node] += subtreeSize[neighbour];
    }
  }
}

void solve()
{
  int n;
  cin >> n;
  vector<vector<int>> edges(n + 1);
  for (int i = 2; i <= n; i++)
  {
    int u;
    cin >> u; // Read the parent of node i
    edges[u].push_back(i);
    edges[i].push_back(u);
  }

  vector<int> subtreeSize(n + 1, 0);
  dfs(1, -1, edges, subtreeSize);

  cout << n - 1 << " "; // Printing n-1 (example use case)
  for (int i = 2; i <= n; i++)
  {
    cout << subtreeSize[i] - 1 << " "; // Subtract 1 to exclude the node itself if required
  }
  cout << endl;
}

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int T = 1; // Number of test cases, modify if needed
  while (T--)
  {
    solve();
  }

  return 0;
}
