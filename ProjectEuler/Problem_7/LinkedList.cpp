#ifndef __LIST_CPP
#define __LIST_CPP

#include <cstdlib>
#include "LinkedList.h"

template <class T>
LinkedList<T>::LinkedList()
{
	head = NULL;
	tail = NULL;
	n = 0;
}

template <class T>
LinkedList<T>::LinkedList(const LinkedList<T>& otherLinkedList)
{
	ListItem<T> *temp;
	temp = otherLinkedList.head;

	head = NULL;

	insertAtTail(temp->value);

	while (temp->next!=NULL)
	{
		temp = temp->next;
		insertAtTail(temp->value);
	}
}

template <class T>
LinkedList<T>::~LinkedList()
{
	while (head!=NULL)
	{
		deleteHead();
	}
}

template <class T>
void LinkedList<T>::insertAtHead(T item)
{
	ListItem<T> *temp;
	temp = new ListItem<T>;
	temp->value = item;

	temp->next = head;
	temp->prev = NULL;

	if (head!=NULL)
		head->prev = temp;
	else
		tail = temp;

	head = temp;
	n++;
}

template <class T>
void LinkedList<T>::insertAtTail(T item)
{
	if (head==NULL)
		insertAtHead(item);
	else
	{
		ListItem<T> *temp;
		temp = new ListItem<T>;
		temp->value = item;

		temp->prev = tail;
		temp->next = NULL;

		tail->next = temp;
		tail = temp;
		n++;
	}
}

template <class T>
void LinkedList<T>::insertAfter(T toInsert, T afterWhat)
{
	if (head==NULL)
		cout << "> The list is empty!!! \n\n";
	else
	{
		if (tail->value==afterWhat)
			insertAtTail(toInsert);
		else
		{
			ListItem<T> *temp1 = head;

			while (temp1->value!=afterWhat && temp1->next!=NULL)
			{
				temp1 = temp1->next;
			}

			if (temp1->next==NULL)
				cout << "> The requested item not found in list so the item was not inserted!!! \n\n";
			else
			{
				ListItem<T> *temp;
				temp = new ListItem<T>;
				temp->value = toInsert;

				temp->prev = temp1;
				temp->next = temp1->next;

				temp1->next = temp;
				temp->next->prev = temp;
			}
		}
	}
	n++;
}

template <class T>
void LinkedList<T>::insertSorted(T item)
{
	if (head==NULL)
		insertAtHead(item);
	else
	{
		if (tail->value<item)
			insertAtTail(item);
		else if (head->value>item)
			insertAtHead(item);
		else
		{
			ListItem<T> *temp1 = head;

			while (temp1->next->value<item)
			{
				temp1 = temp1->next;
			}

			ListItem<T> *temp;
			temp = new ListItem<T>;
			temp->value = item;

			temp->prev = temp1;
			temp->next = temp1->next;

			temp1->next = temp;
			temp->next->prev = temp;
		}
	}
	n++;
}

template <class T>
ListItem<T>* LinkedList<T>::getHead()
{
	return head;
}

template <class T>
ListItem<T>* LinkedList<T>::getTail()
{
	return tail;
}

template <class T>
ListItem<T>* LinkedList<T>::searchFor(T item)
{
	if (head==NULL)
		cout << "> The list is empty!!! \n\n";
	else
	{
		if (tail->value==item)
			return tail;
		else if (head->value==item)
			return head;
		else
		{
			ListItem<T> *temp1 = head;

			while (temp1->value!=item && temp1->next!=NULL)
			{
				temp1 = temp1->next;
			}

			if (temp1->next==NULL)
			{
				return NULL;
			}
			else
				return temp1;
		}
	}
}

template <class T>
void LinkedList<T>::deleteElement(T item)
{
	if (head->value==item)
		deleteHead();
	else if (getTail()->value==item)
		deleteTail();
	else
	{
		ListItem<T> *temp = searchFor(item);

		temp->prev->next = temp->next;
		temp->next->prev = temp->prev;

		delete temp;
		n--;
	}
}

template <class T>
void LinkedList<T>::deleteHead()
{
	if (head!=NULL)
	{
		ListItem<T> *temp = head;

		if (head->next!=NULL)
		{
			head->next->prev = NULL;
			head = head->next;
		}
		else
			head = NULL;

		delete temp;
		n--;
	}
	else if (head==NULL)
		cout << "> The list is already empty!!! \n\n";

}

template <class T>
void LinkedList<T>::deleteTail()
{
	if (head!=NULL)
	{
		if (tail->prev != NULL)
		{
			ListItem<T> * tmp = tail->prev;
			tmp->next = NULL;
			delete tail;
			tail = tmp;
			n--;
		}
		else
			deleteHead();
	}
	else
		cout << "> The list is already empty!!! \n\n";
}

template <class T>
int LinkedList<T>::length()
{
	return n;
}

template <class T>
void LinkedList<T>::reverse()
{
	if (head==NULL)
		cout << "> The list is empty. \n\n";
	else
	{
		ListItem<T> *temp = head;
		LinkedList<T> newL;

		newL.insertAtHead(temp->value);

		while (temp->next!=NULL)
		{
			temp = temp->next;
			newL.insertAtHead(temp->value);
		}

		while (head!=NULL)
		{
			deleteHead();
		}

		head = newL.head;
		newL.head = NULL;
	}
}

template <class T>
void LinkedList<T>::parityArrangement()
{
	if (head==NULL)
		cout << "> The list is empty. \n\n";
	else
	{
		int len = length();
		int counter=1;

		ListItem<T> *temp1 = head;
		ListItem<T> *temp2 = head->next;
		ListItem<T> *temp3 = head->next;

		while (temp1->next!=NULL && temp2->next!=NULL)
		{
			temp1->next = temp1->next->next;
			temp1 = temp1->next;

			temp2->next = temp2->next->next;
			temp2 = temp2->next;

			if (temp1->next==NULL || temp1->next->next==NULL)
				temp1->next = temp3;
		}

		temp1 = head;

		while (temp1->next!=NULL)
		{
			temp1->next->prev = temp1;
			temp1 = temp1->next;
		}
	}
}

#endif
