/* Program Info
 * Project Euler
 * Problem 3 Solution
 */

#include "D:\My Data\Work\essentials.cpp"
#include <fstream>
#include <cmath>

bool isInteger(long double num)
{
    if (num==floor(num))
        return 1;
    return 0;
}

long long Chop(long long num)
{
    long double sqrtOfNum = sqrt(num);

    if (num<2)
        return 0;
    else if (isInteger(sqrtOfNum))
        return sqrtOfNum;

    for (int i=2; i<sqrtOfNum; i++)
        if (num%i==0)
            return num/i;

    return 0;
}

int main()
{
    // Program Info
    StartUp();

    // Opening Output File
    ofstream writeAns;
    writeAns.open("D:\\My Data\\Work\\ProjectEuler\\ans.txt");

    // Solving problem
    long long num = 600851475143;
    int chk;

    cout << "Finding the largest prime factor of the number: " << num << "\n";

    while (true)
    {
        num = Chop(num);
        chk = Chop(num);
        if (chk==0)
            break;
    }

    cout << num;
    writeAns << num;

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
