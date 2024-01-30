import sys

n = int(sys.stdin.readline().rstrip())
q = list(map(int, sys.stdin.readline().rstrip().split()))
new_q = [0]*n
counting = [0]*max(q)

for num in q:
    counting[num-1] += 1

for i in range(len(counting)-1):
    counting[i+1] += counting[i]

for i in range(len(q)-1, -1, -1):
    counting[q[i]-1] -= 1
    new_q[counting[q[i]-1]] = q[i]

for i in range(1, len(new_q)):
    new_q[i] += new_q[i-1]

print(sum(new_q))
                                       
