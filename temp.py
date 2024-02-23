for t in range(1, 11):
    N = int(input())
    grid = [list(map(int, input().split())) for _ in range(100)]
    deadlock = 0
    
    for c in range(100):
        r = 0
        curr = 0
        toggle = 0 # 여기 구현
        while r < 100:
            passthrough = False
            if grid[r][c] != 0:
                go = 1
                
                while r + go < 100:
                    if grid[r + go][c] != 0:
                        break
                    go += 1
                else:
                    break
                
                if grid[r][c] != grid[r + go][c]:
                    deadlock += 1
                    grid[r][c] = 0
                    grid[r+go][c] = 0
                r += go
            else:    
                r += 1
            
    print(f"#{t} {deadlock}")