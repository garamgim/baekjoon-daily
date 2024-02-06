// 백준 1003 피보나치 함수
package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S3_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a == 0) {
                System.out.println("1 0");
            } else {
                int[] fib = new int[a + 2];
                fib[0] = 0;
                fib[1] = 1;
                for (int j = 2; j < a + 1; j++) {
                    fib[j] = fib[j - 1] + fib[j - 2];
                }

                System.out.printf("%d %d\n", fib[a - 1], fib[a]);
            }

        }
    }
}
