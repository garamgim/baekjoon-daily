// 백준 7576 토마토

package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_G5_7576 {
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[1]);
        int M = Integer.parseInt(temp[0]);

        int[][] tomato = new int[N][M];

        Queue<int[]> q = new LinkedList<>();
        int noTomato = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 1) {
                    q.add(new int[]{i, j});
                } else if (tomato[i][j] == -1) {
                    noTomato++;
                }
            }
        }

        int day = -1;

        if (q.size() + noTomato == N * M) {
            System.out.println(0);
        } else {
            while (!q.isEmpty()) {
                int currSize = q.size();
                day++;

                for (int i = 0; i < currSize; i++) {
                    int[] curr = q.poll();
                    int r = curr[0];
                    int c = curr[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        boolean inRange = (0 <= nr && nr < N && 0 <= nc && nc < M);
                        if (inRange && tomato[nr][nc] == 0) {
                            q.add(new int[]{nr, nc});
                            tomato[nr][nc] = 1;
                        }
                    }
                }
            }

            boolean stillHave = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomato[i][j] == 0) {
                        stillHave = true;
                        break;
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


