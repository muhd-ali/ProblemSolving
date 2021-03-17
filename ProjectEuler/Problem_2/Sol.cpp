/* Program Info
 * Project Euler
 * Problem 2 Solution
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
    int limit = 4000000;

    int prev=1;
    int curr=2;
    cout << prev << " ";
    int next=0;

    int sum=0;

    while (next<limit)
    {
        if(curr%2==0)
            sum = sum+curr;
        cout << curr << " ";

        next = prev + curr;
        prev = curr;
        curr = next;
    }
    cout << "\n\n";

    cout << "sum of even numbers: " << sum << "\n";
    writeAns << "sum of even numbers: " << sum << "\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
