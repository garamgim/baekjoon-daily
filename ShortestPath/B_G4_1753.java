package ShortestPath;

// 백준 1753 최단경로

/*
 *   프림 vs 다익스트라
 *
 *   1. 프림
 *   - 그래프 상의 모든 노드들을 최소 비용으로 연결 (두 노드 사이의 최단거리를 보장하지 않음)
 *   - 각 정점에 자신과 연결된 간선의 가중치를 저장
 *   - 어느 정점에서 시작해도 같은 그래프를 도출
 *
 *   2. 다익스트라
 *   - """시작점""" S로부터 모든 정점까지의 최단 경로
 *   - 따라서 시작 정점이 바뀔 때마다 최단 경로 그래프가 다르게 도출
 *   - 또한 """경로"""를 구하는 것이기 때문에 """누적""" 값으로 가중치를 판별
 *
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G4_1753 {
    static ArrayList<ArrayList<Vertex>> adjl;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int V = Integer.parseInt(s[0]);
        int E = Integer.parseInt(s[1]);
        int S = Integer.parseInt(br.readLine());

        // Initializing adjacency list
        adjl = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            adjl.add(new ArrayList<Vertex>());
        }

        for (int i = 0; i < E; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            adjl.get(u).add(new Vertex(w, v));
        }

        System.out.println(djk(S, V));
    }

    static String djk(int s, int v) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(0, s));

        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[s] = 0;

        while (!pq.isEmpty()) {
            Vertex curr = pq.poll();

            // [2% 틀렸습니다] 수정사항 - visited 배열 삭제
            if (dist[curr.data] < curr.weight) continue;

            // Getting vertices that current vertex is connected to
            ArrayList<Vertex> connected = adjl.get(curr.data);

            for (int i = 0; i < connected.size(); i++) {
                Vertex next = connected.get(i);
                int accumulatedDist = dist[curr.data] + next.weight;
                if (dist[next.data] > accumulatedDist) {
                    dist[next.data] = accumulatedDist;
                    // [76% 시간초과] 수정사항 - 가중치로 weight 변경
                    next.weight = accumulatedDist;
                    pq.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= v; i++) {
            if (i != s && dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i] + "\n");
            }
        }

        return sb.toString();
    }

    static class Vertex implements Comparable<Vertex>{
        int weight;
        int data;

        public Vertex(int weight, int data) {
            this.weight = weight;
            this.data = data;
        }

        @Override
        public int compareTo(Vertex o) {
            return weight - o.weight;
        }
    }
}