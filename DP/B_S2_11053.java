// 백준 11053 가장 긴 증가하는 부분 수열
// https://buyandpray.tistory.com/73

package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_S2_11053 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] dp = new int[N + 1];
        int top = 1;
        for (int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(String.valueOf(temp[i - 1]));
            if (num > dp[top - 1]) {
                dp[top] = num;
                top++;
            } else {
                int[] sub = Arrays.copyOfRange(dp, 0, top);
                int idx = Arrays.binarySearch(sub, num);
                if (idx < 0) {
                    idx = -idx - 1;
                    dp[idx] = num;
                }
            }
        }
        System.out.println(top - 1);
    }
}
