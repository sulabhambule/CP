import java.util.*;

public class Simple {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();

    in.close();
  }

  static class Edge implements Comparable<Edge> {
    int u, v;
    long w;

    public Edge(int u, int v, long w) {
      this.u = u;
      this.v = v;
      this.w = -w;
    }

    public int compareTo(Edge o) {
      return Long.compare(this.w, o.w);
    }
  }
}
