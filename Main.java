import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int test_cases = sc.nextInt();

    while (test_cases-- > 0) {
      long n = sc.nextLong();
      long k = sc.nextLong();

      // Compute the lucky value using the recursive function
      Pair result = computeLucky(1, n, k);

      // Output the final lucky value
      System.out.println(result.first);
    }

    sc.close();
  }

  // Pair class to hold two values
  static class Pair {
    long first;
    int second;

    Pair(long first, int second) {
      this.first = first;
      this.second = second;
    }
  }

  // Function to recursively compute the lucky value
  static Pair computeLucky(long left, long right, long threshold) {

    if (right - left + 1 < threshold) {
      return new Pair(0, 0);
    }
    if (right - left + 1 == 1) {
      return new Pair(left, 1);
    }
    long mid = left + (right - left) / 2;

    // If the segment length is odd
    if ((right - left + 1) % 2 == 1) {
      Pair leftResult = computeLucky(left, mid - 1, threshold);

      long totalLucky = mid + 2 * leftResult.first + mid * leftResult.second;
      int totalSegments = 2 * leftResult.second + 1;

      return new Pair(totalLucky, totalSegments);
    } else {

      Pair leftResult = computeLucky(left, mid, threshold);
      long totalLucky = 2 * leftResult.first + mid * leftResult.second;
      int totalSegments = 2 * leftResult.second;

      return new Pair(totalLucky, totalSegments);
    }
  }
}
