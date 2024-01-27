import sys
from collections import deque

n = int(sys.stdin.readline().rstrip())

for i in range(n):
    n, m = map(int, sys.stdin.readline().split())
    q = deque(map(int, sys.stdin.readline().split()))
    q = deque([[doc, idx] for idx, doc in enumerate(q)])
    print_cnt = 0
    
    while len(q) > 0:
        mx = q[0][0]
        for i in range(1, len(q)):
            if q[i][0] > mx:
                mx = q[i][0] 
        if q[0][0] == mx:
            fo = q.popleft()
            print_cnt += 1
            if fo[1] == m:
                print(print_cnt)
                break
        else:
            q.append(q.popleft())