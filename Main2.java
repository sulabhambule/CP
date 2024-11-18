import java.util.*;

public class Main2 {
  static class State {
    int pos, jumpPower, powerUps;

    State(int pos, int jumpPower, int powerUps) {
      this.pos = pos;
      this.jumpPower = jumpPower;
      this.powerUps = powerUps;
    }
  }

  public static int minPowerUps(int L, int[][] hurdles, int[][] powerUps) {
    TreeSet<int[]> hurdleSet = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
    hurdleSet.addAll(Arrays.asList(hurdles));

    Map<Integer, List<Integer>> powerUpMap = new HashMap<>();
    for (int[] powerUp : powerUps) {
      powerUpMap.computeIfAbsent(powerUp[0], k -> new ArrayList<>()).add(powerUp[1]);
    }

    PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.powerUps));
    pq.add(new State(1, 1, 0));

    Set<String> visited = new HashSet<>();

    while (!pq.isEmpty()) {
      State curr = pq.poll();
      int pos = curr.pos, jump = curr.jumpPower, powerUpCount = curr.powerUps;

      if (pos >= L)
        return powerUpCount;

      String stateKey = pos + "," + jump;
      if (visited.contains(stateKey))
        continue;
      visited.add(stateKey);

      int maxJump = pos + jump;
      int nextPos = pos + 1;

      for (; nextPos <= maxJump; nextPos++) {
        if (nextPos > L)
          break;

        int[] hurdle = hurdleSet.floor(new int[] { nextPos, Integer.MAX_VALUE });
        if (hurdle != null && hurdle[0] <= nextPos && hurdle[1] >= nextPos)
          continue;

        pq.add(new State(nextPos, jump, powerUpCount));
      }

      if (powerUpMap.containsKey(pos)) {
        for (int powerUpValue : powerUpMap.get(pos)) {
          pq.add(new State(pos, jump + powerUpValue, powerUpCount + 1));
        }
      }
    }

    return -1;
  }
}
