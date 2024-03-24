// 백준 2665 미로 만들기

package ShortestPath;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G4_2665 {
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }

        System.out.println(djk());
    }

    static int djk() {
        Queue<Coords> q = new ArrayDeque<>();

        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        q.add(new Coords(0, 0, 0));
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            Coords curr = q.poll();

            if (dist[curr.r][curr.c] < curr.w) continue;

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    int acc = curr.w + blackRoom(map[nr][nc]);
                    if (dist[nr][nc] > acc) {
                        dist[nr][nc] = acc;
                        q.add(new Coords(nr, nc, acc));
                    }
                }
            }
        }

        return dist[N-1][N-1];
    }

    static int blackRoom(int n) {
        return (n == 0) ? 1 : 0;
    }

    static class Coords {
        int r;
        int c;
        int w;

        public Coords(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}