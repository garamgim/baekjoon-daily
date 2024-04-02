# 백준 1697 숨바꼭질

from collections import deque

N, K = map(int, input().split())

def bfs(s):
    q = deque()
    q.append(s)    
    time = 0
    visited = [0]*100000
    visited[s] = 1
    while q:
        for _ in range(len(q)):
            curr = q.popleft()
            
            if curr == K:
                return time
            
            if 0 <= curr-1 < 1000000 and visited[curr-1] == 0:
                visited[curr-1] = 1
                q.append(curr-1)
            if 0 <= curr+1 < 1000000 and visited[curr+1] == 0:
                visited[curr+1] = 1
                q.append(curr+1)
            if 0 <= curr*2 < 1000000 and visited[curr*2] == 0:
                visited[curr*2] = 1
                q.append(curr*2)
                
        time += 1

print(bfs(N))
