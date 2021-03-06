/* Program Info
 * Project Euler
 * Problem 5 Solution
 */

#include "D:\My Data\Work\essentials.cpp"
#include <fstream>
#include "LinkedList.cpp"

void factorize(int num, LinkedList<int> &factors)
{
    if (!Chop(num))
    {
        factors.insertAtTail(num);
        return;
    }
    else
    {
        for (int i=2; i<num; i++)
        {
            if (num%i==0)
            {
                factors.insertAtTail(i);
                factorize(num/i,factors);
                return;
            }
        }
    }
}

int product(LinkedList<int> list)
{
    ListItem<int> *tmp = list.getHead();
    int answer=1;

    while (tmp!=NULL)
    {
        answer*=tmp->value;
        tmp = tmp->next;
    }

    return answer;
}

int main()
{
    // Program Info
    StartUp();

    // Opening Output File
    ofstream writeAns;
    writeAns.open("D:\\My Data\\Work\\ProjectEuler\\ans.txt");

    int a=1, b=10;      // range of numbers
    int size = b-a+1;
    vector<int> nums;
    for (int i=a; i<b+1; i++)
        nums.push_back(i);

    LinkedList<int> factors;
    factorize(nums[0],factors);


    for (int i=1; i<nums.size(); i++)
    {
        LinkedList<int> tmp1(factors);
        ListItem<int> *temp1;

        LinkedList<int> tmp2;
        factorize(nums[i],tmp2);
        ListItem<int> *temp2 = tmp2.getHead();

        if (i==7)
        {
            tmp1.printUsingHead();
            tmp2.printUsingHead();
        }

        while(tmp1.getHead()!=NULL && tmp2.getHead()!=NULL)
        {
/// when working for a=1 and b=10, when it comes to factorize 8, first two is searched and removed.
/// strange things happen for the second two.
/// second 2 is searched and a non null pointer is returned but when the value is accessed again, it's not 2 so not deleted.
/// analyze the displays generated by line 87 and 97.

            if (i==7)
                cout << "searching " << tmp2.getHead()->value << "\n";      /// ////////////////////////////////////////////

            temp1 = tmp1.searchFor(tmp2.getHead()->value);



            if (temp1!=NULL)
            {
                if (i==7)
                {
                    cout << "found and deleting " << temp2->value << "\n";  /// ////////////////////////////////////////////
                }
                tmp1.deleteElement(temp2->value);
            }
            else
            {
                if (i==7)
                {
                    cout << "not found so adding " << temp2->value << "\n";
                }
                factors.insertAtTail(tmp2.getHead()->value);
            }

            tmp2.deleteHead();
        }
    }

    factors.printUsingHead();

    cout << "product: " << product(factors) << "\n\n";
    writeAns << "product: " << product(factors) << "\n\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
