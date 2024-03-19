// 백준 1992 쿼드트리

package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S1_1992 {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        String ans = inspect(0, 0, N);
        System.out.println(ans);
    }

    static String zip(int r, int c, int size) {
        int cut = size / 2;
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(inspect(r, c, cut));
        sb.append(inspect(r, c + cut, cut));
        sb.append(inspect(r + cut, c, cut));
        sb.append(inspect(r + cut, c + cut, cut));
        sb.append(')');
        return sb.toString();
    }

    static String inspect(int r, int c, int size) {
        int curr = map[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != curr) {
                    return zip(r, c, size);
                }
            }
        }
        return String.valueOf(curr);
    }
}
