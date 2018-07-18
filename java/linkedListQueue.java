
import java.io.*;

public class linkedListQueue {
	private listNode dummyNode;
	private listNode head = new listNode(-1);
	private listNode tail = new listNode(-1);					
	private int size = 0;
	
	linkedListQueue(){												//initially, head and tail point to dummyNode
		dummyNode = new listNode();
		head.setPrev(dummyNode);
		head.setNext(dummyNode);
		tail.setPrev(dummyNode);
		tail.setNext(dummyNode);
	}
	
	public void addTail(int n){										//inserting new item to the end of the queue
		listNode newNode = new listNode(n);							//create a newNode for new data
		if(isEmpty()){												//if the queue is empty
			head.setNext(newNode);									//new Node will be the first data item
			tail.setPrev(newNode);									//new Node will also be the last data item
			newNode.setPrev(head);									//new Node will point to head as prev
			newNode.setNext(tail);									//new Node will point to tail as next
		}
		else{														//else
			tail.getPrev().setNext(newNode);						//last data item points to new item as next 
			newNode.setPrev(tail.getPrev());						//new Node points to previous last item as prev
			tail.setPrev(newNode);									//new Node will become the last data item
			newNode.setNext(tail);									//new Node points to tail as next
		}
		size++;														
	}
	
	public int deleteHead(){										//deletes the first data item
		int tempData;
		tempData = head.getNext().getData();						//gets the first item's data
		head.getNext().delete();									//remove current first item							
		size--;														
		return tempData;											//returns the value of deleted item
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		else
			return false;
	}
	
	public void printQueue(FileWriter outFile){
		PrintWriter write = new PrintWriter(outFile);
		listNode tempNode = new listNode();
		tempNode = head.getNext();									//access the first item
		while(tempNode.getData() != -1){							//while current node is not empty
			write.print(tempNode.getData() + ", ");					//print data to output file
			tempNode = tempNode.getNext();							//gets the next data item
		}
	}
}
