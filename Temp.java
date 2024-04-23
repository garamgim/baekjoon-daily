import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Temp {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
            System.out.println(Arrays.toString(map[i]));
        }
        int combo = 0;
        while (true) {
            visited = new boolean[12][6];

            boolean didCombo = false;

            // bombCheck
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!visited[i][j]) {
                        if (map[i][j] == '.') {
                            visited[i][j] = true;
                        } else {
                            if (bombCheck(i, j, map[i][j])) {
                                didCombo = true;
                            };
                        }
                    }
                }
            }

            if (didCombo) combo++;

            // gravity check
            gravity();
        }
    }

    static void gravity() {
        for (int i = 0; i < 6; i++) {
            for (int j = 11; j >= 0; j--) {
                int blankStart = -1;
                int blankEnd = -1;
                if (map[j][i] == '0') {
                    blankStart = j;
                    blankEnd = j;
                    while (map[blankEnd][i] == '0') {
                        blankEnd++;
                    }

                    break;
                }

            }
        }
    }

    static boolean bombCheck(int r, int c, char color) {
        visited[r][c] = true;
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> nodes = new ArrayList<>();
        q.add(new int[]{r, c});
        nodes.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (0 <= nr && nr < 12 && 0 <= nc && nc < 6) {
                    if (!visited[nr][nc] && map[nr][nc] == color) {
                        nodes.add(new int[]{nr, nc});
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        if (nodes.size() < 4) {
            return false;
        } else {
            for (int[] node : nodes) {
                map[node[0]][node[1]] = '0';
            }
            return true;
        }
    }

    static class Node {
        int r, c;
        char color;

        public Node(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
}


