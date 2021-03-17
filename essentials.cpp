/* Program Info
 */
#include <iostream>
#include <cstdlib>
#include <time.h>
#include <vector>
#include <cmath>

using namespace std;

void StartUp()
{
    int n = 29;
    for (int i=0; i<n; i++)
        cout << "#";

    cout << "\n";

    cout << "##                         ##\n"
         << "## Author: Muhammad Ali.   ##\n"
         << "## Roll Number: 17100148.  ##\n"
         << "##                         ##\n";

    for (int i=0; i<n; i++)
        cout << "#";

    cout << "\n\n";
}

void stopExe()
{
    int x;
    cout << "\n\n"
         << "Exiting... \n"
         << "GoodBye!!!";
    cin >> x;
}

template<class T>
void printArray(T *A, int n)
{
    for(int i=0; i<n; i++)
        cout << A[i] << " ";
    cout << "\n\n";
}

template<class T>
void printVector(vector<T> A)
{
    int n = A.size();
    for(int i=0; i<n; i++)
        cout << A[i] << " ";
    cout << "\n\n";
}


void debug()
{
    for(int i=0; i<25; i++)
        cout << "*";
    cout << "\n";
}

template <class T>
void debug(T item)
{
    for(int i=0; i<25; i++)
        cout << "*";
    cout << " --> " << item;
    cout << "\n";
}

template <class T1, class T2>
void debug(T1 item1, T2 item2)
{
    for(int i=0; i<30; i++)
        cout << "*";
    cout << " --> " << item1;

    cout << "\n";

    for(int i=0; i<30; i++)
        cout << "*";
    cout << " --> " << item2;
    cout << "\n";
}

int getRand(int from, int to)
{
    srand(time(NULL));

    /*
    Getting range
    */
    int n = to-from+1;

    /*
    Output random values.
    */
    return (rand()%n)+from;
}

template<class T>
int findMax(T* arr, int size)
{
    int max = 0;
    for (int i=0; i<size; i++)
        if (arr[i]>arr[max])
            max = i;

    return max;
}

template<class T>
int findMin(T* arr, int size)
{
    int min = 0;
    for (int i=0; i<size; i++)
        if (arr[i]<arr[min])
            min = i;

    return min;
}

bool isInteger(double num)
{
    if (num==floor(num))
        return 1;
    return 0;
}

int Chop(long long num)
{
    double sqrtOfNum = sqrt(num);

    if (num<2)
        return 0;
    else if (isInteger(sqrtOfNum))
        return sqrtOfNum;

    for (int i=sqrtOfNum; i!=1; i--)
        if (num%i==0)
            return num/i;

    return 0;
}

int Fib(int n);

int Fib(int n, int* &F)
{
		if (n==1 || n==2)
		{
			return  1;
		}
		else if (F[n-1] != -1)
		{
			return F[n-1];
		}
		else
		{
			F[n-1] = Fib(n-1,F) + Fib(n-2,F);
			return F[n-1];
		}
}

int Fib(int n)   // memoized fibonacci seq generator
{
    int *F;
    F = new int[n];
    for (int i=0; i<n; i++)
        F[i] = -1;

    return Fib(n,F);
}

int nDigitNumber(int num)
{
    int count = 1;
    long long n=10;

    if (num<0)
        num*=-1;

    while (n<=num)
    {
        n*=10;
        count++;
    }

    return count;
}
