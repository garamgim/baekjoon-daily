package StackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class B_G2_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] exp = br.readLine().toCharArray();
        StringBuilder ans = new StringBuilder(exp.length);
        Stack<Character> stk = new Stack<>();

        HashMap<Character, Integer> outside = new HashMap<>();
        outside.put('(', 3);
        outside.put('+', 1);
        outside.put('-', 1);
        outside.put('*', 2);
        outside.put('/', 2);

        HashMap<Character, Integer> inside = new HashMap<>();
        inside.put('(', 0);
        inside.put('+', 1);
        inside.put('-', 1);
        inside.put('*', 2);
        inside.put('/', 2);

        for (char c : exp) {
            if (Character.isAlphabetic(c)) {
                ans.append(c);
            } else {
                if (c == ')') {
                    while (!stk.isEmpty() && stk.peek() != '(') {
                        ans.append(stk.pop());
                    }
                    stk.pop();
                } else {
                    if (stk.isEmpty() || (!stk.isEmpty() && outside.get(c) > inside.get(stk.peek()))) {
                        stk.add(c);
                    } else {
                        while (!stk.isEmpty() && outside.get(c) <= inside.get(stk.peek())) {
                            ans.append(stk.pop());
                        }
                        stk.add(c);
                    }
                }
            }
        }

        while (!stk.isEmpty()) {
            ans.append(stk.pop());
        }

        System.out.println(ans);
    }
}
