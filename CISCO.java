public class CISCO {
  public static void main(String[] args) {
    int[] arr = { 9, 2, 3, 4, 5, 1, 1, 1, 1, 1, 19, 190 };
    // mergeSort(arr, 0, arr.length - 1);
    // bubbleSort(arr);
    // selectionSort(arr);
    insertionSort(arr);
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }

  public static void mergeSort(int[] arr, int left, int right) {
    if (left >= right)
      return;
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  public static void merge(int[] arr, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int k = 0;
    int i = left, j = mid + 1;

    while (i <= mid && j <= right) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    while (j <= right) {
      temp[k++] = arr[j++];
    }

    for (i = left, k = 0; i <= right; i++) {
      arr[i] = temp[k++];
    }
  }

  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      boolean swapped = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }
      }
      if (!swapped)
        break;
    }
  }

  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[min]) {
          min = j;
        }
      }
      int tt = arr[i];
      arr[i] = arr[min];
      arr[min] = tt;
    }
  }

  public static void quickSort(int[] arr, int left, int right) {
    if (left < right) {
      int pivot = findP(arr, left, right);
      quickSort(arr, left, pivot - 1);
      quickSort(arr, pivot + 1, right);
    }
  }

  private static int findP(int[] arr, int left, int right) {
    int p = arr[right];
    int i = left - 1;
    for (int j = left; j < right; j++) {
      if (arr[j] < p) {
        i++;
        int t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;
      }
    }
    int tt = arr[i + 1];
    arr[i + 1] = arr[right];
    arr[right] = tt;
    return i + 1;
  }

  public static void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = key;
    }
  }
}
