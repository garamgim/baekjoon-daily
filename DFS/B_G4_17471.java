// 백준 17471 개리맨더링

package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_G4_17471 {
    static int N;
    static int[] visited;
    static int[] population;
    static int[][] graph;
    static int totalPpl;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N+1];
        totalPpl = 0;

        String[] temp = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(temp[i-1]);
            totalPpl += population[i];
        }

        graph = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            for (int j = 1; j <= n; j++) {
                int v = Integer.parseInt(s[j]);
                graph[i][v] = 1;
                graph[v][i] = 1;
            }
        }

        ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N/2; i++) {
            comb(i, 0, 1, new int[i]);
        }
        System.out.println((ans != Integer.MAX_VALUE)? ans : -1);
    }

    static void comb(int n, int lev, int start, int[] parted) {
        if (lev == n) {
            visited = new int[N+1];
            for (int i = 0; i < n; i++) {
                visited[parted[i]] = 2;
            }
            partDfs(parted[0], parted);

            for (int i = 1; i <= N; i++) {
                if (visited[i] == 0) {
                    dfs(i);
                    break;
                }
            }

            boolean doneParting = true;
            for (int i = 1; i <= N; i++) {
                if (visited[i] == 0 || visited[i] == 2) {
                    doneParting = false;
                    break;
                }
            }

            if (doneParting) {
                int ppl1 = 0;
                int ppl2 = 0;
                for (int i = 0; i < lev; i++) {
                    ppl1 += population[parted[i]];
                }
                ppl2 = totalPpl - ppl1;
                ans = Math.min(ans, Math.abs(ppl1 - ppl2));
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            parted[lev] = i;
            comb(n, lev + 1, i + 1, parted);
        }
    }

    static void partDfs(int x, int[] parted) {
        visited[x] = 1;

        for (int i = 1; i <= N; i++) {
            boolean has = false;
            for (int j = 0; j < parted.length; j++) {
                if (i == parted[j]) {
                    has = true;
                    break;
                }
            }

            if (has && graph[x][i] == 1 && visited[i] == 2) {
                partDfs(i, parted);
            }
        }
    }

    static void dfs(int x) {
        visited[x] = 1;

        for (int i = 1; i <= N; i++) {
            if (graph[x][i] == 1 && visited[i] == 0) {
                dfs(i);
            }
        }
    }
}
