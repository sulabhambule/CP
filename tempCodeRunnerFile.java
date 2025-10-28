 static int binarySearch(int tar, int[] a) {
    int index = -1;
    int s = 0, e = a.length - 1;
    while (s <= e) {
      int m = (s + e) / 2;
      if (a[m] <= tar) {
        index = m;
        s = m + 1;
      } else {
        e = m - 1;
      }
    }
    return index;
  }