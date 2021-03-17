def is_pair_removable(p0, p1):
    return (p0 == 'NORTH' and p1 == 'SOUTH') or \
        (p0 == 'SOUTH' and p1 == 'NORTH') or \
        (p0 == 'EAST' and p1 == 'WEST') or \
        (p0 == 'WEST' and p1 == 'EAST')


def dirReduc(path):
    if len(path) < 2:
        return path
    else:
        if is_pair_removable(path[0], path[1]):
            return dirReduc(path[2:])
        else:
            # print(path[1:])
            inner = dirReduc(path[1:])
            # print(inner)
            if len(inner) > 0 and is_pair_removable(path[0], inner[0]):
                return inner[1:]
            else:
                return [path[0]] + inner


def main():
    # input = ["NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"]
    input = ["NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "NORTH", "SOUTH", "SOUTH", "SOUTH", "SOUTH", "SOUTH", "SOUTH"]
    result = dirReduc(input)
    print(result)


main()
