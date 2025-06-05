import java.io.*;
import java.util.*;

public class first1fromNode1toB {
  static Scanner in = new Scanner(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static List<Integer>[] adj;
  static int[] size, heavy, pos, linear_tree, head, parent, depth, value, nodeAtPos;
  static int index = 0;
  static int[] segTree;

  public static void main(String[] hi) {
    int n = in.nextInt(), q = in.nextInt();
    adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    size = new int[n + 1];
    head = new int[n + 1];
    heavy = new int[n + 1];
    pos = new int[n + 1];
    linear_tree = new int[n + 1];
    parent = new int[n + 1];
    depth = new int[n + 1];
    nodeAtPos = new int[n + 1];
    value = new int[n + 1]; // initialy all the nodes are white so 0 , black equal to the 1.
    segTree = new int[4 * n];
    for (int i = 1; i < n; i++) {
      int a = in.nextInt(), b = in.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }
    dfs(1, 0); // calculate the parentm size heavy
    hld(1, 1); // calculate the head of the node.

    build(0, 0, index - 1);
    for (int i = 0; i < q; i++) {
      int type = in.nextInt();
      if (type == 0) {
        int node = in.nextInt(); // want to toggle the color of the node
        update(0, 0, index - 1, pos[node]);
      } else {
        int node = in.nextInt(); // 1st black node from 1 to node path
        out.println(pathQuery(1, node));
      }
    }
    in.close();
    out.flush();
    out.close();
  }

  static void build(int node, int start, int end) {
    if (start == end) {
      segTree[node] = linear_tree[start];
      return;
    }

    int mid = (start + end) / 2;
    build(2 * node + 1, start, mid);
    build(2 * node + 2, mid + 1, end);
    segTree[node] = segTree[2 * node + 1] + segTree[2 * node + 2];
  }

  static void update(int node, int start, int end, int idx) {
    if (start == end) {
      segTree[node] ^= 1;
      linear_tree[idx] ^= 1; // <-- Important fix!
      return;
    }

    int mid = (start + end) / 2;
    if (idx <= mid)
      update(2 * node + 1, start, mid, idx);
    else
      update(2 * node + 2, mid + 1, end, idx);
    segTree[node] = segTree[2 * node + 1] + segTree[2 * node + 2];
  }

  static int getFirstBlack(int node, int start, int end, int l, int r) {
    if (segTree[node] == 0 || r < start || l > end)
      return -1;
    if (start == end)
      return start;
    int mid = (start + end) / 2;
    int left = getFirstBlack(2 * node + 1, start, mid, l, r);
    if (left != -1)
      return left;
    return getFirstBlack(2 * node + 2, mid + 1, end, l, r);
  }

  static int pathQuery(int u, int v) {
    int best = -1;

    while (head[u] != head[v]) {

      int res = getFirstBlack(0, 0, index - 1, pos[head[v]], pos[v]);
      if (res != -1) {
        best = res;
      }

      v = parent[head[v]];
    }

    int res = getFirstBlack(0, 0, index - 1, pos[u], pos[v]);
    if (res != -1) {
      best = res;
    }

    return best == -1 ? -1 : getOriginalNode(best);
  }

  static int getOriginalNode(int idx) {
    return nodeAtPos[idx];
  }

  static void hld(int node, int chain) {
    head[node] = chain;
    linear_tree[index] = value[node];
    nodeAtPos[index] = node;
    pos[node] = index++;
    if (heavy[node] != 0) {
      hld(heavy[node], chain);
    }
    for (int adjNode : adj[node]) {
      if (adjNode != heavy[node] && adjNode != parent[node]) {
        hld(adjNode, adjNode);
      }
    }
  }

  static void dfs(int node, int par) {
    parent[node] = par;
    depth[node] = depth[par] + 1;
    size[node] = 1;

    for (int adjNode : adj[node]) {
      if (adjNode != par) {
        dfs(adjNode, node);
        size[node] += size[adjNode];

        if (size[adjNode] > size[heavy[node]]) {
          heavy[node] = adjNode;
        }
      }
    }
  }
}
