def count_char_in_str(str):
    count = {}
    import re
    for chr in str:
        if re.search('[a-z]', chr):
            if chr in count:
                count[chr] += 1
            else:
                count[chr] = 1
    
    return count

def populate_info_table(table_count1, table_count2, info_table, reverse=False):
    str1 = '1'; str2 = '2'
    if reverse:
        str1, str2 = str2, str1
        table_count1, table_count2 = table_count2, table_count1

    for chr, count1 in table_count1.items():
        if count1 > 1:
            first = str1
            second = count1
            if chr in table_count2:
                count2 = table_count2[chr]
                if count2 > count1:
                    first = str2
                    second = count2
                elif count2 == count1:
                    first = '='                
            info_table[chr] = (first, second)


def assign_prefix(table_count1, table_count2):
    max_count = {}
    populate_info_table(table_count1, table_count2, max_count)
    populate_info_table(table_count1, table_count2, max_count, reverse=True)
    max_info = [
        {
            'char': chr,
            'index': str_ind,
            'count': count
        }
        for chr, (str_ind, count) in max_count.items()
    ]
    return max_info

def apply_sorting(max_info):
    def key_generator(char_info):
        key1 = char_info.get('count')
        key2 = 0
        key3 = ord('z') - ord(char_info.get('char'))
        if char_info.get('index') != '=':
            key2 = 3 - int(char_info.get('index'))
        return (
            key1,
            key2,
            key3
        )

    return sorted (
        max_info,
        key=key_generator,
        reverse=True
    )

def create_string(char_info):
    prefix = char_info.get('index')
    suffix = ''.join([char_info.get('char') for _ in range(0, char_info.get('count'))])
    return f'{prefix}:{suffix}'

def mix(s1, s2):
    s1_count = count_char_in_str(s1)
    s2_count = count_char_in_str(s2)
    chrs_with_prefixes = assign_prefix(s1_count, s2_count)
    sorted_chrs = apply_sorting(chrs_with_prefixes)
    return '/'.join(map(create_string, sorted_chrs))

def main():
    s1 = ' In many languages'
    s2 = " there's a pair of functions"
    result = mix(s1, s2)
    print(result)

    # s1 = 'my&friend&Paul has heavy hats! &'
    # s2 = 'my friend John has many many friends &'
    # result = mix(s1, s2)
    # print(result)

    # s1 = 'mmmmm m nnnnn y&friend&Paul has heavy hats! &'
    # s2 = 'my frie n d Joh n has ma n y ma n y frie n ds n&'
    # result = mix(s1, s2)
    # print(result)

    # s1 = 'Are the kids at home? aaaaa fffff'
    # s2 = 'Yes they are here! aaaaa fffff'
    # result = mix(s1, s2)
    # print(result)

main()
    