import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static ArrayList<int[]> dust;
    static int[][] map;
    static int[][] airPurifier;
    // 우, 하, 좌, 상 (시계방향)
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

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

        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("--------");
        spread();
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void spread() {
        ArrayList<int[]> childrenDust = new ArrayList<>();

        for (int i = 0; i < dust.size(); i++) {
            int[] loc = dust.get(i);
            int r = loc[0];
            int c = loc[1];
            int child = (int) (map[r][c] / 5);
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != -1 && child > 0) {
                    childrenDust.add(new int[]{nr, nc, child});
                    map[r][c] -= child;
                }
            }
        }

        for (int i = 0; i < childrenDust.size(); i++) {
            int[] info = childrenDust.get(i);
            int r = info[0];
            int c = info[1];
            int d = info[2];
            map[r][c] += d;
            if (!dust.contains(new int[]{r, c})) dust.add(new int[]{r, c});
        }
    }
}
