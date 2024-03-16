// 백준 9935 문자열 폭발
// ArrayDeque는 보통 Stack보다 성능이 좋지만 인덱스로 조회가 불가능
// 따라서 해당 문제와 같이 스택 내의 원소를 순차적으로 조회해야 할 때는 Stack을 사용하는 것이 좋음

package StackQueueDeque;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_G4_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String sub = br.readLine();
        Stack<Character> stk = new Stack<>();
        int strLength = str.length();
        int subLength = sub.length();
        int idx = 0;
        for (int i = 0; i < strLength; i++) {
            char c = str.charAt(i);
            stk.add(c);
            if (stk.size() >= subLength && c == sub.charAt(subLength - 1)) {

                while (true) {
                    if (idx == subLength) {
                        for (int j = 0; j < subLength; j++) {
                            stk.pop();
                        }
                        idx = 0;
                        break;
                    }

                    if (!stk.isEmpty() && (stk.get(stk.size() - (subLength - idx)) == sub.charAt(idx))) {
                        idx++;
                    } else {
                        idx = 0;
                        break;
                    }
                }
            }
        }

        if (stk.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder(strLength);
            while (!stk.isEmpty()) {
                sb.append(stk.pop());
            }
            System.out.println(sb.reverse());
        }
    }
}

