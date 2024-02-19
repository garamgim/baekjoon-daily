package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_S1_2178 {
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        int[][] maze = new int[N][M];
        int[][] visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0});
        visited[0][0] = 1;
        int route = 0;
        boolean reached = false;

        while (!q.isEmpty()) {
            route++;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                if (r == N - 1 && c == M - 1) {
                    reached = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    boolean inRange = (0 <= r + dr[d] && r + dr[d] < N && 0 <= c + dc[d] && c + dc[d] < M);
                    if (inRange && visited[r + dr[d]][c + dc[d]] != 1 && maze[r + dr[d]][c + dc[d]] == 1) {
                        q.add(new int[]{r + dr[d], c + dc[d]});
                        visited[r + dr[d]][c + dc[d]] = 1;
                    }
                }
            }

            if (reached) {
                break;
            }
        }

        System.out.println(route);
    }
}


