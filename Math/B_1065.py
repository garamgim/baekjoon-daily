# 백준 1065 한수

n = int(input())
cnt = 0

for num in range(1, n+1):
    if num < 100:
        cnt += 1
    else:
        diff = set()
        lst = list(str(num))
        for i in range(len(lst)-1):
            diff.add(int(lst[i+1])-int(lst[i]))
            if len(diff) > 1:
                break
        if len(diff) == 1:
            cnt += 1

print(cnt)