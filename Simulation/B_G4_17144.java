
// 백준 17144 미세먼지 안녕!

package Simulation;

import java.io.*;
import java.util.*;

public class B_G4_17144 {
    // 우, 하, 좌, 상 (시계방향)
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int R;
    static int C;
    static ArrayList<int[]> dust;
    static int[][] map;
    static int[][] airPurifier;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        int T = Integer.parseInt(temp[2]);

        map = new int[R][C];
        dust = new ArrayList<>();
        airPurifier = new int[2][2];
        int airIdx = 0;

        for (int i = 0; i < R; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s[j]));
                if (map[i][j] == -1) {
                    airPurifier[airIdx] = new int[]{i, j};
                    airIdx++;
                } else if (map[i][j] > 0) {
                    dust.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            purify();
        }

        int totalDust = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) totalDust += map[i][j];
            }
        }

        System.out.println(totalDust);
    }

    static void spread() {
        ArrayList<int[]> childrenDust = new ArrayList<>();

        for (int[] loc : dust) {
            int r = loc[0];
            int c = loc[1];
            int child = map[r][c] / 5;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (inRange(nr, nc) && map[nr][nc] != -1 && child > 0) {
                    childrenDust.add(new int[]{nr, nc, child});
                    map[r][c] -= child;
                }
            }
        }

        for (int[] info : childrenDust) {
            int r = info[0];
            int c = info[1];
            int d = info[2];
            map[r][c] += d;
        }
    }

    static void purify() {
        blow(airPurifier[0], -1);
        blow(airPurifier[1], 1);
    }

    static void blow(int[] purifier, int direction) {
        ArrayList<int[]> temp = new ArrayList<>();

        int d = 0;
        int r = purifier[0] + dr[d];
        int c = purifier[1] + dc[d];
        int currVal = 0;
        temp.add(new int[]{r, c, currVal});

        while (true) {
            if (!inRange(r + dr[d], c + dc[d])) {
                d = Math.floorMod(d + direction, 4);
            }
            if (map[r + dr[d]][c + dc[d]] == -1) break;
            currVal = map[r][c];
            r += dr[d];
            c += dc[d];
            temp.add(new int[]{r, c, currVal});
        }

        for (int[] loc : temp) {
            map[loc[0]][loc[1]] = loc[2];
        }

        dust.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    dust.add(new int[]{i, j});
                }
            }
        }
    }

    static boolean inRange(int nr, int nc) {
        return (0 <= nr && nr < R && 0 <= nc && nc < C);
    }
}
