// 백준 7662 이중 우선순위 큐

package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class B_G4_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> q = new TreeMap<>();
            for (int o = 0; o < n; o++) {
                String[] temp = br.readLine().split(" ");
                String op = temp[0];
                int num = Integer.parseInt(temp[1]);
                if (op.equals("I")) {
                    if (q.containsKey(num)) {
                        q.replace(num, q.get(num) + 1);
                    } else {
                        q.put(num, 0);
                    }
                } else {
                    if (!q.isEmpty()) {
                        if (num == -1) {
                            if (q.get(q.firstKey()) > 0) {
                                q.replace(q.firstKey(), q.get(q.firstKey()) - 1);
                            } else {
                                q.remove(q.firstKey());
                            }
                        } else {
                            if (q.get(q.lastKey()) > 0) {
                                q.replace(q.lastKey(), q.get(q.lastKey()) - 1);
                            } else {
                                q.remove(q.lastKey());
                            }
                        }
                    }
                }
            }
            if (q.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.printf("%d %d \n", q.lastKey(), q.firstKey());
            }
        }
    }
}
