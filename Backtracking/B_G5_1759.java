// 백준 1759 암호 만들기
// 백트래킹 버전
// 조합만으로 구현했던 코드:
// https://github.com/garamgim/ssafy-gwangju3-java-algorithms/blob/master/week_07_%EC%99%84%EC%A0%84%ED%83%90%EC%83%89/%EA%B9%80%EA%B0%80%EB%9E%8C/B_0_1759.java

package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G5_1759 {
    static int L;
    static int C;
    static String[] chars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        L = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        chars = br.readLine().split(" ");
        Arrays.sort(chars);
        password(0, 0, new String[L], 0, 0);
    }

    static void password(int lev, int start, String[] ans, int vow, int cons) {
        if (lev == L) {
            if (vow >= 1 && cons >= 2) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < L; i++) {
                    sb.append(ans[i]);
                }
                System.out.println(sb);
            }
            return;
        }

        for (int i = start; i < C; i++) {
            if (lev == L - 1 && vow == 0) {
                if (isVowel(chars[i])) {
                    ans[lev] = chars[i];
                    password(lev + 1, i + 1, ans, vow + 1, cons);
                }
            } else if (lev == L - 2 && cons == 0) {
                if (!isVowel(chars[i])) {
                    ans[lev] = chars[i];
                    password(lev + 1, i + 1, ans, vow, cons + 1);
                }
            } else if (lev == L - 1 && cons == 1) {
                if (!isVowel(chars[i])) {
                    ans[lev] = chars[i];
                    password(lev + 1, i + 1, ans, vow, cons + 1);
                }
            } else {
                if (isVowel(chars[i])) {
                    ans[lev] = chars[i];
                    password(lev + 1, i + 1, ans, vow + 1, cons);
                } else {
                    ans[lev] = chars[i];
                    password(lev + 1, i + 1, ans, vow, cons + 1);
                }
            }
        }
    }

    static boolean isVowel(String c) {
        if (c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") || c.equals("u")) return true;
        return false;
    }
}
