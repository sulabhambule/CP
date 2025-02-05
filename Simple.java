import java.util.*;

public class Simple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); 

        while (t-- > 0) {
            int n = in.nextInt();
            String a = in.next();

            int[] sufCnt = new int[n + 1]; 
            for (int i = n - 1; i >= 0; i--) {
                sufCnt[i] = sufCnt[i + 1] + (a.charAt(i) == '1' ? 1 : 0);
            }

            int prefCnt = 0; 
            int optAns = -1;
            int optDist = 2 * n;

            for (int i = 0; i <= n; i++) {
                if (prefCnt >= (i + 1) / 2 && sufCnt[i] >= (n - i + 1) / 2) {
                    int dist = Math.abs(n - 2 * i);
                    if (dist < optDist) {
                        optDist = dist;
                        optAns = i;
                    }
                }
                if (i != n) {
                    prefCnt += (a.charAt(i) == '0' ? 1 : 0);
                }
            }

            System.out.println(optAns);
        }
        in.close();
    }
}
