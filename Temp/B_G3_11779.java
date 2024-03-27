// https://www.acmicpc.net/problem/11779

package Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class B_G3_11779 {
    static int N;
    static ArrayList[] adjl;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adjl = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjl[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            adjl[u].add(new Node(w, v));
        }

        String[] temp2 = br.readLine().split(" ");
        int S = Integer.parseInt(temp2[0]);
        int E = Integer.parseInt(temp2[1]);
        djk(S, E);
    }

    static void djk(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, s));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        ArrayList[] path = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            path[i] = new ArrayList<Integer>();
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (dist[curr.n] < curr.w) continue;
            dist[curr.n] = curr.w;

            ArrayList<Node> connected = adjl[curr.n];
            for (int i = 0; i < connected.size(); i++) {
                Node next = connected.get(i);
                int acc = curr.w + next.w;
                if (dist[next.n] > acc) {
                    path[next.n].add(next.n);
                    dist[next.n] = acc;
                    pq.add(new Node(acc, next.n));
                }
            }
        }
        System.out.println(dist[e]);
        System.out.println(path[e]);
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

