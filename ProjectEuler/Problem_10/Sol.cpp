/* Program Info
 * Project Euler
 * Problem 10 Solution
 */

#include "D:\My Data\Work\essentials.cpp"
#include <fstream>

int main()
{
    // Program Info
    StartUp();

    // Opening Output File
    ofstream writeAns;
    writeAns.open("D:\\My Data\\Work\\ProjectEuler\\ans.txt");

    // Main Code
    int a = 2000000;
    long long sum=0;

    for (int i=2; i<a; i++)
    {
        if (Chop(i)==0)
        {
            sum+=i;
        }
    }

    cout << sum << "\n";
    writeAns << sum << "\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
