// 백준 10830 행렬 제곱

package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_G4_10830 {
    static int N;
    static long B;
    static long[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        B = Long.parseLong(temp[1]);
        matrix = new long[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp2 = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(temp2[j]);
            }
        }
        long[][] answer = divide(B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j] % 1000);
                if (j == N - 1) break;
                sb.append(" ");
            }
            if (i == N - 1) break;
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static long[][] divide(long n) {
        if (n == 1) {
            return matrix;
        }
        long[][] smaller = divide(n/2);
        return (n % 2 == 0)? mul(smaller, smaller) : mul(mul(smaller, smaller), matrix);
    }

    static long[][] mul(long[][] arr1, long[][] arr2) {
        long[][] result = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += (arr1[i][k] * arr2[k][j]);
                    result[i][j] %= 1000;
                }

            }
        }
        return result;
    }
}

