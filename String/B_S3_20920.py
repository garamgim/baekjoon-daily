# 백준 20920 영단어 암기는 괴로워
import sys

n, m = map(int, sys.stdin.readline().split())
my_dict = {}

for i in range(n):
    vocab = sys.stdin.readline().rstrip()
    if len(vocab) >= m:
        if vocab in my_dict:
            my_dict[vocab] += 1
        else:
            my_dict[vocab] = 1
    
result = [item for item in my_dict.items()]
result.sort(key=lambda x: (-x[1], -len(x[0]), x[0]))

for tuple in result:
    print(tuple[0])