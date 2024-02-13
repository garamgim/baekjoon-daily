import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class temp {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            char[] exp = br.readLine().toCharArray();
            StringBuilder ans = new StringBuilder(exp.length);
            Stack<Character> stk = new Stack<>();

            HashMap<Character, Integer> outside = new HashMap<>();
            outside.put('(', 0);
            outside.put('+', 1);
            outside.put('-', 1);
            outside.put('*', 2);
            outside.put('/', 2);

            HashMap<Character, Integer> inside = new HashMap<>();
            inside.put('(', 3);
            inside.put('+', 1);
            inside.put('-', 1);
            inside.put('*', 2);
            inside.put('/', 2);

            for (char c : exp) {
                if (Character.isAlphabetic(c)) {
                    ans.append(c);
                } else {
                    if (outside.get(c) > inside.get(stk.peek())) {
                        stk.add(c);
                    } else {
                        ans.append(stk.pop());
                    }
                }
            }
        }

}

