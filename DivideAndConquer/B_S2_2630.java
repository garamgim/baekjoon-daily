// 백준 2630 색종이 만들기

package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S2_2630 {
    static int N;
    static int white;
    static int blue;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        white = 0;
        blue = 0;
        isFull(0, 0, N);

        System.out.printf("%d\n%d", white, blue);
    }

    static void cutPaper(int r, int c, int size) {
        int piece = size / 2;
        isFull(r, c, piece);
        isFull(r + piece, c, piece);
        isFull(r, c + piece, piece);
        isFull(r + piece, c + piece, piece);
    }

    static void isFull(int r, int c, int size) {
        if (size == 1) {
            if (map[r][c] == 1) blue++;
            else white++;
            return;
        }

        int curr = map[r][c];

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != curr) {
                    cutPaper(r, c, size);
                    return;
                }
            }
        }

        if (curr == 1) blue++;
        else white++;
    }
}