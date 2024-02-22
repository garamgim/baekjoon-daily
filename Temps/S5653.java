package Temps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5653 {
    static int N;
    static int M;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int[] ddr = new int[]{-1, 1, 1, -1};
    static int[] ddc = new int[]{1, 1, -1, -1};
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            int K = Integer.parseInt(temp[2]);

            grid = new int[600+N][600+M];

            int sizeR = 600+N;
            int sizeC = 600+M;

            for (int i = 300; i < 300+N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 300; j < 300+M; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int s = 1; s <= K; s++) {
                for (int i = 0; i < sizeR; i++) {
                    for (int j = 0; j < sizeC; j++) {
                        if (grid[i][j] > 0 && (s - 1) % grid[i][j] == 0) {
                            reproduce(i, j, grid[i][j]);
                        }
                    }
                }
            }

            int cnt = 0;

            for (int i = 0; i < sizeR; i++) {
                for (int j = 0; j < sizeC; j++) {
                    if (grid[i][j] > 0) {
                        cnt++;
                    }
                }
            }

            System.out.printf("#%d %d\n", t, cnt);
        }
    }

    static void reproduce(int r, int c, int myNum) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            boolean inRange = (0 <= nr && nr < N && 0 <= nc && nc < M);
            boolean didLose = false;
            for (int e = 0; e < 2; e++) {
                int nnr = r + ddr[(d+e)%4];
                int nnc = c + ddc[(d+e)%4];
                if (grid[nnr][nnc] > grid[r][c]) {
                    didLose = true;
                }
            }
            if (inRange && !didLose && grid[nr][nc] == 0) {
                grid[nr][nc] = myNum;
            }
        }
        grid[r][c] = -1;
    }
}