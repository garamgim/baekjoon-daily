package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class B_G5_10026 {
    static int N;
    static char[][] grid;
    static int[][] visited;
    static int[][] visitedNoRG;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();
    static Queue<int[]> qNoRG = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new int[N][N];
        visitedNoRG = new int[N][N];
        grid = new char[N][N];

        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        int zone = 0;
        int noRg = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] != 1) {
                    zone++;
                    bfs(i, j, q, visited, true);
                }
                if (visitedNoRG[i][j] != 1) {
                    noRg++;
                    bfs(i, j, qNoRG, visitedNoRG, false);
                }

            }
        }

        System.out.printf("%d %d", zone, noRg);
    }

    static void bfs(int r, int c, Queue<int[]> q, int[][] visited, boolean RG) {
        q.add(new int[]{r, c});

        while (!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int cr = curr[0];
                int cc = curr[1];
                char currColor = grid[cr][cc];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    boolean inRange = (0 <= nr && nr < N && 0 <= nc && nc < N);
                    if (RG) {
                        if (inRange && visited[nr][nc] != 1 && grid[nr][nc] == currColor) {
                            q.add(new int[]{nr, nc});
                            visited[nr][nc] = 1;
                        }
                    } else {
                        boolean sameColor;
                        if (currColor == 'R' || currColor == 'G') {
                            sameColor = inRange && (grid[nr][nc] == 'R' || grid[nr][nc] == 'G');
                        } else {
                            sameColor = inRange && (grid[nr][nc] == currColor);
                        }
                        if (inRange && visited[nr][nc] != 1 && sameColor) {
                            q.add(new int[]{nr, nc});
                            visited[nr][nc] = 1;
                        }
                    }
                }
            }
        }
    }
}


