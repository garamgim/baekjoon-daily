// https://www.acmicpc.net/problem/2636

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int R;
    static int C;
    static int[] start;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int[][] map;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        map = new int[R][C];
        q = new LinkedList<>();
        start = new int[]{0, 0};

        for (int i = 0; i < R; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }
        int day = 0;
        int cheese = 0;
        while (true) {
            System.out.println("---------");
            for (int i = 0; i < R; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println("---------");
            day++;
            dfs(start);
            melt();
            if (countCheese() == 0) break;
            cheese = countCheese();
        }
        System.out.println(day);
        System.out.println(cheese);
    }

    static void dfs(int[] start) {
        int r = start[0];
        int c = start[1];

        map[r][c] = 2;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (inRange(nr, nc)) {
                if (map[nr][nc] == 0) dfs(new int[]{nr, nc});
                if (map[nr][nc] == 1) q.add(new int[]{nr, nc});
            }
        }
    }

    static void melt() {
        System.out.println(Arrays.toString(q.peek()));
        start = q.peek();
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] loc = q.poll();
            int r = loc[0];
            int c = loc[1];
            map[r][c] = 0;
        }
    }

    static int countCheese() {
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) count++;
            }
        }
        return count;
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }
}
