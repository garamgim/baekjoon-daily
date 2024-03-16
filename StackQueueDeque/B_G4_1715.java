// 백준 1715 카드 정렬하기

package StackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_G4_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> nums = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            nums.add(Long.parseLong(br.readLine()));
        }
        long s = 0;
        long prev;
        long curr;
        if (N == 1) System.out.println(0);
        else {
            while (!nums.isEmpty()) {
                prev = nums.poll();
                curr = prev + nums.poll();
                s += curr;
                if (nums.isEmpty()) break;
                nums.add(curr);
            }
            System.out.println(s);
        }
    }
}

