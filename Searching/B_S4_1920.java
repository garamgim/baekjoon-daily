// 백준 1920 수 찾기
package Searching;
import java.io.*;
import java.util.*;

public class B_S4_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Long> mySet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            mySet.add(Long.parseLong(st.nextToken()));
        }
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            long k = Long.parseLong(st2.nextToken());
            if (mySet.contains(k)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}