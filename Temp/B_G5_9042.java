package Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 두 수의 합
// 스터디 3번
// https://www.acmicpc.net/problem/9024

public class B_G5_9042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] temp1 = br.readLine().split(" ");
            int N = Integer.parseInt(temp1[0]);
            int K = Integer.parseInt(temp1[1]);

            String[] temp2 = br.readLine().split(" ");
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(temp2[j]);
            }
            Arrays.sort(arr);

            int ansDiff = Integer.MAX_VALUE;
            int ansCnt = 0;

            for (int j = 0; j < N; j++) {
                int curr  = arr[j];
                int l = j + 1;
                int r = N - 1;
                int m;
                int target = K - curr;
//                System.out.println("curr = " + curr);
//                System.out.println("target = " + target);
                while (l < r) {
                    m = (l + r) / 2;
//                    System.out.println(arr[m]);
                    if (arr[m] == target) {
                        if (ansDiff != 0) {
                            ansDiff = 0;
                            ansCnt = 1;
                        } else {
                            ansCnt++;
                        }
                        break;
                    } else if (arr[m] > target) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }
                int currDiff = Math.abs(K - (curr + arr[r]));
                if (currDiff < ansDiff) {
                    ansDiff = currDiff;
                    ansCnt = 1;
                } else if (currDiff == ansDiff) {
                    ansCnt++;
                }
            }
            System.out.println(ansCnt);
        }
    }
}
