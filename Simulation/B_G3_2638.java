// 백준 2638 치즈

package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_G3_2638 {
    static int N;
    static int M;
    static int[][] visited;
    static int[][] map;
    static Queue<int[]> q;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        map = new int[N][M];
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(row[j]);
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        int time = 0;

        while (!didMeltAll()) {
            time++;
            visited = new int[N][M];
            dfs(0, 0);
            bfs();
        }

        System.out.println(time);
    }

    static void dfs(int r, int c) {
        visited[r][c] = 1;
        map[r][c] = 2;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (inRange(nr, nc) && visited[nr][nc] == 0 && map[nr][nc] != 1) {
                dfs(nr, nc);
            }
        }
    }

    static void bfs() {
        Queue<int[]> temp = new LinkedList<>();
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] loc = q.poll();
            int air = 0;
            boolean shouldMelt = false;
            for (int d = 0; d < 4; d++) {
                int nr = loc[0] + dr[d];
                int nc = loc[1] + dc[d];
                if (inRange(nr, nc) && map[nr][nc] == 2) {
                    air++;
                    if (air == 2) {
                        temp.add(loc);
                        shouldMelt = true;
                        break;
                    }
                }
            }
            if (!shouldMelt) q.add(loc);
        }

        while (!temp.isEmpty()) {
            int[] loc = temp.poll();
            map[loc[0]][loc[1]] = 0;
        }
    }

    static boolean didMeltAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return false;
            }
        }
        return true;
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }
}

