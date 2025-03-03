package Template.SeiveSPF;
public class sieve {
  public static void main(String[] args) {
    
  }

  int N = (int) 1e6;
  boolean[] arr = new boolean[N + 1];
  private void seive() {
    for(int i = 2; i <= N; i++) {
      arr[1] = true;
    }

    for(int i = 2; i * i <= N; i++) {
      if(!arr[i]) {
        continue;
      }   
      for(int j = i * i; j <= N; j += i) {
        arr[i] = false; /// not prime as the divisor is j
      }
    }

    // for number = i , if (arr[i] == true) --> "Prime number" 
    // else not  prime
  }
}
