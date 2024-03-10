// 백준 12015 가장 긴 증가하는 부분 수열 2

package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_G2_12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] dp = new int[N + 1];
        int top = 1;
        for (int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(String.valueOf(temp[i - 1]));
            if (num > dp[top - 1]) {
                dp[top] = num;
                top++;
            } else {
                int idx = binSearch(0, top, num, dp);
                dp[idx] = num;
            }
        }
        System.out.println(top - 1);
    }

    static int binSearch(int low, int high, int key, int[] arr) {
        if (low >= high) {
            if (arr[low] < key) {
                return low + 1;
            }
            return low;
        }

        int mid = (low + high) / 2;
        if (key == arr[mid]) {
            return mid;
        } else if (key < arr[mid]) {
            return binSearch(low, mid - 1, key, arr);
        } else {
            return binSearch(mid + 1, high, key, arr);
        }
    }
}
