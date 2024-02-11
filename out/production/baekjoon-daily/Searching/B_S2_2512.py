# 백준 2512 예산

import sys

n = int(sys.stdin.readline().rstrip())
budgets = list(map(int, input().split()))
total = int(sys.stdin.readline().rstrip())

low = min(budgets)
high = max(budgets)
budget_set = set()

def binsrch(arr, low, high, my_set, total_budget):
    mid = int((low+high)/2)
    print(mid)
    curr_len = len(my_set)

    new_budget = []
    for num in arr:
        if num >= mid:
            new_budget.append(mid)
        else:
            new_budget.append(num)
    new_sum = sum(new_budget)
         
    if new_sum > total_budget:
        return binsrch(arr, low, mid-1, my_set, total_budget)
    elif new_sum < total_budget:
        my_set.add(new_sum)
        new_len = len(my_set)
        if new_len == curr_len:
            return max(new_budget)
        else:
            return binsrch(arr, mid+1, high, my_set, total_budget)
    else:
        return max(new_budget)
        

print(binsrch(budgets, low, high, budget_set, total))



