class Expression:
    class Operators:
        precedence = ['/', '*', '-', '+']
        @staticmethod
        def isFirstOnHigherPrecedence(first, second):
            this = Expression.Operators
            p = this.precedence
            return p.index(first) < p.index(second)


    def __init__(self, string):
        self.string = string
        tokens = self.tokenize(string)
        self.value = self.parse(tokens)

    def tokenize(self, string):
        import re
        p = self.Operators.precedence
        o = '\\'.join(p)
        pattern = re.compile(f'(\d+(\s*)?[{o}](\s*)?)+\d+')
        if not pattern.fullmatch(string):
            raise(Exception(f'invalid expression {string}'))
        tokens = re.findall(f'\d+|[{o}]', string)
        tokens = map(lambda x: int(x) if x not in p else x, tokens)
        return list(tokens)


    def isOperator(self, token):
        return token in self.Operators.precedence

    def evaluate(self, operand1, operator, operand2):
        import operator as o
        operation = {
            '+' : o.add,
            '-' : o.sub,
            '*' : o.mul,
            '/' : o.floordiv,
        }
        return operation[operator](operand1, operand2)

    def reduce(self, stack):
        operand2 = stack.pop()
        operator = stack.pop()
        operand1 = stack.pop()
        res = self.evaluate(operand1, operator, operand2)
        stack.append(res)

    def shift(self, index, tokens, stack):
        operand1 = stack.pop()
        operator = tokens[index]
        operand2 = tokens[index + 1]
        print(operand1, operator, operand2)
        res = self.evaluate(operand1, operator, operand2)
        return res

    def singlePass(self, tokens):
        stack = []
        prevOp = None
        for i in range(0, len(tokens)):
            nextIndex = i + 2
            token = tokens[i]
            nextOp = tokens[nextIndex] if nextIndex < len(tokens) else None
            print(i)
            if self.isOperator(token):
                if prevOp is not None:
                    if prevOp == token or self.Operators.isFirstOnHigherPrecedence(prevOp, token):
                        self.reduce(stack)
                    elif self.Operators.isFirstOnHigherPrecedence(token, prevOp) and (nextOp is None or self.Operators.isFirstOnHigherPrecedence(token, nextOp)):
                        token = self.shift(i, tokens, stack)
                        i+=1
                    else:
                        prevOp = token
                else:
                    prevOp = token
            stack.append(token)
        return stack

    def parse(self, tokens):
        count = 0
        while(len(tokens) > 3 and count < 5):
            tokens = self.singlePass(tokens)
            count += 1
            print(tokens)
        self.reduce(tokens)
        return tokens[0]


if __name__ == "__main__":
    testCases = [
        '2+4*2',
        # '2+32/8*2',
        # '32/16*8+2',
        # '32-16*8+2',
        # '32-16*8/2',
        # '32+16-8*2',
    ]
    for test in testCases:
        actual = Expression(test).value
        expected = int(eval(test))
        assert (actual == expected), f'test failed for {test}. actual = {actual}, expected = {expected}'