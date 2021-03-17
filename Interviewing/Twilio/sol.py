def missingWords(s, t):
    words_s = s.split(' ')
    words_t = t.split(' ')
    store = {}
    for word in words_s:
        if word in store:
            store[word] += 1
        else:
            store[word] = 1
    for word in words_t:
        if word in store:
            store[word] -= 1

    result = []
    for word in words_s:
        if word in store and store[word] > 0:
            store[word] -= 1
            result.append(word)
    return result

s = 'I am a programmer am I'
t = 'am programmer'
res = missingWords(s, t)
print(res)
