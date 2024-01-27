// 백준 11365 !밀비 급일

package String;

import java.util.Scanner;

public class B_11365 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("END")) {
            StringBuilder sb = new StringBuilder(s);
            System.out.println(sb.reverse());
            s = sc.nextLine();
        }
        sc.close();
    }
}
