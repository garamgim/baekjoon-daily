// 백준 1259 팰린드롬수 (B1)
package String;
import java.io.*;

public class B_B1_1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        while (!s.equals("0")) {
            StringBuilder sb = new StringBuilder(s);
            boolean isPalindrome = true;
            for (int i = 0; i < sb.length()/2; i++) {
                if (sb.charAt(i) != sb.charAt(sb.length()-1-i)) {
                    isPalindrome = false;
                    System.out.println("no");
                    break;
                }
            }
            if (isPalindrome) {
                System.out.println("yes");
            }
            s = br.readLine();
        }
    }
}