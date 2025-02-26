import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int k = in.nextInt();
            if (k == 0) {
                System.out.println(1);
                System.out.println("0 0");
                continue;
            }
            List<Integer> ls = new ArrayList<>();
            int rem = k;
            while (rem > 0) {
                for (int m = 500; m >= 2; m--) {
                    int tri = m * (m - 1) / 2;
                    if (tri <= rem) {
                        ls.add(m);
                        rem -= tri;
                        break;
                    }
                }
            }
            int total = 0;
            for (int g : ls)
                total += g;
            System.out.println(total);
            for (int i = 0; i < ls.size(); i++) {
                int g = ls.get(i);
                int baseX = i * 1000;
                int y = i;
                for (int j = 0; j < g; j++) {
                    System.out.println((baseX + j) + " " + y);
                }
            }
        }
        in.close();
    }
}
