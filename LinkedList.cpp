#include <iostream>
using namespace std;

class node{
    public:
    int data;
    node * next;
};

class LinkedList{
	public:
	node  *head = NULL;
	bool isEmpty(node *head){
	    if(head == NULL){
		return true;
	    }
	    else
		return false;
	}

	void insertFirstElement(node *&head, int number){
	    node *temp = new node;
	    temp->data = number;
	    temp->next = NULL;
	    head = temp;
	}
	 
	void insert(node *&head, int number){
	    if(isEmpty(head)){
		insertFirstElement(head,number);
	    }
	    else{
		node *temp = new node;
		temp->data = number;
		temp->next = NULL;
		node *cur = head;
		while (cur -> next != NULL){
			cur = cur -> next;
		}
		cur -> next = temp;
	    }
	}

	void remove(node *&head){
		node *cur = head;
		while (cur -> next != NULL){
			cur = cur -> next;
		}	
		if (isEmpty(head)){
			cout << "Linked List is already empty\n";
		} else if (head == cur){
			delete head;
			head = NULL;
		}
		else{
			node *temp = head;
			head = head -> next;
			delete temp;
		}
	}


	void showList(node *current){
	    if (isEmpty(current)){
		cout << "Linked List is empty, nothing to show\n";
	    }
	    else{
		cout << " The list contains : ";
		while(current != NULL){
		    cout << current->data << "->";
		    current = current -> next;
		}
		cout <<"NULL \n";
		}
	}

	void cumulativeSum(node *current){
		if(isEmpty(current)){
			cout << "The linked list is empty\n";
		}
		int sum = current-> data;
		
		while (current-> next != NULL){
			current = current-> next;
			current-> data = sum + current-> data;
			sum += current -> data;
		}
	}
	
	void alternate(node *current, int length){
		node *start = current -> next;		
		if (isEmpty(current)){
			cout << "The list is empty\n";		
		}		
		
		// this deals with the even nodes		
		node *second = current -> next;
		for (int i = 0; i < length; i++){
			if (i % 2 == 0){
				current -> next = second -> next;
				current = current -> next;
				second = current -> next;
			}
		}

		// now deal with the odd nodes
		node *currentTwo = start;
		for (int i = 1; i < length; i++)
		
	}

	int size(node *current){
		int s = 1;
		while (current -> next != NULL){
			s++;
			current = current -> next;
		}
		return s;
	}
};



int main(){

    LinkedList ll;
    int choice;
    int number;
    
    do{
        cout << "Menu \n";
        cout << "1. Add an item \n";
        cout << "2. Remove an item \n";
        cout << "3. Show the list \n";
	cout << "4. Calculate the cumulative sum \n";
	cout << "5. Show the size of the list\n";
        cout << "6. Quit\n";
        cin >> choice;
        
        if (choice == 1){
            cout << "Enter a number: ";
            cin >> number;
            ll.insert (ll.head, number);
        }
        else if (choice == 2){
            ll.remove(ll.head);
        }
        else if (choice == 3){
            ll.showList(ll.head);
        } else if (choice == 4){
		ll.cumulativeSum(ll.head);
        }else if (choice == 5){
		cout << ll.size(ll.head) << "\n";
	} else{
            choice = 6;
        }
    }while (choice !=6);


    return 0;
}
