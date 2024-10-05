import java.io.*;
import java.util.*;

// Author : Sulabh Ambule
public class TLE2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Accepted(br);
  }

  /*
   * 
   * 
   * || JAI SHREE RAM ||
   * 
   * 
   */
  private static void Accepted(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int mex = 0; // MEX starts at 0
    int max = n + 1; // The maximum possible MEX is n + 1 since elements are within 0 to n.
    int[] count = new int[max]; // Frequency array to track elements in the current window
    int minMex = max; // To store the minimum MEX encountered

    // Initialize the first window of size m
    for (int i = 0; i < m; i++) {
      if (arr[i] < max) {
        count[arr[i]]++;
        if (arr[i] == mex) {
          // Update the MEX if the current number matches mex
          while (mex < max && count[mex] > 0) {
            mex++;
          }
        }
      }
    }

    // Set the initial minimum MEX to the MEX of the first window
    minMex = mex;

    // Slide the window across the array from index m to n
    for (int i = m; i < n; i++) {
      // Remove the outgoing element from the previous window
      int out = arr[i - m];
      if (out < max) {
        count[out]--;
        if (out < mex) {
          mex = out; // Set mex to the outgoing element
        }
      }

      // Add the incoming element to the current window
      int in = arr[i];
      if (in < max) {
        count[in]++;
        if (in == mex) {
          // Update mex if needed
          while (mex < max && count[mex] > 0) {
            mex++;
          }
        }
      }

      // Update the minimum MEX encountered so far
      minMex = Math.min(minMex, mex);
    }
    System.out.println(minMex);
  }

  // Other code snippets (commented)
}
