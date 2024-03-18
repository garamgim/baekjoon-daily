// 백준 11003 최솟값 찾기
package StackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_P5_11003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int L = Integer.parseInt(s[1]);

        String[] A = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder(2 * N);
        ArrayDeque<Element> elements = new ArrayDeque<>();

        int curr;
        for (int i = 0; i < N; i++) {
            curr = Integer.parseInt(A[i]);
            if (!elements.isEmpty() && elements.peekFirst().idx == i - L) elements.pollFirst();
            while (!elements.isEmpty() && elements.peekLast().data >= curr) elements.pollLast();
            elements.add(new Element(i, curr));
            sb.append(elements.peekFirst().data);
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static class Element {
        int idx;
        int data;

        public Element(int idx, int data) {
            this.idx = idx;
            this.data = data;
        }
    }
}

