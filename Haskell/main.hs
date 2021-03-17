repeatTimes :: Int -> [Int]
repeatTimes = \x -> take x $ repeat x

repeatTimesArr :: [Int] -> [Int]
repeatTimesArr xs = concatMap repeatTimes xs

add1 :: Int -> Int
add1 = (+) 1

add2 :: Int -> Int
add2 = add1.add1