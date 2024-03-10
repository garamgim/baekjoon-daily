package DP;

// 백준 11722 가장 긴 감소하는 부분 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_S2_11722 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = N-1; i >= 0; i--) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];
        int top = 1;
        for (int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(String.valueOf(arr[i - 1]));
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
