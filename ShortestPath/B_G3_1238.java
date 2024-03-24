// 백준 1238 파티
// Arrays.fill(arr, new ArrayList<>) 안됨
// 참조값이 같은 객체들로 전부 채워짐

package ShortestPath;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G3_1238 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int X = Integer.parseInt(temp[2]);

        ArrayList<Node>[] adjl1 = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjl1[i] = new ArrayList<Node>();
        }

        ArrayList<Node>[] adjl2 = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjl2[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < M; i++) {
            String[] temp2 = br.readLine().split(" ");
            int s = Integer.parseInt(temp2[0]);
            int e = Integer.parseInt(temp2[1]);
            int w = Integer.parseInt(temp2[2]);
            adjl1[s].add(new Node(w, e));
            adjl2[e].add(new Node(w, s));
        }


        int[] dist1 = djk(X, adjl1);
        int[] dist2 = djk(X, adjl2);

        int ans = Integer.MIN_VALUE;

        for (int i = 1; i < N + 1; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }

        System.out.println(ans);
    }

    static int[] djk(int s, ArrayList[] adjl) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, s));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (dist[curr.n] < curr.w) continue;
            dist[curr.n] = curr.w;

            ArrayList<Node> connected = adjl[curr.n];
            for (int i = 0; i < connected.size(); i++) {
                Node next = connected.get(i);
                int acc = curr.w + next.w;
                if (dist[next.n] > acc) {
                    dist[next.n] = acc;
                    pq.add(new Node(acc, next.n));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int w, n;

        public Node(int w, int n) {
            this.w = w;
            this.n = n;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}