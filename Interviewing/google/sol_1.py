def solution(D, A):
    ancestor = A.copy()

    store = {}
    for i in range(0, len(A)):
        if A[i] in store:
            ancestor[i] = store[A[i]]
        else:
            for j in range(0, D-1):
                if ancestor[i] == -1:
                    break
                ancestor[i] = A[ancestor[i]]
            store[A[i]] = ancestor[i]
    return ancestor


D = 1000
A = [i-1 for i in range(0, 1000)]
res = solution(D, A)
print(res)
