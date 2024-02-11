import sys

n = int(sys.stdin.readline().rstrip())
three = 0

n = str(bin(n))

for i in range(len(n)-2):
    if int(n[::-1][i]):
        three += 3**i

print(three)
