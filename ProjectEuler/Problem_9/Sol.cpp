/* Program Info
 * Project Euler
 * Problem 9 Solution
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
    int a=1000;
    LinkedList<int> nums;

    for (int i=0; i<a+1; i++)
        nums.insertAtHead(i);

    ListItem<int> *tmp1=nums.getHead();
    ListItem<int> *tmp2;
    double diff_sq;
    double diff;
    int sum;
    bool done=0;

    while(tmp1!=NULL)
    {
        tmp2 = tmp1->next;
        while(tmp2->value_sq > tmp1->value_sq/2)
        {
            diff_sq = tmp1->value_sq - tmp2->value_sq;
            diff = sqrt(diff_sq);

            if (isInteger(diff))
            {
              sum = tmp1->value + tmp2->value + diff;

                if (sum==a)
                {
                    done = 1;
                    break;
                }
            }

            tmp2 = tmp2->next;
        }

        if (done==1)
        {
            break;
        }

        tmp1 = tmp1->next;
    }

    long product = diff*tmp2->value*tmp1->value;

    cout << diff << "(squared) + "
         << tmp2->value << "(squared) = "
         << tmp1->value << "(squared)\n";

    cout << diff << " + "
         << tmp2->value << " + "
         << tmp1->value << " = "
         << a << "\n";

    cout << diff << " * "
         << tmp2->value << " * "
         << tmp1->value << " = "
         << product << "\n";

    writeAns << diff << " * "
            << tmp2->value << " * "
            << tmp1->value << " = "
            << product << "\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
