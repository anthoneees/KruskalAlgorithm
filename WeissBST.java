// Binary Search Tree class From your textbook (Weiss)
//TODO:
//  (1) Update this code to meet the code style and JavaDoc style requirements.
//			Why? So that you get experience with the code for a binary search tree!
//			Also, this happens a lot in industry (updating old code
//			to meet your new standards). 
//  (2) Change remove() to be "predecessor replacement".
//  (3) Implement three more methods needed by other classes of this project:
//		size(),  toString(), and values(). Make sure to add JavaDoc for those. 


import java.util.LinkedList; //only for the return of values(), do not use it anywhere else

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 * @param <T> generic value that we input to specify what we are using the BST for
 */
public class WeissBST<T extends Comparable<? super T>>
{

	//--------------------------------------------------------
	// CODE PROVIDED from WEISS
	//--------------------------------------------------------
	// Do NOT change the implementation - you should only edit 
	// in order to pass JavaDoc and style checking.
	//--------------------------------------------------------

	// Basic node stored in unbalanced binary search trees
	// Note that this class is not accessible outside
	// of this class.

	/**
	 * innerClass for node definition for the BST.
	 * @param <T> generic type
	 */
	private class BinaryNode<T>
	{
		// Constructor
		/**
		 * constructor for the node.
		 * @param theElement node value
		 */
		BinaryNode( T theElement )
		{
			element = theElement;
			left = right = null;
		}

		/**
		 * Generic node data.
		 */
		T element;  // The data in the node
		/**
		 * Left child of the node.
		 */
		BinaryNode<T> left;	 // Left child
		/**
		 * Right child of the node.
		 */
		BinaryNode<T> right;	// Right child
	}

	//NOTE: you may not have any other instance variables, only this one below.
	//if you make more instance variables your binary search tree class 
	//will receive a 0, no matter how well it works
	
	/**
	 * The tree root.
	 */
	private BinaryNode<T> root;



	/**
	 * Construct the tree.
	 */
	public WeissBST( )
	{
		root = null;
	}

	/**
	 * Insert into the tree.
	 * @param x the item to insert.
	 * @throws Exception if x is already present.
	 */
	public void insert( T x )
	{
		root = insert( x, root );
	}

	/**
	 * Remove minimum item from the tree.
	 * @throws Exception if tree is empty.
	 */
	public void removeMin( )
	{
		root = removeMin( root );
	}

