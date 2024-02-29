// 16236
// bfs 한번 하고 종료, visited 비우고 다시 bfs, ...
// direction -> 상, 좌, 하, 우 (반시계방향)
// 1 0 1
// 0 9 0 -> 이 상황에서 좌측의 먹이를 먹고 바로 종료하고 다시 오른쪽거 먹으러 bfs 돌리기
// 지금 먹어야 하는 물고기의 갯수는 전역변수로 놓고 bfs 끝날 때마다 갱신해줌
// babySize보다 작은 물고기를 찾으면 해당 물고기를 먹고(0으로 바꾸고) eaten++ 하고 break
// 예제 4, 6번 틀림


import jdk.jshell.execution.JdiDefaultExecutionControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

public class Main {
    static int N;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[] babyLocation;
    static int babySize;

    static int eaten;
    static int totalMove;
    static boolean didEat;
    static Queue<int[]> q;
    static PriorityQueue<int[]> feed;
    static int[][] visited;
    static int[][] map;
    static HashMap<Integer, ArrayList<int[]>> startPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];
        q = new LinkedList<>();
        feed = new PriorityQueue<>((int[] o1, int[] o2) -> o1[0] != o2[0]? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(row[j]);
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    visited[i][j] = 1;
                    q.add(new int[]{i, j});
                }
            }
        }

        babySize = 2;
        eaten = 0;
        totalMove = 0;
        didEat = false;
        bfs();
        System.out.println(totalMove);
    }

    static void bfs() {
        while (!q.isEmpty()){
            didEat = false;
            int size = q.size();
            ArrayList<int[]> temps = new ArrayList<>();
            System.out.println("Q: "+ Arrays.deepToString(q.toArray()));
            int currDistance = 0;

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                currDistance = visited[r][c];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if ((0 <= nr && nr < N && 0 <= nc && nc < N) && visited[nr][nc] == 0) {
                        if (map[nr][nc] > babySize) {
                            continue;
                        } else if (map[nr][nc] == 0 || map[nr][nc] == babySize) {
                            visited[nr][nc] = visited[r][c] + 1;
                            temps.add(new int[]{nr, nc});
                        } else if (map[nr][nc] < babySize) {
                            feed.add(new int[]{nr, nc});
                        }
                    }
                }
            }

            if (!feed.isEmpty()) {
                System.out.println(Arrays.deepToString(feed.toArray()));
                eaten++;
                totalMove += currDistance;
                int[] closest = feed.poll();
                int cr = closest[0];
                int cc = closest[1];
                System.out.printf("ate:%d ---- r:%d c:%d\n", map[cr][cc], cr, cc);
                map[cr][cc] = 9;
                for (int j = 0; j < N; j++) {
                    System.out.println(Arrays.toString(map[j]));
                }
                map[cr][cc] = 0;
                System.out.printf("move:%d current size:%d \n", currDistance, babySize);
                System.out.println();
                if (eaten == babySize) {
                    babySize++;
                    System.out.printf("grew: %d \n", babySize);
                    System.out.println("--------------");
                    eaten = 0;
                }
                visited = new int[N][N];
                visited[cr][cc] = 1;
                feed.clear();
                q.clear();
                q.add(new int[]{cr, cc});
            } else {
                q.addAll(temps);
            }
        }
    }
}