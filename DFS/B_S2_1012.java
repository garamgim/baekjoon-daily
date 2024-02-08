// 백준 1012 유기농 배추
// 좌표 클래스 + 재귀 DFS로 다시 풀어보기



package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_S2_1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++){
            String[] s = br.readLine().split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int cabbage = Integer.parseInt(s[2]);

            int[][] field = new int[n][m];
            boolean[][] visited = new boolean[n][m];

            for (int i = 0; i < cabbage; i++) {
                String[] temp = br.readLine().split(" ");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                field[b][a] = 1;
            }

            Stack<int[]> stk = new Stack<>();

            // 상하좌우
            int[] dr = new int[]{-1, 1, 0, 0};
            int[] dc = new int[]{0, 0, -1, 1};
            int worm = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        worm++;
                        visited[i][j] = true;
                        int r = i;
                        int c = j;
                        while (true) {
                            boolean didMove = false;
                            for (int d = 0; d < 4; d++) {
                                boolean isInsideField = (0 <= r + dr[d] && r + dr[d] < n && 0 <= c + dc[d] && c + dc[d] < m);
                                if (isInsideField && !(visited[r + dr[d]][c + dc[d]]) && (field[r+dr[d]][c+dc[d]] == 1)) {
                                    didMove = true;
                                    stk.add(new int[]{r, c});
                                    r += dr[d];
                                    c += dc[d];
                                    visited[r][c] = true;
                                    break;
                                }
                            }

                            if (!didMove) {
                                if (!stk.isEmpty()) {
                                    int[] prev = stk.pop();
                                    r = prev[0];
                                    c = prev[1];
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(worm);
        }
    }
}