/* Program Info
 * Project Euler
 * Problem 7 Solution
 */

#include "D:\My Data\Work\essentials.cpp"
#include <fstream>
#include "LinkedList.cpp"

int main()
{
    // Program Info
    StartUp();

    // Opening Output File
    ofstream writeAns;
    writeAns.open("D:\\My Data\\Work\\ProjectEuler\\ans.txt");

    // Main Code
    LinkedList<int> list;
    int l=10001;
    int i=2;

    while(list.length()!=l)
    {
        if (Chop(i)==0)
        {
            list.insertAtTail(i);
        }
        i++;
    }

    cout << list.getTail()->value << "\n";
    writeAns << list.getTail()->value << "\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
