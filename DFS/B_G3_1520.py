# 백준 1520 내리막길
# DFS + DP

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dfs(r, c):
    if r == R - 1 and c == C - 1:
        return 1

    if memo[r][c] != -1:
        return memo[r][c]

    can_reach = 0

    for d in range(4):
        nr = r + dr[d]
        nc = c + dc[d]

        if 0 <= nr < R and 0 <= nc < C and arr[nr][nc] < arr[r][c]:
            can_reach += dfs(nr, nc)

    memo[r][c] = can_reach
    return memo[r][c]

R, C = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(R)]

ans = 0
memo = [[-1] * C for _ in range(R)]
dfs(0, 0)
print(memo[0][0])