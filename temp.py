for t in range(1, 11):
    N = int(input())
    grid = [list(map(int, input().split())) for _ in range(100)]
    deadlock = 0

    for j in range(100):
        magnet = ""
        for i in range(100):
            if grid[i][j] != 0:
                magnet += str(grid[i][j])
        deadlock += magnet.count("12")

    print(f"#{t} {deadlock}")