// 백준 17070 파이프 옮기기 1

package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class B_G5_17070 {
    static int N;
    static int ans;
    static int[][] map;
    static Queue<Coords> q;
    static int[] dr = new int[]{0, 1, 1};
    static int[] dc = new int[]{1, 0, 1};
    static int[][] nextDir = {{0, 2}, {1, 2}, {0, 1, 2}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // LinkedList 사용 시 시간초과
        q = new ArrayDeque<>();
        ans = 0;
        System.out.println(bfs());
    }

    // 가로: 0, 세로: 1, 대각선: 2
    static int bfs() {
        q.add(new Coords(0, 1, 0));
        int cnt = 0;

        while (!q.isEmpty()) {
            Coords curr = q.poll();
            if (curr.r == N-1 && curr.c == N-1) {
                cnt++;
                continue;
            }

            int[] canGo = nextDir[curr.dir];
            for (int i = 0; i < canGo.length; i++) {
                int d = canGo[i];

                if (d == 2) {
                    if (isThreeAvailable(curr.r, curr.c)) {
                        q.add(new Coords(curr.r + dr[d], curr.c + dc[d], d));
                    }
                } else {
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];

                    if (inRange(nr, nc)&& map[nr][nc] == 0) {
                        q.add(new Coords(nr, nc, d));
                    }
                }
            }
        }

        return cnt;
    }

    static boolean isThreeAvailable(int r, int c) {
        for (int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!inRange(nr, nc) || map[nr][nc] == 1) {
                return false;
            }
        }
        return true;
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }

    static class Coords {
        int r, c, dir;

        public Coords(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}
