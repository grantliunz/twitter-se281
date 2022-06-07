package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 *
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 *
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> n = new Node<T>(element);
		if (!isEmpty()) {
			n.setNext(head);
		}
		head = n;

	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 *
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		Node<T> n = head;
		head = head.getNext();
		return n.getValue();

	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return head.getValue();

	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node<T> n = new Node<T>(element);
		if (isEmpty()) {
			head = n;
		} else {
			Node<T> i = head;
			while (i.getNext() != null) {
				i = i.getNext();
			}
			i.setNext(n);

		}

	}
}
