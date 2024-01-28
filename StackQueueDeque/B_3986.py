# 백준 3986 좋은 단어
import sys

n = int(sys.stdin.readline().rstrip())
cnt = 0

for i in range(n):
    wrd = list(sys.stdin.readline().rstrip())
    stk = []
    for char in wrd:
        if len(stk) > 0 and char == stk[-1]:
            stk.pop()
        else:
            stk.append(char)
    if not stk:
        cnt += 1

print(cnt)