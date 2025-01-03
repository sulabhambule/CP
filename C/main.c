#include <stdio.h>

void swap(int *a, int *b)
{
  int t = *a;
  *a = *b;
  *b = t;
}

void bubbleSort(int arr[], int n)
{
  for (int i = 0; i < n; i++)
  {
    int swapp = 0;
    for (int j = 0; j < n - i - 1; j++)
    {
      if (arr[j] > arr[j + 1])
      {
        swap(&arr[j], &arr[j + 1]);
        swapp = 1;
      }
    }
    if (swapp == 0)
    {
      break;
    }
  }
}

void mergeSort(int arr[], int left, int right)
{
  if (left < right)
  {
    int mid = left + (right - left) / 2;

    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }
}

void merge(int arr[], int left, int mid, int right)
{
  int n1 = mid - left + 1;
  int n2 = right - mid;
  int L[n1], R[n2];

  for (int i = 0; i < n1; i++)
  {
    L[i] = arr[left + i];
  }
  for (int i = 0; i < n2; i++)
  {
    R[i] = arr[mid + 1 + i];
  }

  int i = 0, j = 0, k = left;

  while (i < n1 && j < n2)
  {
    if (L[i] <= R[j])
    {
      arr[k] = L[i];
      i++;
    }
    else
    {
      arr[k] = R[j];
      j++;
    }
    k++;
  }

  while (i < n1)
  {
    arr[k++] = L[i];
    i++;
  }
  while (j < n2)
  {
    arr[k++] = R[j];
    j++;
  }
}

void quickSort(int arr[], int low, int high)
{
  if (low < high)
  {
    int pivotIndex = partition(arr, low, high);
    quickSort(arr, low, pivotIndex - 1);
    quickSort(arr, pivotIndex + 1, high);
  }
}

int partition(int arr[], int low, int high)
{
  int pivot = arr[high];
  int i = low - 1;

  for (int j = low; j < high; j++)
  {
    if (arr[j] < pivot)
    {
      i++;
      swap(&arr[i], &arr[j]);
    }
  }
  swap(&arr[i + 1], &arr[high]);
  return i + 1;
}

void printArray(int arr[], int n)
{
  for (int i = 0; i < n; i++)
  {
    printf("%d ", arr[i]);
  }
  printf("\n");
}

int main()
{
  int arr[] = {12, 11, 13, 5, 6, 7};
  int n = sizeof(arr) / sizeof(arr[0]);

  printf("Unsorted array:\n");
  printArray(arr, n);

  mergeSort(arr, 0, n - 1);

  printf("Sorted array:\n");
  printArray(arr, n);

  return 0;
}