// 백준 4179 불!

package BFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_G4_4179 {
    static int R;
    static int C;
    static char[][] map;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static Queue<Loc> playerQ = new LinkedList<>();
    static Queue<Loc> fireQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        R = Integer.parseInt(info[0]);
        C = Integer.parseInt(info[1]);
        map = new char[R][C];
        Loc player = null;
        Loc fire = null;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    playerQ.add(new Loc(i, j));
                } else if (map[i][j] == 'F') {
                    fireQ.add(new Loc(i, j));
                }
            }
        }
        int ans = bfs();
        System.out.println((ans != -1) ? ans : "IMPOSSIBLE");
    }

    static int bfs() {
        int time = 0;

        while (!playerQ.isEmpty() || !fireQ.isEmpty()) {
            time++;

            int playerQSize = playerQ.size();
            while (playerQSize-- > 0) {
                Loc curr = playerQ.poll();

                if (map[curr.r][curr.c] != 'J') continue;

                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];

                    if (!inRange(nr, nc)) return time;
                    else {
                        if (map[nr][nc] == '.') {
                            map[nr][nc] = 'J';
                            playerQ.add(new Loc(nr, nc));
                        }
                    }
                }
            }

            int fireQSize = fireQ.size();
            while (fireQSize-- > 0) {
                Loc curr = fireQ.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];

                    if (inRange(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] == 'J')) {
                        map[nr][nc] = 'F';
                        fireQ.add(new Loc(nr, nc));
                    }
                }
            }

            boolean alive = false;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'J') alive = true;
                }
            }

            if (!alive) return -1;
        }

        return -1;
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }

    static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}