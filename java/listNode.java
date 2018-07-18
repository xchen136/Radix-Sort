
public class listNode {
	private int data;
	private listNode prev = null;									//prev and next points to NULL initially
	private listNode next = null;
	
	listNode(){
	}
	
	listNode(int value){											//initializes the data value
		data = value;
	}
	
	public void delete(){											//deletes current node by having no nodes point to current node
		if(prev!=null & next!=null){								//if both previous and next node exists
			prev.setNext(next);										//modify both prev and next pointer
			next.setPrev(prev);
		}
		else if(prev!=null){										//if only previous node exists
			prev.setNext(null);										//only can modify non-null pointer
		}
		else{														//if only next node exists
			next.setPrev(null);										//only can modify non-null pointer
		}
	}//deleted node will be garbage collected by the JVM since no node will point to it, therefore no need to reassign its pointers
	
	public int getData(){
		return data;
	}
	public listNode getPrev(){
		return prev;
	}
	public listNode getNext(){
		return next;
	}
	
	public void setData(int val){
		data = val;
	}
	public void setPrev(listNode p){
		prev = p;
	}
	public void setNext(listNode n){
		next = n;
	}
	
	
}
