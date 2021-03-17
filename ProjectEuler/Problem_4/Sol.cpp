/* Program Info
 * Project Euler
 * Problem 4 Solution
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
    int num = 999999;
    int chk=0;
    int i=1;
    vector<int> palindromes;
//////////////////////////////////////////////// generating palindromes
    while (num > 10000)
    {
        palindromes.push_back(num);

        if (i==900)
        {
            num = num-2;
            chk++;
            goto skip;
        }
        switch (chk)
        {
        case 0:
            if (i%100==0)
                num = num-11;
            else if (i%10==0)
                num = num-110;
            else
                num = num-1100;

            break;

        case 1:
            if (i%100==0)
                num = num-11;
            else if (i%10==0)
                num = num-110;
            else
                num = num-100;

            break;
        }

        skip: i++;
    }
//////////////////////////////////////////////// generated palindromes

//////////////////////////////////////////////// factorising palindromes
    int *chops;
    chops = new int[palindromes.size()];
    for (int i=0; i<palindromes.size(); i++)
        chops[i] = -1;

    int tmp;

    for (int i=0; i<palindromes.size(); i++)
    {
        tmp = Chop(palindromes[i]);
        while(tmp>999 && tmp!=0)
            tmp = Chop(tmp);
        if (tmp!=0)
            chops[i] = tmp;

    }

    int *divisions;
    divisions = new int[palindromes.size()];
    for (int i=0; i<palindromes.size(); i++)
    {
        tmp = palindromes[i]/chops[i];
        if (chops[i]==-1)
            divisions[i]=-1;
        else if (tmp<1000)
            divisions[i] = tmp;
        else
            divisions[i]=-1;
    }
//////////////////////////////////////////////// factorised palindromes

//////////////////////////////////////////////// checking max
    int *sums;
    sums = new int[palindromes.size()];
    for (int i=0; i<palindromes.size(); i++)
    {
        if (chops[i]==-1)
            sums[i]=-1;
        else
            sums[i] = divisions[i]+chops[i];
    }
//////////////////////////////////////////////// checked max

    tmp = findMax(sums,palindromes.size());
    cout << chops[tmp] << " x " << divisions[tmp] << " = " << palindromes[tmp] << "\n";
    writeAns << chops[tmp] << " x " << divisions[tmp] << " = " << palindromes[tmp] << "\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
