//시작점의 지속적인 갱신 (내가 먹을 수 있는 물고기)
//        먹은 후 0으로 바꿈
//        - 나보다 낮은 물고기를 n개 먹으면 다음 물고기로 타겟 변경
//        - 물고기가 존재하지 않으면 break
//        ---> n물고기가 n+1개 있는지
//        - 일단 물고기를 배열에 다 넣어놓고 자른다
//        1: {1, 2}, {2, 3}, 2: {4, 5}, ... ,...


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Temp {
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static HashMap<Integer, ArrayList<int[]>> startPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        startPoints = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && map[i][j] != 9) {
                    if (startPoints.containsKey(map[i][j])) {
                        ArrayList<int[]> prevArr = startPoints.get(map[i][j]);
                        prevArr.add(new int[]{i, j});
                        startPoints.put(map[i][j], prevArr);
                    } else {
                        ArrayList<int[]> newArr = new ArrayList<>();
                        newArr.add(new int[]{i, j});
                        startPoints.put(map[i][j], newArr);
                    }
                }
            }
        }

        for (Map.Entry<Integer, ArrayList<int[]>> entry : startPoints.entrySet()) {
            ArrayList<int[]> val = entry.getValue();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < val.size(); i++) {
                sb.append(Arrays.toString(val.get(i)));
                sb.append(", ");
            }
            System.out.println("KEY = " + entry.getKey() + ", VALUE = " + sb);
        }

    }
}