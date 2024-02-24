package BFS;

// 백준 2206 벽 부수고 이동하기
// 메모리 초과

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_G3_2206 {
    static int N, M;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int[][] map;
    static int[][][] visited;
    static int cnt;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new int[N][M];
        visited = new int[2][N][M];
        // visit 배열을 3차원으로 설정하여 벽을 부수었던 경로와 안 부수었던 경로를 따로 기록해줌
        // 돌이켜 생각해보면 벽을 부순 후에는 이미 다른 map이기 때문에 자연스레 다른 경로와 달라질 수 밖에 없음
        // 다른 map은 경로를 따로 체크하듯 여기서도 다르게 설정하는 것 뿐이다


        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        q = new LinkedList<>();

        // r, c, didBreak, count
        q.add(new int[] {0, 0, 0, 1});

        System.out.println(bfs());
    }

    static int bfs() {

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                int didBreak = curr[2];
                int cnt = curr[3];

                if (r == N - 1 && c == M - 1) {
                    return cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    boolean inRange = (0 <= nr && nr < N && 0 <= nc && nc < M);
                    if (!inRange) {
                        continue;
                    }
                    if (map[nr][nc] == 0 && visited[didBreak][nr][nc] == 0) {
                        // 여기서 방문체크 해줘야 메모리 초과가 나지 않는다는 글을 보았다
                        // 하라는 대로 하기는 했는데 뭐가 다른지 잘 모르겠다
                        // 어차피 enqueue 되는 데이터는 같지 않나?
                        visited[didBreak][nr][nc] = 1;
                        q.add(new int[]{nr, nc, didBreak, cnt+1});
                    } else if (map[nr][nc] == 1 && didBreak == 0) {
                        visited[1][nr][nc] = 1;
                        q.add(new int[]{nr, nc, 1, cnt+1});
                    }
                }
            }
        }

        return -1;
    }
}