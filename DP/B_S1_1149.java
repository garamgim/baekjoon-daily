// 백준 1149 RGB 거리
// 완전탐색 (DFS) 시도 했으나 시간초과 -> DP로 해결

package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_S1_1149 {
    static int N;
    static int[][] houses;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        houses = new int[N][N];
        dp = new int[N][N];
        StringTokenizer st = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0] = houses[0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(houses[i][j] + dp[i - 1][(j + 1) % 3], houses[i][j] + dp[i - 1][(j + 2) % 3]);
            }
        }

        int minSum = Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]);
        System.out.println(minSum);
    }
}