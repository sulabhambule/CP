import java.util.*;

public class Simple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int m = in.nextInt();
            String s = in.next();
            int[] count = new int[k];
            int block = 0;
            HashSet<Character> set = new HashSet<>();
            String ans = "";
            for (int i = 0; i < m; i++) {
                char ch = s.charAt(i);
                count[ch - 'a']++;
                set.add(ch);
                if (set.size() == k) {
                    block++;
                    ans += ch;
                    set = new HashSet<>();
                }
            }
            if (ans.length() < n) {
                System.out.println("NO");
                for (int i = 0; i < k; i++) {
                    char ch = (char) (i + 'a');
                    if (!set.contains(ch)) {
                        while (ans.length() < n) {
                            ans += ch;
                        }
                        break;
                    }
                }
                System.out.println(ans);
            } else {
                System.out.println("YES");
            }
        }
    }
}