import java.io.*;
import java.util.Scanner;

public class RadixSort {
	private int tableSize = 10;
	private linkedListQueue [][] hashTable = new linkedListQueue[2][tableSize];			//a hashTable containing 2 rows for iteration and 10 columns for digits 0-9
	private int currentTable;															//current table for storage
	private int previousTable;															//previous table for storage
	private int currentDigit;															//current comparison: one's place, ten's place, hundred's place
	private int currentQueue;															//for traversal from digits 0 -> 9
	private int hashIndex;																//digit value of the integer
	private int iteration = 1;															//starts out as the first sort
	linkedListStack LLStack = new linkedListStack();									//create a stack to store the input integers
	int longestDataLength;																//highest # of digits 
	
	RadixSort(){																		//initializes the queueList 
		for(int iteration = 0; iteration < 2; iteration++){								//each iteration
			for(int digit = 0; digit < tableSize; digit++){								//each digit
				hashTable[iteration][digit] = new linkedListQueue();											
			}
		}
		currentTable = 0;																//starts out in table 0
		previousTable = 1;																//initialized to 1 for the convenience of swapping table to reuse later		
		currentDigit = 1;																//starts comparing one's place
	}
	
	public int getDigit(int val){														//returns the digit at specific place
		return (val/currentDigit)%10;												
	}
	
	public void printTable(FileWriter oFile){											//prints the integers of current iteration in order from 0->9 left->right
		PrintWriter write = new PrintWriter(oFile);										
		write.println("<Iteration: " + iteration +">");
		for(currentQueue = 0; currentQueue<tableSize; currentQueue++){					//for each digit of queues
			if(!hashTable[currentTable][currentQueue].isEmpty()){						//if current queue is not empty
				write.print("Table[" + currentTable + "][" + currentQueue + "]: ");		//output the notation for current queueList
				hashTable[currentTable][currentQueue].printQueue(oFile);				//print current queue
				write.println("");
			}
		}
		write.println("");
		if(iteration == longestDataLength){												//if printed the last table
			write.println("Positive integers sorted:");									//print final sorted values
			for(currentQueue=0; currentQueue<tableSize; currentQueue++){				//from digits 0->9
				if(!hashTable[currentTable][currentQueue].isEmpty()){					//if current queue is not empty
					hashTable[currentTable][currentQueue].printQueue(oFile);			//print current queue
				}
			}
			write.close();
		}
	}
	
	public void update(){																//update values for next iteration			
		iteration++;																	
		currentDigit = currentDigit * 10;												//next digit would be times 10
		int temp = currentTable;														//swap the tables for reuse
		currentTable = previousTable;	
		previousTable = temp;
	}
	
	public void sort(FileWriter resultFile){													//store integers by their next integer
		int tempData;																
		while(iteration <= longestDataLength){													//while we haven't finish comparing all the digits, continue sorting them
			if(iteration == 1){																	//if it's the first iteration, transfer data from stack to queue first
				while(!LLStack.isEmpty()){														//while stack is not empty
					tempData = LLStack.Pop();													//remove and return the last data item
					hashIndex = getDigit(tempData);												//gets the digit at specific place
					hashTable[currentTable][hashIndex].addTail(tempData);						//add the data node into the corresponding queue referenced by its digit
				}
				printTable(resultFile);
				update();
			}																																							
			for(currentQueue = 0; currentQueue<tableSize; currentQueue++){					//traverse through the table and re-sort them by the next digit
				while(!hashTable[previousTable][currentQueue].isEmpty()){					//if there are integers for current digit
					tempData = hashTable[previousTable][currentQueue].deleteHead();			//access the integers from left to right, starting at the head of the queue
					hashIndex = getDigit(tempData);											
					hashTable[currentTable][hashIndex].addTail(tempData);					//store it into the other table by the new digit
				}
			}
			printTable(resultFile);
			update();
			sort(resultFile);																//continue sorting if necessary
		}
	}
	
	public void readFile(File r, FileWriter output) throws FileNotFoundException{
		Scanner readF = new Scanner(r);
		int current;
		int maximum = -1;																				//positive integers must be greater to start out with
		listNode newNode;
		
		//Reads the input file and store the integers into a stack, finds the largest integer
		if(readF.hasNextInt()){																			//if there are more integers in the file
			while(readF.hasNextInt()){																	//while there are more integers in the file								
				current = readF.nextInt();																//store the next integer into 'current'
				if(current < 0){																		//if 'current' integer is negative
					System.err.println("No negative integer allowed!! Try again...");					//print error message
					System.exit(0);																		//exit the program
				}
				if(current > maximum){																	//if the 'current' integer is greater than the largest integer on file					
					maximum = current;																	//set largest integer on file to 'current' integer			
				}
				newNode = new listNode(current);														//create a node for 'current' integer
				LLStack.push(newNode);																	//push the new integer node onto the top of the stack
			}//continues to the next existing integer until file is empty
			LLStack.printStack(output);
		}
		else{																							//if the file is totally empty from the start
			System.err.println("No integers!! Try again...");												
			System.exit(0);																	
		}
		longestDataLength = Integer.toString(maximum).length(); 										//calculates the number of digits of the largest positive integer
		readF.close();
	}
}
