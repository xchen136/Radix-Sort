//Cs323 - Project#4: RadixSort by Xiaomin Chen

import java.io.*;

public class main {
	public static void main(String [] args) throws IOException{
		File inFile = new File(args[0]);													//access the input file					
		FileWriter outFile = new FileWriter(args[1]);										//access the output file		
		RadixSort rs = new RadixSort();														//create a radixSort and initialize the hash table
		
		rs.readFile(inFile, outFile);														//read, store, and print input data in a stack style
		rs.sort(outFile);																	//sort the input integers by radix sort																	
	}
}
