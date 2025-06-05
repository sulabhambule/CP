import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int t = Integer.parseInt(st.nextToken());
    while (t-- > 0) {

      int n = in.nextInt();
      long answer = 0;
      for (int i = 0; i < n; i++) {
        for (int jj = 1; i + jj - 1 < n; jj++) {
          int j = i + jj - 1;
          if (a[j] < jj) {
            break;
          }
          answer++;
        }
      }
      System.out.println(answer);
    }
  }
}
