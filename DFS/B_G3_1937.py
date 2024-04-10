# 백준 1937 욕심쟁이 판다
# DFS + DP (1520번과 비슷)

import sys
sys.setrecursionlimit(40000)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dfs(r, c):
    if memo[r][c] != -1:
        return memo[r][c]

    way = 1
    for d in range(4):
        nr, nc = r + dr[d], c + dc[d]
        if 0 <= nr < N and 0 <= nc < N and arr[r][c] < arr[nr][nc]:
            way = max(way, dfs(nr, nc) + 1)

    memo[r][c] = way
    return memo[r][c]


N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
memo = [[-1] * N for _ in range(N)]

for i in range(N):
    for j in range(N):
        if memo[i][j] == -1:
            dfs(i, j)

ans = -1000
for i in range(N):
    ans = max(ans, max(memo[i]))
print(ans)