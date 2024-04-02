# 백준 1194 달이 차오른다, 가자.

from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]

s = 0

for i in range(N):
    for j in range(M):
        if board[i][j] == '0':
            board[i][j] = '.'
            s = (i, j)
            break

# "A" : 65
# "a" : 97

def bfs(S):
    q = deque()
    status = 0
    visited = [[[0] * M for _ in range(N)] for _ in range(64)]
    keys = 0
    visited[keys][S[0]][S[1]] = 1
    q.append((S[0], S[1], keys))
    dist = -1

    while q:
        dist += 1

        for _ in range(len(q)):
            curr = q.popleft()
            cr = curr[0]
            cc = curr[1]
            curr_keys = curr[2]

            for d in range(4):
                nr = cr + dr[d]
                nc = cc + dc[d]
                if 0 <= nr < N and 0 <= nc < M and visited[curr_keys][nr][nc] == 0:
                    if board[nr][nc] == '#':
                        continue
                    elif board[nr][nc] == '.':
                        visited[curr_keys][nr][nc] = 1
                        q.append((nr, nc, curr_keys))
                    elif board[nr][nc] in 'abcdef':
                        visited.append([[0] * M for _ in range(N)])
                        visited[curr_keys][nr][nc] = 1
                        q.append((nr, nc, curr_keys | (1 << (ord(board[nr][nc]) - 97))))
                    elif board[nr][nc] in 'ABCDEF':
                        if curr_keys & (1 << (ord(board[nr][nc]) - 65)):
                            visited[curr_keys][nr][nc] = 1
                            q.append((nr, nc, curr_keys))
                    else:
                        return dist + 1

    return -1

print(bfs(s))
