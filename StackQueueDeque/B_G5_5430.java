package StackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class B_G5_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String order = br.readLine();
            int N = Integer.parseInt(br.readLine());
            LinkedList<Integer> q = new LinkedList<>();

            String s = br.readLine();
            String sub = s.substring(1, s.length()-1);
            if (sub.length() != 0) {
                String[] temp = sub.split(",");
                for (int i = 0; i < temp.length; i++) {
                    q.add(Integer.parseInt(temp[i]));
                }
            }
            boolean reverse = false;
            boolean error = false;

            for (int i = 0; i < order.length(); i++) {
                if (order.charAt(i) == 'R') {
                    if (reverse) {
                        reverse = false;
                    } else {
                        reverse = true;
                    }
                } else {
                    if (!q.isEmpty()) {
                        if (reverse) {
                            q.pollLast();
                        } else {
                            q.pollFirst();
                        }
                    } else {
                        error = true;
                        System.out.println("error");
                        break;
                    }
                }
            }

            if (!error) {
                StringBuilder answer = new StringBuilder();
                answer.append('[');
                while (!q.isEmpty()) {
                    int n = 0;
                    if (reverse) {
                        n = q.pollLast();
                    } else {
                        n = q.pollFirst();
                    }
                    answer.append(n);
                    if (q.isEmpty()) { break; }
                    answer.append(',');
                }
                answer.append(']');
                System.out.println(answer);
            }
        }
    }
}


