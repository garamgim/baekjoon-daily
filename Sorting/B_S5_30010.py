# 백준 30010 잘못된 버블정렬

n = int(input())
lst = [i+1 for i in range(n)]
lst[0], lst[-1] = lst[-1], lst[0]

print(*lst)