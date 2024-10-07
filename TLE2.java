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

    int mex = 0; 
    int max = n + 1;
    int[] count = new int[max];
    int minMex = max; 

    for (int i = 0; i < m; i++) {
      if (arr[i] < max) {
        count[arr[i]]++;
        if (arr[i] == mex) {
          while (mex < max && count[mex] > 0) {
            mex++;
          }
        }
      }
    }

    minMex = mex;

    for (int i = m; i < n; i++) {
      int out = arr[i - m];
      if (out < max) {
        count[out]--;
        if (out < mex) {
          mex = out; 
        }
      }

      int in = arr[i];
      if (in < max) {
        count[in]++;
        if (in == mex) {
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
