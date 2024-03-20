// 백준 2447 별 찍기 - 10

package DivideAndConquer;

import java.io.*;

public class B_G5_2447 {
    static char[][] canvas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        canvas = new char[N][N];
        makeStar(0, 0, N);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (canvas[i][j] != '*') bw.write(" ");
                else bw.write(canvas[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    static void makeStar(int r, int c, int n) {
        if (n == 1) {
            canvas[r][c] = '*';
            return;
        }

        int smaller = n / 3;
        makeStar(r, c, smaller);
        makeStar(r, c + smaller, smaller);
        makeStar(r, c + smaller * 2, smaller);
        makeStar(r + smaller, c, smaller);
        makeStar(r + smaller, c + smaller * 2, smaller);
        makeStar(r + smaller * 2, c, smaller);
        makeStar(r + smaller * 2, c + smaller, smaller);
        makeStar(r + smaller * 2, c + smaller * 2, smaller);
    }
}