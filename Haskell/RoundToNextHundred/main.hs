import Text.Printf

main :: IO()
main = do
    putStrLn $ printf "input = %d, result = $%d" input output where
        (input, output) = wrapper


wrapper =
    if (isInputAPositiveNumber input) then
        (input, roundToNextHundred input) else (input, 0) where
            input = string2Int getInput

getInput :: [Char]
getInput = "150"

string2Int :: String -> Int
string2Int = read

isInputAPositiveNumber :: Int -> Bool
isInputAPositiveNumber x = x >= 0

roundToNextHundred :: Int -> Int
roundToNextHundred x = do
    let diff = mod x 100 in
        x - diff + 100