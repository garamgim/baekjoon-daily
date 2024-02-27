# SWEA 1767 프로세서 연결하기

T = int(input())

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dfs(idx, s, connected_core):
    global max_core    
    global min_sum
        
    if idx == len(core):
        if connected_core > max_core:
            max_core = connected_core
            min_sum = s
        elif connected_core == max_core:
            min_sum = min(min_sum, s)
        return

    curr = core[idx]
    r = curr[0]
    c = curr[1]
    
    for d in range(4):
        nr = r + dr[d]
        nc = c + dc[d]
        cnt = 0
        stk = []
        while 0 <= nr < N and 0 <= nc < N:
            if arr[nr][nc] == 0:
                stk.append((nr, nc))
                arr[nr][nc] = 9
                cnt += 1
                nr += dr[d]
                nc += dc[d]
            else:
                while len(stk) != 0:
                    pr, pc = stk.pop()
                    arr[pr][pc] = 0
                break
        else:
            dfs(idx + 1, s + cnt, connected_core + 1)

        while len(stk) != 0:
            pr, pc = stk.pop()
            arr[pr][pc] = 0

    dfs(idx + 1, s, connected_core)



for t in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    
    core = []             
    min_sum = 999999999     
    max_core = 0           
    
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 1:
                if not (i == 0 or i == N - 1 or j == 0 or j == N - 1): 
                    core.append((i, j))     

    dfs(0, 0, 0)
    print(f'#{t} {min_sum}')

