import java.util.*;
import java.io.*;

public class Simple {
     static class FastReader {
          BufferedReader br;
          StringTokenizer st;

          public FastReader() {
               br = new BufferedReader(new InputStreamReader(System.in));
          }

          String next() {
               while (st == null || !st.hasMoreElements()) {
                    try {
                         st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                         e.printStackTrace();
                    }
               }
               return st.nextToken();
          }

          int nextInt() {
               return Integer.parseInt(next());
          }

          long nextLong() {
               return Long.parseLong(next());
          }
     }

     public static void main(String[] args) {
          FastReader fr = new FastReader();
          StringBuilder sb = new StringBuilder();

          int t = fr.nextInt();

          while (t-- > 0) {
               int n = fr.nextInt();
               int q = fr.nextInt();
               int[] a = new int[n + 1];
               long[] prefixSum = new long[n + 1];
               long[] prefixXor = new long[n + 1];

               for (int i = 1; i <= n; i++) {
                    a[i] = fr.nextInt();
                    prefixSum[i] = prefixSum[i - 1] + a[i];
                    prefixXor[i] = prefixXor[i - 1] ^ a[i];
               }

               int L = fr.nextInt();
               int R = fr.nextInt();

               int bestL = -1, bestR = -1;
               long maxF = prefixSum[R] - prefixSum[L - 1] - (prefixXor[R] ^ prefixXor[L - 1]);

               for (int i = L; i <= R; i++) {
                    int l = i, r = R, ans = -1;
                    while (l <= r) {
                         int mid = (l + r) / 2;
                         long sumLR = prefixSum[mid] - prefixSum[i - 1];
                         long xorLR = prefixXor[mid] ^ prefixXor[i - 1];
                         long f = sumLR - xorLR;

                         if (f == maxF) {
                              ans = mid;
                              r = mid - 1;
                         } else {
                              l = mid + 1;
                         }
                    }
                    if (ans != -1) {
                         if (bestL == -1 || (ans - i + 1 < bestR - bestL + 1)) {
                              bestL = i;
                              bestR = ans;
                         }
                    }
               }

               sb.append(bestL).append(" ").append(bestR).append("\n");
          }
          System.out.print(sb);
     }
}
