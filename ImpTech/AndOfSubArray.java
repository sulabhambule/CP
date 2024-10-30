package ImpTech;

import java.util.*;

// Author : Sulabh Ambule
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

// the arr is given , a1, a2,a3,a4,a5,a6, a7, a8, a9...
// now if we have to find the and for the query l --> r
// we will precompute 

public class AndOfSubArray {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int cases = in.nextInt();
    while (cases-- > 0) {
      Accepted(in);
    }
    in.close();
  }

  private static void Accepted(Scanner in) {
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = in.nextInt();
    }

    // the below code snippet -> it mke the prefix array in which we prefix[n][30]
    // --> which gonna be store the which jth bit is set int arr[i].

    int[][] prefix = new int[n][30];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 30; j++) {
        if ((arr[i] & (1 << j)) == 0) {
          prefix[i][j] = 1; // if the bit is not set then we will store 1; why ?? look below
        } else {
          prefix[i][j] = 0;
        }
      }
    }

    // id the bit is not set then we are storing 1 in that perticualr position
    // because if any bit gets unset then it never be going to set so the bit is set
    // we store 0 so if we use pefix sum then sum will not change as the sum of 0 +
    // 0 + 0 + 0 = 0

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < 30; j++) {
        prefix[i][j] += prefix[i - 1][j]; // prefix sum approach
      }
    }

    int q = in.nextInt();
    for (int i = 0; i < q; i++) {
      int l = in.nextInt() - 1; // adjust according to the 0 or 1 based indexing.
      int r = in.nextInt() - 1;

      int AndFromRangeLtoR = 0;
      for (int j = 0; j < 30; j++) {
        int setBit = prefix[r][j] - (l == 0 ? 0 : prefix[l - 1][j]);
        // if the setBit is zer means from l -> r the bit of jth position is set
        if (setBit == 0) {
          AndFromRangeLtoR += (1 << j); // 2^j
        }
      }

      System.out.println(AndFromRangeLtoR);
    }
  }
}
