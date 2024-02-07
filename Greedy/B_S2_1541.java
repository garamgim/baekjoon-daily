// 백준 1541 잃어버린 괄호

package Greedy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_S2_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        ArrayList<String> exp = new ArrayList<>(s.length());
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < s.length(); i++) {
            try {
                sb.append(Integer.parseInt(String.valueOf(s.charAt(i))));
                if (i == s.length()-1) {
                    exp.add(sb.toString());
                }
            } catch (NumberFormatException e) {
                exp.add(sb.toString());
                exp.add(String.valueOf(s.charAt(i)));
                sb.setLength(0);
            }
        }

        for (int i = exp.size()-1; i >= 0; i--) {
            if (exp.get(i).equals("+")) {
                int a = Integer.parseInt(exp.get(i-1));
                int b = Integer.parseInt(exp.get(i+1));
                exp.set(i-1, String.valueOf(a+b));
                exp.remove(i+1);
                exp.remove(i);
            }
        }

        for (int i = 0; i < exp.size(); i++) {
            if (exp.get(i).equals("-")) {
                int a = Integer.parseInt(exp.get(i-1));
                int b = Integer.parseInt(exp.get(i+1));
                exp.set(i+1, String.valueOf(a-b));
                exp.set(i-1, "+");
                exp.set(i,"+");
            }
        }

        System.out.println(exp.get(exp.size()-1));
    }
}
