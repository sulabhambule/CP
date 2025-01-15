import java.util.List;

public class TreeUtility {
  public static void main(String[] args) {

  }

  static int time = 0;

  // calculte the inTime and outTime of the node to check if two node have the
  // common ancestor
  private static void dfs(int node, List<List<Integer>> adj, int parent, int[] inTime, int[] outTime,
      int[] subTreeSize) {
    inTime[node] = ++time;
    subTreeSize[node] = 1;
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, adj, node, inTime, outTime, subTreeSize);
        subTreeSize[node] += subTreeSize[adjNode];
      }
    }
    outTime[node] = ++time;
  }

  private static boolean isAncestor(int nodeA, int nodeB, int[] inTime, int[] outTime) {
    return (inTime[nodeA] <= inTime[nodeB] && outTime[nodeA] >= outTime[nodeB]);
  }
}
