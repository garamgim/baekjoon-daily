// 백준 1074 Z
// 다시 풀어봐야 함 (남의 코드)

package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S1_1074 {
    static int N;
    static int R;
    static int C;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        R = Integer.parseInt(temp[1]);
        C = Integer.parseInt(temp[2]);
        int size = (int) Math.pow(2, N);
        find(0, 0, size);
    }

    static void find(int r, int c, int size) {
        if (size == 1) {
            System.out.println(ans);
            return;
        }

        int newSize = size / 2;

        if (R < r + newSize && C < c + newSize) {
            find(r, c, newSize);
        } else if (R < r + newSize && c + newSize <= C) {
            ans += (size*size) / 4;
            find(r, c+newSize, newSize);
        } else if (r + newSize <= R && C < c + newSize ) {
            ans += ((size*size) / 4) * 2;
            find(r+newSize, c, newSize);
        } else {
            ans += ((size*size) / 4) * 3;
            find(r+newSize, c+newSize, newSize);
        }
    }
}