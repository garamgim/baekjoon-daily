// 백준 11286 최대 힙
package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class B_S2_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        for (int t = 0; t < tc; t++){
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                if (!q.isEmpty()) {
                    System.out.println(q.poll());
                } else {
                    System.out.println(0);
                }
            } else {
                q.add(n);
            }
        }
    }
}