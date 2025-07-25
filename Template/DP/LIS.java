
import java.util.*;

public class LIS {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = in.nextInt();
    List<Integer> lis = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();
    int[] insertedat = new int[n]; // insertedat[i] is gonna be store the length at which the number arr[i] is inserted.
    for (int i = 0; i < n; i++) {
      if (lis.isEmpty() || lis.get(lis.size() - 1) < arr[i]) {
        lis.add(arr[i]);
        insertedat[i] = lis.size();
      } else {
        int index = Collections.binarySearch(lis, arr[i]);
        if (index < 0) {
          index = -(index + 1);
        }
        lis.set(index, arr[i]);
        insertedat[i] = index + 1;
      }
    }
    int currLen = lis.size();
    for (int i = n - 1; i >= 0; i--) {
      if (insertedat[i] == currLen) {
        ans.add(arr[i]);
        currLen--;
      }
    }
    for (int i = ans.size() - 1; i >= 0; i--) {
      System.out.print(ans.get(i) + " ");
    }
  }
}
