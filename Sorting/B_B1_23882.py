# 백준 23882 알고리즘 수업 - 선택 정렬 2
import sys

n, k = map(int, sys.stdin.readline().rstrip().split())
arr = list(map(int, sys.stdin.readline().rstrip().split()))

i = n-1
swap_cnt = 0

while i >= 1:
    max_idx = i

    for j in range(i-1, -1, -1):
        if arr[j] > arr[max_idx]:
            max_idx = j

    if (i != max_idx):
        arr[i], arr[max_idx] = arr[max_idx], arr[i]
        swap_cnt += 1
        if swap_cnt == k:
            print(*arr)
            break
    i -= 1
else: 
    print(-1)