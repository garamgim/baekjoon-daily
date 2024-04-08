package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G4_16234 {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] visited;
    static int unionCnt;
    static int unionPpl;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        L = Integer.parseInt(info[1]);
        R = Integer.parseInt(info[2]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int day = 0;

        while (true) {
            visited = new int[N][N];
            boolean didMove = false;

            int trace = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) {
                        unionCnt = 0;
                        unionPpl = 0;
                        bfs(i, j, trace);
                        if (unionCnt > 1) {
                            movePpl(trace);
                            didMove = true;
                        }
                        trace++;
                    }
                }
            }

            if (didMove) day++;
            else break;
        }

        System.out.println(day);
    }
    static void movePpl(int trace) {
        int ppl = unionPpl / unionCnt;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == trace){
                    map[i][j] = ppl;
                }
            }
        }
    }

    static void bfs(int sr, int sc, int trace) {
        visited[sr][sc] = trace;
        unionCnt = 1;
        unionPpl += map[sr][sc];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    int diff = Math.abs(map[nr][nc] - map[r][c]);
                    if (L <= diff && diff <= R && visited[nr][nc] == 0) {
                        unionCnt++;
                        visited[nr][nc] = trace;
                        unionPpl += map[nr][nc];
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}
