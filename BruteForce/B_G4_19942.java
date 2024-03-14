// 백준 19942 다이어트 (부분집합)

package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_G4_19942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] required = new int[4];
        int[][] ing = new int[N][5];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            required[i] = Integer.parseInt(temp[i]);
        }

        for (int i = 0; i < N; i++) {
            String[] ingTemp = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                ing[i][j] = Integer.parseInt(ingTemp[j]);
            }
        }

        boolean hasAnswer = false;
        int ans = Integer.MAX_VALUE;
        ArrayList<Integer> ansSet = new ArrayList<>();

        for (int i = 1; i < (1 << N); i++) {
            int[] cook = new int[5];
            ArrayList<Integer> subset = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if ((i & (1 << (j))) != 0) {
                    int[] currIng = ing[j];
                    for (int k = 0; k < 5; k++) {
                        cook[k] += currIng[k];
                    }
                    subset.add(j + 1);
                }
            }

            boolean meetCondition = true;

            for (int j = 0; j < 4; j++) {
                if (cook[j] < required[j]) {
                    meetCondition = false;
                    break;
                }
            }

            if (meetCondition) {
                // 최소비용일 때
                if (cook[4] < ans) {
                    ans = cook[4];
                    ansSet = subset;
                    hasAnswer = true;

                // 같은 비용에 다른 부분집합일 때
                } else if (cook[4] == ans) {

                    // 부분집합끼리 사전순서로 비교
                    int currLength = subset.size();
                    int ansLength = ansSet.size();
                    int idx = 0;
                    boolean same = true;

                    while (idx < currLength && idx < ansLength) {
                        if (ansSet.get(idx) == subset.get(idx)) {
                            idx++;
                            continue;
                        }
                        if (ansSet.get(idx) > subset.get(idx)) {
                            ans = cook[4];
                            ansSet = subset;
                        }
                        same = false;
                        break;
                    }

                    // 비교한 곳까지 같다면 길이가 작은 집합이 우위
                    if (same) {
                        if (currLength < ansLength) {
                            ans = cook[4];
                            ansSet = subset;
                        }
                    }
                }
            }
        }

        if (hasAnswer) {
            StringBuilder finalAns = new StringBuilder();
            finalAns.append(ans);
            finalAns.append("\n");
            for (int i = 0; i < ansSet.size(); i++) {
                finalAns.append(ansSet.get(i));
                if (i == ansSet.size()) break;
                finalAns.append(" ");
            }
            System.out.println(finalAns);
        } else {
            System.out.println(-1);
        }
    }
}

