package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G4_2573 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int M;
    static int continent;
    static int[][] map;
    static int[][] visited;
    static Queue<int[]> iceberg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        map = new int[N][M];
        iceberg = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(row[j]);
                if (map[i][j] > 0) {
                    iceberg.add(new int[]{i, j});
                }
            }
        }

        int year = 0;
        boolean extinct = false;

        while (true) {
            continent = 0;
            boolean hasIce = false;
            visited = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && visited[i][j] == 0) {
                        hasIce = true;
                        continent++;
                        dfs(i, j);
                    }
                }
            }

            if (!hasIce) {
                year = 0;
                break;
            }

            if (continent >= 2) {
                break;
            }

            bfs();
            year++;
        }

        System.out.println(year);
    }

    static void bfs() {
        int size = iceberg.size();
        ArrayList<int[]> temp = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int[] loc = iceberg.poll();
            int r = loc[0];
            int c = loc[1];
            int currIce = map[r][c];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == 0) {
                    currIce--;
                }
            }
            if (currIce > 0) {
                temp.add(new int[]{r, c, currIce});
            } else {
                temp.add(new int[]{r, c, 0});
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            int[] loc = temp.get(i);
            map[loc[0]][loc[1]] = loc[2];
        }

        iceberg.addAll(temp);
    }

    static void dfs(int r, int c) {
        visited[r][c] = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] == 0 && map[nr][nc] != 0) {
                dfs(nr, nc);
            }
        }

        return;
    }
}

