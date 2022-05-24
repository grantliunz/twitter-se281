package nz.ac.auckland.se281.a4.ds;

import org.apache.commons.lang3.ObjectUtils;

import java.util.NoSuchElementException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos
	 * TODO: Complete this method
	 * 
	 * @param pos:
	 *            an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException
	 *             if position is less than 0 or greater than
	 *             size-1
	 * @throws NoSuchElementException
	 *             if the element does not exist in the
	 *             LinkedList
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException{

		// Negative numbers are invalid
		if(pos < 0){
			throw new InvalidPositionException();
		}
		Node<T> n = head;

		//Traverses the list
		while(n != null){
			if(pos-- == 0){
				return n;
			}
			n = n.getNext();
		}
		throw new InvalidPositionException();
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element
	 *            a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		Node<T> n = new Node<T>(element);
		if (head != null) {
			n.setNext(head);
		}
		head = n;



	}

	/**
	 * This method adds a node with specified data as the end node of the list
	 * TODO: Complete this method
	 *
	 * @param element
	 *            a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> n = new Node<T>(element);
		if(head==null){
			head = n;
		}else{
			Node<T> i = head;
			while(i.getNext() != null){
				i = i.getNext();
			}
			i.setNext(n);

		}

	}

	/**
	 * This method gets the value of a node at a given position
	 * TODO: Complete this method
	 *
	 * @param pos
	 *            an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException
	 *             if position is less than 0 or greater than
	 *             size-1
	 */
	public T get(int pos) throws InvalidPositionException {
		return locateNode(pos).getValue();
	}

	/**
	 * This method adds an node at a given position in the List
	 * TODO: Complete this method
	 * 
	 * @param pos:
	 *            an integer, which is the position
	 * @param element:
	 *            the element to insert
	 * @throws InvalidPositionException
	 *             if position is less than 0 or greater than
	 *             size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {
		Node<T> n1 = new Node<T>(element);
		Node<T> n = head;
		//Checks if insert is at very start of LL
		if(pos == 0){
			n1.setNext(head);
			head = n1;
			return;
		}

		//Loops to find the pointer of node 1 position before pos
		while(pos>1){
			try{
				n = n.getNext();
				pos--;
			}catch (NullPointerException e){
				throw new InvalidPositionException();
			}

		}
		n1.setNext(n.getNext());
		n.setNext(n1);



	}

	/**
	 * This method removes an node at a given position
	 * TODO: Complete this method
	 *
	 * @param pos:
	 *            an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		Node<T> n1 = locateNode(pos);
		if(pos == 0){
			head = n1.getNext();
		}else{
			Node<T> n2 = locateNode(pos -1);
			n2.setNext(n1.getNext());
		}


	}

	/**
	 * This method returns the size of the Linked list
	 * TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {
		int i = 0;
		Node<T> n = head;
		while(n != null){
			n = n.getNext();
			i++;
		}
		return i;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}