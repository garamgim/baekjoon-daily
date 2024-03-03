/*
* SWEA 3234 준환이의 양팔저울
*
* https://data-make.tistory.com/490 답안 참고
* static 변수를 사용하지 않고 함수에 파라미터를 일일이 넘겨주는 방식을 사용
* 공간 효율성은 떨어지지만 시간 효율성은 올라갈 수 있다
* 지역변수에 접근하는 것이 더 빠르기 때문
* */

/*
* static 변수 미사용시 공간효율성이 떨어지는 이유?
*
* static 변수를 사용하면 하나의 변수가 모든 인스턴스에 의해 공유되므로 메모리 사용이 적지만
* 인스턴스 변수는 각 인스턴스마다 별도의 변수가 있으므로 더 많은 메모리를 사용할 수 있다
* https://withlearncode.com/entry/%EC%9E%90%EB%B0%94-%EC%9D%91%EC%9A%A9-Chapter-16-1-%EC%9E%90%EB%B0%94-%EB%A9%94%EB%AA%A8%EB%A6%AC-%EA%B5%AC%EC%A1%B0%EC%99%80-static-%ED%82%A4%EC%9B%8C%EB%93%9C%EC%9D%B4%EB%A1%A0-%EC%A0%95%EB%A6%AC
* */


package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S_3234 {
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] chu = new int[N];
            int[] used = new int[N];
            int totalSum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                chu[i] = Integer.parseInt(st.nextToken());
                totalSum += chu[i];
            }

            ans = 0;
            counter(0, 0, 0, N, totalSum, chu, used);
            System.out.printf("#%d %d\n", tc+1, ans);
        }

    }
    static void counter(int lev, int leftSum, int rightSum, int N, int totalSum, int[] chu, int[] used) {
        if (lev == N) {
            ans++;
            return;
        }

        if (leftSum >= totalSum - leftSum) {
            int leftChu = N - lev;
            ans += (int) (Math.pow(2, leftChu) * factorial(leftChu));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (used[i] == 0) {
                used[i] = 1;
                if (leftSum + chu[i] >= rightSum) {
                    counter(lev + 1, leftSum + chu[i], rightSum, N, totalSum, chu, used);
                }
                if (leftSum >= rightSum + chu[i]) {
                    counter(lev + 1, leftSum, rightSum + chu[i], N, totalSum, chu, used);
                }
                used[i] = 0;
            }
        }
    }

    static int factorial(int n) {
        if (n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
