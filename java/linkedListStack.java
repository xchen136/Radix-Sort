
import java.io.*;

public class linkedListStack {
	private listNode Top;
	private int size = 0;
	
	linkedListStack(){
		Top = new listNode();
	}
	
	public void push(listNode item){							//pushed new node onto the top of the stack
		if(isEmpty()){											//if stack is empty
			Top.setPrev(item);;									
			item.setNext(Top);										
		}
		else{													//else
			Top.getPrev().setNext(item);
			item.setPrev(Top.getPrev());
			item.setNext(Top);
			Top.setPrev(item);
		}
		size++;
	}
	
	public int Pop(){											//delete and returns the last node when stack is not empty			
		int tempData;
		tempData = Top.getPrev().getData();						//gets the data of the last item
		Top.getPrev().delete();									//delete the last data item
		size--;			
		return tempData;										//return the value of deleted node
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		else
			return false;
	}
	
	public void printStack(FileWriter outputFile){				//retrieves the output file through file writer
		PrintWriter write = new PrintWriter(outputFile);		//opens the output file
		listNode tempNode;
		tempNode = Top.getPrev();								//reference to last data item
		write.println("Stack:\t");								
		while(tempNode != null){								//while current node is not empty
			write.println(tempNode.getData());					//print current node's data
			tempNode = tempNode.getPrev();						//access the next data item
		}
		write.println("*************************************************************************************************************************************************");
	}
}
