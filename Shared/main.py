num_employees = 3
[
   'YYY',
   'NYY',
   'YYY',
   'YYY',
   'NYY',
]

def maxStreak(num_employees, data):
    pass

# 373

#iter = 1; chr = 'a'
#s[i-1] = 'a'
#if
#count = 2

#iter = 2; chr = 'b'
#s[i-1] = 'a'
#else
#count = 1, start = 2

#iter = 3; chr = 'c'
#s[i-1] = 'b'
#else
#count = 1, start = 3

#iter = 4; chr = 'c'
#s[i-1] = 'c'
#if
#count = 2, start = 3

#iter = 7; chr = 'd'
#s[i-1] = 'c'
#else
#count = 2, start = 3


def countLargeGroups(s):
    count = 1
    indeces = []
    start = 0
    for i, chr in enumerate(s):
        if i == 0:
            continue
        if (chr == s[i - 1]):
            count += 1
        else:
            if count >= 3:
                indeces.append((start, i-1))
            count = 1
            start = i

    if count >= 3:
        indeces.append((start, len(s) - 1))
    return indeces


res = countLargeGroups(s)
print(res)