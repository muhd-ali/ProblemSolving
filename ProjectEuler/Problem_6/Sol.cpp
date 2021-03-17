/* Program Info
 * Project Euler
 * Problem 6 Solution
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
    int a=1, b=100;
    int x=0, x_sq=0;

    for (int i=a; i<b+1; i++)
    {
        x+=i;
        x_sq+=i*i;
    }

    cout << "(sum(x))^2 = " << x*x << "\n";
    cout << "(sum(x^2)) = " << x_sq << "\n";
    cout << "(sum(x))^2 - (sum(x^2)) = " << x*x-x_sq << "\n";

    writeAns << "(sum(x))^2 = " << x*x << "\n";
    writeAns << "(sum(x^2)) = " << x_sq << "\n";
    writeAns << "(sum(x))^2 - (sum(x^2)) = " << x*x-x_sq << "\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
