public class Utility {

  public static void main(String[] args) {

  }

  // ----------------------------------------------------
  // private static int lowerBound(int num, int[] arr) {
  // int n = arr.length;
  // int low = 0, high = n - 1;
  // long ans = -1;
  // while (low <= high) {
  // int mid = (low + high) / 2;
  // if (arr[mid] <= num) {
  // ans = mid;
  // low = mid + 1;
  // } else {
  // high = mid - 1;
  // }
  // }
  // return (int) ans;
  // }

  // ---------------------------------------------------
  // This is used when we use Pair inside the map

  // Map<Pair, Integer> map = new HashMap<>();
  // static class Pair {
  // long first, second;
  // Pair(long first, long second) {
  // this.first = first;
  // this.second = second;
  // }
  // @Override
  // public boolean equals(Object o) {
  // if (this == o) return true;
  // if (o == null || getClass() != o.getClass()) return false;
  // Pair pair = (Pair) o;
  // return first == pair.first && second == pair.second;
  // }
  // @Override
  // public int hashCode() {
  // return (int) (31 * first + second);
  // }
  // }
}
