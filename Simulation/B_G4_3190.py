# 백준 3190 뱀

from collections import deque

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

N = int(input())
K = int(input())
arr = [[0] * N for _ in range(N)]

for _ in range(K):
    r, c = map(int, input().split())
    arr[r-1][c-1] = 2

L = int(input())
commands = deque()
for _ in range(L):
    sec, dir = input().split()
    sec = int(sec)
    commands.append((sec, dir))


d = 0
time = 0

r = 0
c = 0
arr[r][c] = 1

snake = deque()
snake.append((r, c))
curr_command = commands.popleft()


while True:
    # print('time = ', time)
    # for i in range(N):
    #     print(arr[i])
    # print()
    time += 1
    nr, nc = r + dr[d], c + dc[d]

    if nr < 0 or nr >= N or nc < 0 or nc >= N:
        break

    if arr[nr][nc] == 1:
        break

    snake.append((nr, nc))

    if arr[nr][nc] == 0:
        tr, tc = snake.popleft()
        arr[tr][tc] = 0

    arr[nr][nc] = 1
    r, c = nr, nc

    if time == curr_command[0]:
        dir = curr_command[1]
        if dir == "L":
            d = (d - 1) % 4
        else:
            d = (d + 1) % 4

        if commands:
            curr_command = commands.popleft()
        else:
            curr_command = (-1, -1)

print(time)