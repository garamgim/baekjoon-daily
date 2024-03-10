// 백준 1654 랜선 자르기
// https://st-lab.tistory.com/269

package Searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S2_1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        long low = 0;
        long high = (long) Math.pow(2, 32);
        long mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;
            int cnt = 0;
            for (long num : arr) {
                cnt += (int) (num / mid);
            }
            if (cnt < K) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(high);
    }
}
