package week7;

import java.util.*;

public class BurnTree {

	//creating a map to store node and its parent as key value pair 
	public static Map <Integer,Integer> nodeParent = new LinkedHashMap<Integer,Integer>();
	
	//Creating a blueprint for each vertex in the tree 
	static class Node
	{
	    int key;
	    Node left, right, parent;
	    boolean burntstatus;
	}
	 
	//constructor to initialize the data and the variables to null
	static Node newNode(int item)
	{
	    Node temp = new Node();
	    temp.key = item;
	    temp.left = null;
	    temp.right = null;
	    temp.parent = null;
	    temp.burntstatus=false;
	    return temp;
	}
	
	 
	// function which does the inorder traversal i:e left root right
	static void inorder(Node root)
	{
	    if (root != null)
	    {
	        inorder(root.left);
	        
	        
	        //if parent is null it means this is the main root node so we give it dummy value of -1
	        //so that we can know it is the root node when we encounter -1 later
	        if (root.parent == null) {
	        	nodeParent.put(root.key,-1);
	        	
	        }
	        
	        //if parent is not null then we simply assign the parent
	        else {
	        	
	        	nodeParent.put(root.key,root.parent.key);
	        }
	        
	        
	        
	        inorder(root.right);
	    }
	}
	public static int check() {
		
		//creating a stack to store only the visited nodes
		Stack<Integer> n = new Stack<Integer>();
		
		//creating another stack of type ArrayList<Integer> to store the child,parent,grandparent nodes 
		Stack <ArrayList<Integer>> s = new Stack <ArrayList<Integer>>();
		
		//looping through the hashmap of nodes and parent collection 
		//we will take first value which is the parent of the leaf node
		//then will search for same value in the hashmap and and add it into the array list and into the stack to avoid repetition 
		 for (Map.Entry mapElement : nodeParent.entrySet()) {
	            int key = (int)mapElement.getKey();
	            int value = (int)mapElement.getValue();
	            ArrayList<Integer> arr = new ArrayList<Integer>();
	            
	            //if value is -1 it means it is root node . we will skip root node for now because in most of the case root node will
	            //get burned by its child
	            if(value!=-1) {
	            	//if the stack does contain the value already means it is already burnt and we only need to take unburnt
	            	//cases therefore we proceed only if the stack doesnot contain the value
	            	 if(!n.contains(value)) {
	            		 
	            		 //searching for the value in the hashmap either as key or as value and if found we add and push the same key
	            		 //into the stack of arraylist and stack of visited elements respectively
	 	            	for (Map.Entry insidemapElement : nodeParent.entrySet()) {
	 		            	if((int)insidemapElement.getKey()==value || (int)insidemapElement.getValue()==value) {
	 		            		arr.add((int)insidemapElement.getKey());
	 		            		n.push((int)insidemapElement.getKey());
	 		            	}
	 		            }
	 		            //same loop but this time we add and push the value 
	 		            for (Map.Entry insidemapElement : nodeParent.entrySet()) {
	 		            	if((int)insidemapElement.getKey()==value || (int)insidemapElement.getValue()==value) {
	 		            		arr.add((int)insidemapElement.getValue());
	 		            		n.push((int)insidemapElement.getValue());
	 		            	}
	 		            }
	 		            
	 		            //by the completion of the 2 for loops , the left most deep children,parent and grandparent are burnt successfully
	 		            //Arraylist contains this set of burnt vertices finally we add it into the stack 
	 		            s.add(arr);
	 	            }
	            }
	            

	            
	           
	            
	           
	          
	        }
		 
		 //worst case scenario checking if the root node is not burnt
		 int rootnode=-99999;
		 for (Map.Entry insidemapElement : nodeParent.entrySet()) {
          	if((int)insidemapElement.getValue()==-1) {
          		rootnode=((int)insidemapElement.getKey());
          		
          	}
          }
		 //if it is not burnt we simply burn it and add it to the stack
         if(!n.contains(rootnode)) {
        	 ArrayList<Integer> arr = new ArrayList<Integer>();
         	s.add(arr);
         } 	
         
		 //the stack contains the list of burnt children,parent,grandparent groups
		 //the size of which gives the minimum number of nodes needed to be burnt to burn the whole tree
		 return s.size(); 
	}
	
	
	//insert function which inserts the provided number to its right position in the tree
	static Node insert(Node node, int key)
	{
	    
	    if (node == null) return newNode(key);
	 
	    
	    if (key < node.key)
	    {
	        Node lchild = insert(node.left, key);
	        node.left = lchild;
	 
	      
	        lchild.parent = node;
	    }
	    else if (key > node.key)
	    {
	        Node rchild = insert(node.right, key);
	        node.right = rchild;
	 
	        
	        rchild.parent = node;
	    }
	 
	    
	    return node;
	}
	 
	
	public static void main(String[] args)
	{
	   
	    Node root = null;
	    root = insert(root, 80);
	    insert(root, 40);
	    insert(root, 60);
	    insert(root, 10);
	   // insert(root, 70);
	   // insert(root, 60);
	   // insert(root, 80);
	 
	   
	    inorder(root);
	    int res=check();
	    System.out.println(res);
	}
}