// 백준 2805 나무 자르기
package Searching;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_S2_2805_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        String[] temp = br.readLine().split(" ");
        int[] trees = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            trees[i] = Integer.parseInt(temp[i]);
        }

        int low = 0;
        int high = Arrays.stream(trees).max().getAsInt();
        int mid = 0;

        while (low <= high) {
            mid = (int) (low+high)/2;
            long cut_sum = 0;

            for (int num : trees) {
                if (num > mid) {
                    cut_sum += (num-mid);
                }
            }

            if (cut_sum >= m) {
                low = mid + 1;
            } else if (cut_sum < m) {
                high = mid - 1;
            }
        }

        System.out.println(high);
    }
}

