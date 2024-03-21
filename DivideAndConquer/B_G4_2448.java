// 백준 2448 별 찍기 - 11
//  *
// * *
//*****

        package DivideAndConquer;

import java.io.*;

public class B_G4_2448 {
    static char[][] canvas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int binSize = N / 3;
        int C = (binSize) * 5 + ((binSize) - 1);
        canvas = new char[N][C];
        System.out.println("C = " + C);
        makeStar(0, C / 2, N, C);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < C; j++) {
                if (canvas[i][j] == '*') bw.write('*');
                else bw.write(' ');
            }
            bw.write('\n');
        }
        bw.flush();
    }

    static void makeStar(int r, int c, int R, int C) {
        if (R == 3) {
            canvas[r][c] = '*';
            canvas[r + 1][c - 1] = '*';
            canvas[r + 1][c + 1] = '*';
            canvas[r + 2][c - 2] = '*';
            canvas[r + 2][c - 1] = '*';
            canvas[r + 2][c] = '*';
            canvas[r + 2][c + 1] = '*';
            canvas[r + 2][c + 2] = '*';
            return;
        }

        int nextRSize = R / 2;
        int nextCSize = (C - 1) / 2;

        makeStar(r, c, nextRSize, nextCSize);
        makeStar(r + nextRSize, c - nextCSize / 2 - 1, nextRSize, nextCSize);
        makeStar(r + nextRSize, c + nextCSize / 2 + 1, nextRSize, nextCSize);
    }
}