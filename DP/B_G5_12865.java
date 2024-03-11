// 백준 12865 평범한 배낭
package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_G5_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);
        int[][] dp = new int[N+1][K+1];
        int[][] w = new int[N+1][2];
        for (int i = 0; i < N; i++) {
            String[] o = br.readLine().split(" ");
            int wt = Integer.parseInt(o[0]);
            int vl = Integer.parseInt(o[1]);
            w[i+1] = new int[]{wt, vl};
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (w[i][0] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(w[i][1] + dp[i-1][j-w[i][0]], dp[i-1][j]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        System.out.println(ans);
    }
}
