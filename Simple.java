import java.util.*;

public class Simple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            String s = in.next();
            int c = 0;
            for (char ch : s.toCharArray()) {
                if (ch == '1')
                    c++;
            }
            if (c == 2) {
                boolean f = true;
                for (int i = 1; i < n; i++) {
                    if (s.charAt(i) == '1' && s.charAt(i - 1) == '1') {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");

                }
            } else {
                if (c % 2 == 1) {
                    System.out.println("NO");
                } else {
                    System.out.println("YES");
                }
            }
        }
    }
}
