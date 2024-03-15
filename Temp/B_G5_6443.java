// 백준 6443 애너그램
// https://www.acmicpc.net/problem/6443

package Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_G5_6443 {
    static int M;
    static char[] arr, p, mx;
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            arr = br.readLine().toCharArray();
            Arrays.sort(arr);
            M = arr.length;
            p = new char[M];
            mx = new char[M];
            selected = new boolean[M];

            perm(0);
        }

        System.out.println(sb.toString());
        br.close();
    }

    private static void perm(int lev) {
        if (lev == M) {
            String str = new String(p);
            sb.append(str).append("\n");

            return;
        }

        mx[lev] = '0';

        for (int i = 0; i < M; i++) {
            if (!selected[i] && mx[lev] < arr[i]) {
                System.out.println("----------");
                System.out.println("lev: " + lev);
                System.out.printf("i: %d\n", i);
                System.out.printf("할당 전 / mx[lev]:%c arr[i]:%c \n", mx[lev], arr[i]);
                System.out.print("mx: ");
                System.out.println(Arrays.toString(mx));
                mx[lev] = arr[i];
                System.out.printf("할당 후 / mx[lev]:%c arr[i]:%c \n", mx[lev], arr[i]);
                System.out.print("mx: ");
                System.out.println(Arrays.toString(mx));
                System.out.println("----------");
                System.out.println();
                selected[i] = true;
                p[lev] = arr[i];
                perm(lev + 1);
                selected[i] = false;
            } else {
                System.out.println("--blocked---");
                if (selected[i]) {
                    System.out.printf("selected, i: %d, arr[i]: %c\n", i, arr[i]);
                    System.out.println("lev: " + lev);
                    System.out.printf("mx[lev]:%c arr[i]:%c \n", mx[lev], arr[i]);
                    System.out.print("mx: ");
                    System.out.println(Arrays.toString(mx));
                    System.out.println("----------");
                    System.out.println();
                }
                else {
                    System.out.println("중복 발견");
                    System.out.println("lev: " + lev);
                    System.out.printf("i: %d\n", i);
                    System.out.printf("mx[lev]:%c arr[i]:%c \n", mx[lev], arr[i]);
                    System.out.print("mx: ");
                    System.out.println(Arrays.toString(mx));
                    System.out.println("----------");
                    System.out.println();
                }
            }
        }

    }

}