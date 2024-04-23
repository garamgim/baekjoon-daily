// 백준 4781 사탕 가게

package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class B_G4_4781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String[] info = br.readLine().split(" ");
            int N = Integer.parseInt(info[0]);
            int M = (int) (Double.parseDouble(info[1]) * 100 + 0.5);
            if (N == 0 && M == 0) break;

            Candy[] candies = new Candy[N + 1];
            candies[0] = new Candy(0, 0);
            for (int i = 1; i <= N; i++) {
                String[] temp = br.readLine().split(" ");
                int cal = Integer.parseInt(temp[0]);
                int price = (int) (Double.parseDouble(temp[1]) * 100 + 0.5);
                candies[i] = new Candy(cal, price);
            }

            Arrays.sort(candies);

            int[] memo = new int[M + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    Candy candy = candies[i];
                    if (candy.price <= j) {
                        memo[j] = Math.max(candy.cal + memo[j - candy.price], memo[j]);
                    }
                }
            }

            sb.append(memo[M]).append("\n");
        }
        System.out.println(sb);
    }

    static class Candy implements Comparable<Candy> {
        int cal, price;

        public Candy(int cal, int price) {
            this.cal = cal;
            this.price = price;
        }

        @Override
        public int compareTo(Candy o) {
            return Integer.compare(price, o.price);
        }
    }
}