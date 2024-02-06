// 백준 2609 최대공약수와 최소공배수

package Math;
import java.io.*;
import java.util.*;

public class B_B1_2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int m = a*b;
        int r = a%b;

        while (r != 0) {
            a = b;
            b = r;
            r = a%b;
        }

        System.out.println(b);
        System.out.println(m/b);
    }
}