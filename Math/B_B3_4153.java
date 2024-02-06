// 백준 4153 직각삼각형

package Math;
import java.io.*;
import java.util.*;

public class B_B3_4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        while (!s.equals("0 0 0")){
            StringTokenizer st = new StringTokenizer(s);

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[] t = Arrays.stream(new int[]{a, b, c}).sorted().toArray();

            if (t[2] * t[2] == t[0] * t[0] + t[1] * t[1]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }

            s = br.readLine();
        }
    }
}
