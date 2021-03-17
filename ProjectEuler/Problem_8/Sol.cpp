/* Program Info
 * Project Euler
 * Problem 8 Solution
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
    ifstream myFile;
    myFile.open("1000_Digit_num.txt");

    int num[1000];
    char digit;

    for(int i=0; i<1000; i++)
    {
        myFile >> digit;
        num[i] = (int)digit-48;
    }

    int range=13;
    long long product=1;
    long long max=0;
    int j;
    int end=0;

    for(int i=0; i<1000; i++)
    {
        for(j=i; j<i+range; j++)
        {
            product*=num[j];
        }

        if (product>max)
        {
            end = j;
            max = product;
        }

        product = 1;
    }

    for(int i=end-range; i<end; i++)
    {
        cout << num[i];
        writeAns << num[i];

        if (i<end-1)
        {
            cout << "x";
            writeAns << "x";
        }
    }

    cout << " = " << max << "\n";
    writeAns << " = " << max << "\n";

    myFile.close();
    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
