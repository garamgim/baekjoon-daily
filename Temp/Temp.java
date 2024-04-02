package Temp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Temp {
    static int N;
    static int H;
    static int[] cave;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        H = Integer.parseInt(temp[1]);
        cave = new int[N];
        for (int i = 0; i < N; i++) {
            cave[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(fly(5));
    }

    public static void binSearch(int l, int r, int[] arr) {
        if (l < r) return;

        int m = (l + r) / 2;

    }

    public static int fly(int sector) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((i % 2 == 0 && sector <= cave[i]) || (i % 2 != 0 && sector > (H - cave[i]))) {
                cnt++;
            }
        }
        return cnt;
    }
}
