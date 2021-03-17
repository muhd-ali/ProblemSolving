def count_vals(val_string, count=1):
    if len(val_string) == 0:
        return []
    elif len(val_string) == 1:
        return [(val_string, count)]
    else:
        if val_string[0] == val_string[1]:
            return count_vals(val_string[1:], count+1)
        else:
            inner = count_vals(val_string[1:])
            if count == 0:
                count = count+1
            inner.insert(0, (val_string[0], count))
            return inner

def create_string(val_count_pair):
    return str(val_count_pair[1]) + val_count_pair[0]

def look_and_say_single(val):
    val_counts = count_vals(val)
    strs = list(map(create_string, val_counts))
    return ''.join(strs)


def look_and_say(data='1', maxlen=5):
    #TODO populate result list with the look and say numbers
    ''' data:   starting number set
        maxlen: max sequence length
    '''
    this_result = look_and_say_single(data)
    if maxlen < 1:
        return []
    else:
        inner = look_and_say(this_result, maxlen - 1)
        inner.insert(0, this_result)
        return inner

def main():
    data = '1'
    maxlen = 10
    # result = look_and_say_single(data)
    result = look_and_say(data, maxlen)
    # result = count_vals("1")
    print(result)

main()
