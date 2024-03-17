// 백준 1987 알파벳

package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_G4_1987 {
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int R;
    static int C;
    static int[] used;
    static char[][] map;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        ans = Integer.MIN_VALUE;
        used = new int[26];
        used[map[0][0] - 'A']++;
        dfs(0, 0, 1, used);
        System.out.println(ans);
    }

    static void dfs(int r, int c, int cnt, int[] used) {
        ans = Math.max(ans, cnt);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (used[map[nr][nc] - 'A'] == 0) {
                    used[map[nr][nc] - 'A']++;
                    dfs(nr, nc, cnt + 1, used);
                    used[map[nr][nc] - 'A']--;
                }
            }
        }

    }
}
