/* Program Info
 * Project Euler
 * Problem 1 Solution
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

    // Solving problem
    int sum=0;

    for (int i=0; i<1000; i++)
        if (i%3==0 || i%5==0)
            sum=sum+i;
    cout << sum;
    writeAns << sum;

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
