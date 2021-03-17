#ifndef __LIST_H
#define __LIST_H
#include <cstdlib>
#include <iostream>

using namespace std;

/* This class just holds a single data item. */
template <class T>
struct ListItem
{
    T value;
    T value_sq;
    ListItem<T> *next;
    ListItem<T> *prev;

    ListItem()
    {
    }

    ListItem(T theVal)
    {
        this->value = theVal;
        this->next = NULL;
        this->prev = NULL;
    }
};

/* This is the generic List class */
template <class T>
class LinkedList
{

public:

    ListItem<T> *head;
    ListItem<T> *tail;
    int n;

    // Constructor
    LinkedList();

    // Copy Constructor
    LinkedList(const LinkedList<T>& otherList);

    // Destructor
    ~LinkedList();

    // Insertion Functions
    void insertAtHead(T item);
    void insertAtTail(T item);

    // Lookup Functions
    ListItem<T> *getHead();
    ListItem<T> *getTail();
    ListItem<T> *searchFor(T item);

    // Deletion Functions
    void deleteElement(T item);
    void deleteHead();
    void deleteTail();

    // Utility Functions
    int length();

    // My Functions
    void printUsingHead()
    {
        ListItem<T> *temp;
        temp = head;

        if (temp==NULL)
            cout << "> the list is empty (head = NULL)!!! \n\n";
        else
            while (temp!=NULL)
            {
                cout << temp->value << " " << temp->value_sq << "\n";
                temp = temp->next;
            }

        cout << "\n\n";
    }

    void printUsingTail()
    {
        ListItem<T> *temp;
        temp = getTail();

        if (temp==NULL)
            cout << "> the list is empty (tail = NULL)!!! \n\n";
        else
            while (temp!=NULL)
            {
                cout << temp->value << " " << temp->value_sq << "\n";
                temp = temp->prev;
            }

        cout << "\n\n";
    }
};

#endif
