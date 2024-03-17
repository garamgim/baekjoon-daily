// 백준 1167 트리의 지름

package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G2_1167 {
    static int N;
    static int ans;
    static int furthest;
    static int[] visited;
    static ArrayList<int[]>[] adjl;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjl = new ArrayList[N + 1];
        StringTokenizer st = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            adjl[idx] = new ArrayList<int[]>();
            int v;
            int d;
            while (true) {
                v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                d = Integer.parseInt(st.nextToken());
                adjl[idx].add(new int[]{v, d});
            }
        }

        ans = Integer.MIN_VALUE;
        furthest = 0;

        visited = new int[N + 1];
        dfs(1, 0);

        visited = new int[N + 1];
        dfs(furthest, 0);

        System.out.println(ans);
    }

    static void dfs(int i, int total) {
        visited[i] = 1;

        boolean didExplore = false;
        for (int[] e : adjl[i]) {
            if (visited[e[0]] == 0) {
                didExplore = true;
                dfs(e[0], total + e[1]);
            }
        }

        if (!didExplore) {
            if (total > ans) {
                ans = total;
                furthest = i;
            }
        }
    }
}

