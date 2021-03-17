/* Program Info
 * Project Euler
 * Problem 11 Solution
 */

#include "D:\My Data\Work\essentials.cpp"
#include <fstream>

void printArray(long long *A, int n, int maxDig)
{
    int dig;
    int zeros;

    for(int i=0; i<n; i++)
    {
        dig = nDigitNumber(A[i]);
        zeros = maxDig-dig;
        for (int j=0; j<zeros; j++)
            cout << 0;

        cout << A[i] << " ";
    }
    cout << "\n\n";
}

int findMax(long long** grid, int n)
{
    long long max = grid[0][findMax(grid[0],n)];
    long long temp;

    for (int i=1; i<n; i++)
    {
        temp = grid[i][findMax(grid[i],n)];

        if (temp>max)
            max = temp;
    }
    return max;
}

long long printGrid(long long** grid, int n)
{
    long long max = findMax(grid,n);

    for (int i=0; i<n; i++)
    {
        printArray(grid[i],n,nDigitNumber(max));
    }

    return max;
}

long long maxProduct(long long** grid, int x, int y, int size, int span)
{
    long long hor=1, ver=1, diag_dwn=1, diag_up=1;

    for (int i=0; i<span; i++)
    {
        if (x+i<size)
        {
            hor*=grid[x+i][y];
        }

        if (y+i<size)
        {
            ver*=grid[x][y+i];
        }

        if (x+i<size && y+i<size)
        {
            diag_dwn*=grid[x+i][y+i];
        }

        if (x+i<size && y-i>=0)
        {
            diag_up*=grid[x+i][y-i];
        }
    }

    long long maxim = max(hor,ver);
    maxim =  max(maxim,diag_up);
    return max(maxim,diag_dwn);
}


int main()
{
    // Program Info
    StartUp();

    // Opening Output File
    ofstream writeAns;
    writeAns.open("D:\\My Data\\Work\\ProjectEuler\\ans.txt");

    // Main Code
    ifstream myfile;
    myfile.open("grid.txt");

    int size = 20;
    long long** grid = new long long* [size];

    for (int i=0; i<size; i++)
    {
        grid[i] = new long long [size];
        for (int j=0; j<size; j++)
        {
            myfile >> grid[i][j];
        }
    }

    printGrid(grid,size);

    long long** sol = new long long* [size];

    for (int i=0; i<size; i++)
    {
        sol[i] = new long long[size];

        for (int j=0; j<size; j++)
        {
            sol[i][j] = maxProduct(grid,i,j,size,4);
        }
    }
    long long ans = printGrid(sol,size);

    cout << ans << "\n\n";
    writeAns << ans << "\n\n";

    // Closing Output File
    writeAns.close();

    // Stopping exe from closing
    stopExe();
}
