
import java.util.*;

class Fenwick {
  private final int n;
  private final int[] tree;

  public Fenwick(int size) {
    this.n = size;
    this.tree = new int[n + 1];
  }

  public void update(int i, int delta) {
    for (i++; i <= n; i += i & -i) {
      tree[i] += delta;
    }
  }

  public int query(int i) {
    int s = 0;
    for (i++; i > 0; i -= i & -i) {
      s += tree[i];
    }
    return s;
  }

  public int queryRange(int l, int r) {
    if (l > r)
      return 0;
    return query(r) - (l == 0 ? 0 : query(l - 1));
  }
}

public class Solution {

  public int[] popcountDepth(long[] nums, long[][] queries) {
    int n = nums.length;

    Fenwick[] fw = new Fenwick[6];
    // fenwick tree of ith node is gonna be store the tree for the kth depth
    for (int k = 0; k <= 5; k++) {
      fw[k] = new Fenwick(n);
    }

    int[] depth = new int[n];
    for (int i = 0; i < n; i++) {
      depth[i] = getDepth(nums[i]);
      fw[depth[i]].update(i, +1);
    }

    List<Integer> answers = new ArrayList<>();
    for (long[] q : queries) {
      int type = (int) q[0];
      if (type == 1) {
        int l = (int) q[1];
        int r = (int) q[2];
        int k = (int) q[3];
        answers.add(fw[k].queryRange(l, r));
      } else {
        int idx = (int) q[1];
        long val = q[2];
        fw[depth[idx]].update(idx, -1);
        nums[idx] = val;
        depth[idx] = getDepth(val);
        fw[depth[idx]].update(idx, +1);
      }
    }

    int m = answers.size();
    int[] result = new int[m];
    for (int i = 0; i < m; i++) {
      result[i] = answers.get(i);
    }
    return result;
  }

  private int getDepth(long x) {
    int d = 0;
    while (x != 1) {
      x = Long.bitCount(x);
      d++;
    }
    return d;
  }
}
