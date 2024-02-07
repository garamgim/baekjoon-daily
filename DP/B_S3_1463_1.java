// 백준 1463 1로 만들기
// 반례 642(정답: 10) -> 321 -> 320 -> 160 -> 80 -> 40 -> 20 -> 10 -> 9 -> 3 -> 1

package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S3_1463_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i-1] + 1;
            if (i % 3 == 0) {
                arr[i] = Math.min(arr[i/3]+1, arr[i]);
            }
            if (i % 2 == 0) {
                arr[i] = Math.min(arr[i/2]+1, arr[i]);
            }
        }
        System.out.println(arr[n]);
    }
}
