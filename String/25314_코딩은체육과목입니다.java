package String;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        n /= 4;
        StringBuilder sb = new StringBuilder(6 * n);
        for (int i = 0; i < n; i++) {
            sb.append("long ");
        }
        System.out.println(sb.append("int").toString());
    }
}