	/**
	 * Find the smallest item in the tree.
	 * @return smallest item or null if empty.
	 */
	public T findMin( )
	{
		return elementAt( findMin( root ) );
	}


	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return the matching item or null if not found.
	 */
	public T find( T x )
	{
		return elementAt( find( x, root ) );
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Internal method to get element field.
	 * @param t the node.
	 * @return the element field or null if t is null.
	 */
	private T elementAt( BinaryNode<T> t )
	{
		return t == null ? null : t.element;
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 * @throws Exception if x is already present.
	 */
	private BinaryNode<T> insert( T x, BinaryNode<T> t )
	{
		if( t == null )
			t = new BinaryNode<T>( x );
		else if( x.compareTo( t.element ) < 0 )
			t.left = insert( x, t.left );
		else if( x.compareTo( t.element ) > 0 )
			t.right = insert( x, t.right );
		else
			throw new IllegalArgumentException( "Duplicate Item: " + x.toString( ) );  // Duplicate
		return t;
	}


	/**
	 * Internal method to remove minimum item from a subtree.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 * @throws Exception if t is empty.
	 */
	private BinaryNode<T> removeMin( BinaryNode<T> t )
	{
		if( t == null )
			throw new IllegalArgumentException( "Min Item Not Found");
		else if( t.left != null ) //if the left subtree exists
		{
			t.left = removeMin( t.left ); //recursively call removeMin() on the left child which finds the minimum value
			return t;
		}
		else //once minimum value is found return the right child of the node, connecting the right child to its parent
			return t.right;
	}	

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<T> findMin( BinaryNode<T> t )
	{
		if( t != null )
			while( t.left != null ) //go all the way to the left
				t = t.left;

		return t; //return the item all the way to the left
	}


	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private BinaryNode<T> find( T x, BinaryNode<T> t )
	{
		while( t != null )
		{
			if( x.compareTo( t.element ) < 0 )
				t = t.left;
			else if( x.compareTo( t.element ) > 0 )
				t = t.right;
			else
				return t;	// Match
		}
		
		return null;		 // Not found
	}

	//--------------------------------------------------------
	// CODE THAT YOU MAY NEED TO CHANGE
	//----------------- ---------------------------------------
	// We need the BST removal to be "predecessor replacement".
	// Make necessary changes to match the expected behavior.
	// Feel free to add private helper methods as needed.
	//--------------------------------------------------------

	/**
	 * Remove from the tree using predecessor replacement.
	 * @param x the item to remove.
	 * @throws Exception if x is not found.
	 */
	public void remove( T x )
	{
		root = remove( x, root );
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 * @throws Exception if x is not found.
	 */
	private BinaryNode<T> remove( T x, BinaryNode<T> t )
	{
		if( t == null )
			throw new IllegalArgumentException( "Item Not Found: " + x.toString( ) );
		if( x.compareTo( t.element ) < 0 ) //if x is less than the current node go to the left subtree, else move to the right subtree
			t.left = remove( x, t.left );
		else if( x.compareTo( t.element ) > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // If the node has two children
		{
			t.element = findMax( t.left ).element; //the value of t = the element of the leftmost node in the right subtree
			t.left = removeMax( t.left ); // delete the node that originally contained the minimum element in the right subtree
		}
		else //if had one child or no child,
			t = ( t.left != null ) ? t.left : t.right; //if t has left child, t=t.left, if t does not have left child t = t.right
		return t; //return modified tree
	}

	/**
	 * helper method used in remove to find the max value of a tree (rightMost node).
	 * @param t root of the tree
	 * @return the node of the max value.
	 */
	private BinaryNode<T> findMax( BinaryNode<T> t )
	{
		if( t != null )
			while( t.right != null ) //go all the way to the right
				t = t.right;

		return t; //return the item all the way to the right
	}

	/**
	 * helper method that removes the max value in a tree.
	 * @param t the root of the tree.
	 * @return the value to be removed.
	 */
	private BinaryNode<T> removeMax( BinaryNode<T> t )
	{
		if( t == null )
			throw new IllegalArgumentException( "Min Item Not Found");
		else if( t.right != null ) //if the right subtree exists
		{
			t.right = removeMax( t.right ); //recursively call removeMin() on the left child which finds the minimum value
			return t;
		}
		else //once minimum value is found return the right child of the node, connecting the right child to its parent
			return t.left;
	}
	//--------------------------------------------------------
	// CODE YOU MUST IMPLEMENT
	//--------------------------------------------------------
	// Feel free to define private helper methods.
	// Remember to add JavaDoc for your methods.
	//--------------------------------------------------------

	/**
	 * report number of nodes in tree. O(N) N - Tree Size.
	 * @return 0 for null trues
	 */
	public int size(){
		return sizeHelper(root);
	}

	/**
	 * helper method for size that returns the size of the tree.
	 * @param t current node in the tree.
	 * @return size of the tree
	 */
	private int sizeHelper(BinaryNode<T> t){
		if(t == null){
			return 0;
		}
		else{
			return 1 + sizeHelper(t.left) + sizeHelper(t.right);
		}
	}
	

	// Return a string representation of the tree
	// follow IN-ORDER traversal to include all nodes.
	// Include one space after each node.
	// O(N): N is the tree size
	//
	// Return empty string "" for null trees.
	// Check main method below for more examples.
	//
	// Example 1: a single-node tree with the root data as "A"
	//   toString() should return "A "
	//
	// Example 2: a tree with three nodes:      B
	//										   / \
	//										  A   C 
	// toString() should return "A B C "

	/**
	 * toString method that prints the tree using strings.
	 * @return String version of a tree
	 */
	public String toString(){
		String ans = "";
		if(root == null){
			return ans;
		}
		//System.out.println(ans);
		return toStringHelper(root, ans);
  	}

	/**
	 * helper for toString that prints in inorder traversal.
	 * @param t root of the tree we will be printing.
	 * @param current String that holds current toString value
	 * @return string to be printed
	 */
	private String toStringHelper(BinaryNode<T> t, String current){
		if(t.left != null){
			current = toStringHelper(t.left, current);
		}
		current = current + t.element + " ";
		if(t.right != null){
			current = toStringHelper(t.right, current);
		}
		return current;
	}
	
	// Return an array representation of all values 
	// following PRE-ORDER traversal.
	// O(N): N is the tree size
	//
	// Return an empty LinkedList for null trees.
	// Example : a tree with three nodes:       B
	//										   / \
	//										  A   C 
	//  values should return a linked list with B-->A-->C
	//Check main() below for more examples.

	/**
	 * return array representation of all values using PRE-ORDER traversal in O(N).
	 * @return array representation of tree.
	 */
	public LinkedList<T> values(){
		LinkedList<T> ans = new LinkedList<>();
		if(root == null){
			return ans;
		}
		return valuesHelper(root, ans);
	}

	/**
	 * helper for values that will be used to traverse through the tree and add items to the linked list.
	 * @param t root of the tree we will be traversing.
	 * @param ans linked list that will hold the values
	 * @return linked list that holds the values in t according to preorder traversal
	 */
	private LinkedList<T> valuesHelper(BinaryNode<T> t, LinkedList<T> ans){
		ans.add(t.element);
		if(t.left != null){
			valuesHelper(t.left, ans);
		}
		if(t.right != null){
			valuesHelper(t.right, ans);
		}
		return ans;
	}
	


	//--------------------------------------------------------
	// TESTING CODE
	//--------------------------------------------------------
	// Edit as much as you want ... do not forget JavaDoc
	//--------------------------------------------------------


	/**
	 * main method for testing.
	 * @param args not relevant in this case
	 */
	public static void main( String [ ] args )
	{
		WeissBST<Integer> t = new WeissBST<Integer>( );

		if (t.size()==0 && t.isEmpty() && t.find(310)==null){
			System.out.println("Yay 1");
		}
		
		t.insert(310);
		t.insert(112);
		t.insert(440);
		t.insert(330);
		t.insert(471);
		LinkedList<Integer> values = t.values();

		// Current tree:
		//			  310
		//           /   \
		//        112     440
		//                /  \
		//              330  471

		if (t.size()==5 && t.toString().equals("112 310 330 440 471 ") && !t.isEmpty()){
			System.out.println("Yay 2");
		}

		if (values.size()==5 && values.get(0)==310 && values.get(1)==112 &&
			values.get(2) == 440 && values.get(3)== 330 && values.get(4)== 471){
			System.out.println("Yay 3");
		}

		//remove
		t.remove(440);
		
		//check removal with predecessor replacement
		//tree expected:
		//
		//			  310
		//           /   \
		//        112     330
		//                  \
		//              	 471
		values = t.values();
		if (values.size() == 4 && values.get(2)==330 && values.get(3)==471){
			System.out.println("Yay 4");		
		}
		//System.out.print(t);
	
	}
	
}
