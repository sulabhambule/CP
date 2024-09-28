import java.util.Scanner;

public class SIEVELargeInpuut {
  public static void main(String[] args) {
    
  } 
  
  int N = (int) 1e6;
  boolean[] sieve = new boolean[N + 1];
  private void seive() {
    for(int i = 2; i <= N; i++) {
      sieve[1] = true;
    }
    for(int i = 2; i * i <= N; i++) {
      if(!sieve[i]) {
        continue;
      }   
      for(int j = i * i; j <= N; j += i) {
        sieve[i] = false; /// not prime as the divisor is j
      }
    }

    int l = 10000, r = (int)1e9; // take the input

    int size = r - l + 1;
    boolean[] dummy = new boolean[size];

    for(int i = 0; i < size; i++) {
      dummy[i] = true;
    }

    for(int i = 2; i * i <= r; i++) {
      if(sieve[i]) {
        int initial = (l / i) * i;
        if(initial < l) {
          initial += i;
        }
        initial = Math.max(initial, i * i);
        for(int j = initial - 1; j < size; j += i) {
          dummy[j] = false;
        }
      }
    }

    int count = 0;

    for(int i = 0; i < size; i++) {
      if(dummy[i]) {
        count++;
      }
    }

    System.out.println(count);
  }
}

// code is to give the count of primes in between range l -- r
// this is for large number in orders of 1e9.

// advanced seive
