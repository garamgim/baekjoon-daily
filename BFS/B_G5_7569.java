// 백준 7569 토마토

package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_G5_7569 {

    // 앞뒤상하좌우
    static int[] db = new int[]{-1, 1, 0, 0, 0, 0};
    static int[] dr = new int[]{0, 0, -1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[1]);
        int M = Integer.parseInt(temp[0]);
        int H = Integer.parseInt(temp[2]);

        int[][][] tomato = new int[H][N][M];

        Queue<int[]> q = new LinkedList<>();
        int noTomato = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomato[i][j][k] == 1) {
                        q.add(new int[]{i, j, k});
                    } else if (tomato[i][j][k] == -1) {
                        noTomato++;
                    }
                }
            }
        }

        int day = -1;

        if (q.size() + noTomato == N*M*H) {
            System.out.println(0);
        } else {
            while (!q.isEmpty()) {
                int currSize = q.size();
                day++;

                for (int i = 0; i < currSize; i++) {
                    int[] curr = q.poll();
                    int b = curr[0];
                    int r = curr[1];
                    int c = curr[2];

                    for (int d = 0; d < 6; d++) {
                        int nb = b + db[d];
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        boolean inRange = (0 <= nb && nb < H && 0 <= nr && nr < N && 0 <= nc && nc < M);
                        if (inRange && tomato[nb][nr][nc] == 0) {
                            q.add(new int[]{nb, nr, nc});
                            tomato[nb][nr][nc] = 1;
                        }
                    }
                }
            }

            boolean stillHave = false;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if (tomato[i][j][k] == 0) {
                            stillHave = true;
                            break;
                        }
                    }
                }
            }

            if (stillHave) {
                System.out.println(-1);
            } else {
                System.out.println(day);
            }
        }
    }
}


