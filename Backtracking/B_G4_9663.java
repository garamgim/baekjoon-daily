// 백준 9663 N-Queen
package Backtracking;

import java.io.IOException;
import java.util.Scanner;

public class B_G4_9663 {
    static int N;
    static int cnt;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cnt = 0;
        arr = new int[N];
        queen(0);
        System.out.println(cnt);
    }

    static void queen(int row) {
        if (row == N) {
            cnt++;
            return;
        }

        // i == column
        for (int i = 0; i < N; i++) {
            boolean hasQueen = false;
            for (int j = row - 1; j >= 0; j--) {
                // j == 윗줄들의 인덱스
                // 윗줄에 같은 열이 이미 있거나 || 현재 행 - 윗줄의 행 == 절댓값(해당 열 - 윗줄의 열)
                if (arr[j] == i || (row - j) == Math.abs(i - arr[j])) {
                    hasQueen = true;
                    break;
                }
            }

            if (!hasQueen) {
                arr[row] = i;
                queen(row+1);
                arr[row] = 0;
            }

        }
    }

}


