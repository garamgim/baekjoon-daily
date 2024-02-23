package Temps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S5653 {
    static int N, M, K;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int[][] grid;
    static PriorityQueue<int[]> q;
    static int sec;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            K = Integer.parseInt(temp[2]);

            grid = new int[600+N][600+M];

            int sizeR = 600+N;
            int sizeC = 600+M;

            for (int i = 300; i < 300+N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 300; j < 300+M; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            q = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[3], o1[3]));

            for (int i = 0; i < sizeR; i++) {
                for (int j = 0; j < sizeC; j++) {
                    if (grid[i][j] > 0) {
                        // 행, 열, 현재 상태, 원래의 상태, 활성화 상태 (초기값 0)
                        q.add(new int[]{i, j, grid[i][j]*2, grid[i][j], 0});
                    }
                }
            }

            sec = 0;

            while (sec < K) {
                bfs();
                sec++;
            }

            int size = 0;

            for (int[] cell : q) {
                if (cell[4] == 1) {
                    size++;
                }
            }

            System.out.printf("#%d %d\n", t, size);
        }
    }

    static void bfs() {
        int size = q.size();
        ArrayList<int[]> temp = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int[] cell = q.poll();

            int r = cell[0];
            int c = cell[1];
            int currStatus = cell[2];
            int cellData = cell[3];

            if (currStatus > cellData) {
                cell[2] -= 1;
                if (cell[2] == cellData) cell[4] = 1;  // cellData 시간이 당도하면 활성화
                temp.add(cell);
                // cellData 시간 당도 후 1시간 후 번식 완료
            } else if (currStatus == cellData) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (grid[nr][nc] == 0) {
                        grid[nr][nc] = cellData;
                        temp.add(new int[]{nr, nc, cellData * 2, cellData, 0});
                    }
                }
                // 번식 후 생명력이 다하면 큐에 더해주지 않음
                cell[2] -= 1;
                if (cell[2] > 0) {
                    temp.add(cell);
                }
            } else {
                // currStatus < cell Data -1
                cell[2] -= 1;
                if (cell[2] > 0) {
                    temp.add(cell);
                }
            }
        }

        q.addAll(temp);
    }
}