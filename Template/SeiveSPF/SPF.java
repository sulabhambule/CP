package Template.SeiveSPF;
import java.util.Scanner;

public class SPF {
    public static void main(String[] args) {
        spfOfi();
    }

    static int N = 100000;
    static int[] spf = new int[N + 1]; // store the smallest prime factor of i in spf[i].

    private static void spfOfi() {
        Scanner in = new Scanner(System.in);

        // Initialize the smallest prime factor for every number to itself
        for (int i = 2; i <= N; i++) {
            spf[i] = i;
        }

        // Sieve of Eratosthenes modified to find smallest prime factor
        for (int i = 2; i * i <= N; i++) {
            if (spf[i] == i) { // If i is prime
                for (int j = i * i; j <= N; j += i) {
                    if (spf[j] == j) { // Mark spf[j] with the smallest prime factor
                        spf[j] = i;
                    }
                }
            }
        }

        int q = in.nextInt(); // Number of queries
        while (q-- > 0) {
            int n = in.nextInt();
            while (n != 1) {
                System.out.print(spf[n] + " ");
                n /= spf[n];
            }
            System.out.println();
        }

        in.close();
    }
}
