import java.io.*;
import java.util.*;

public class Main {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();

    public static void main(String[] Hi) {
        int T = in.nextInt();
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    static void solve() {
        String s = in.next();
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        List<Integer> ans = new ArrayList<>();

        int num = sortedArr[0];
        int idx = -1;
        for (int ii = 0; ii < n; ii++) {
            if (arr[ii] == num) {
                idx = ii;
            }
        }
        List<Integer> ls = new ArrayList<>();
        for (int j = 0; j <= idx; j++) {
            if (arr[j] != num) {
                ls.add(Math.min(9, arr[j] + 1));
            } else {
                ans.add(num);
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int jj = idx + 1; jj < n; jj++) {
            // set.add(arr[jj]);
            map.put(arr[jj], jj);
        }
        idx++;
        int nn = num + 1;
        while (nn <= 9) {
            if (map.containsKey(nn)) {
                int idx22 = map.get(nn);
                if (idx22 >= idx) {
                    while (idx <= idx22) {
                        if (arr[idx] == nn && nn != 9) {
                            ls.add(nn);
                        } else {
                            ls.add(Math.min(9, arr[idx] + 1));
                        }
                        idx++;
                    }
                }
                nn++;
            } else {
                nn++;
            }
        }
        Collections.sort(ls);
        ans.addAll(ls);
        for (int val : ans)
            System.out.print(val);
        System.out.println();
    }

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
    }
}
