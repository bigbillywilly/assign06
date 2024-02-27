package assign06;

import java.util.NoSuchElementException;

/**
 * A class to represent a LinkedListStack. Backed by a SinglyLinkedList.
 * 
 * @param <E> - any generic type
 * @authors  Elijah Potter and William Ngo
 * @version 2/27/2024
 */
public class LinkedListStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list;

	/**
	 * Constructor for a LinkedListStack.
	 */
	public LinkedListStack() {
		this.list = new SinglyLinkedList<>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/**
	 * Method that determines whether a list is empty.
	 * 
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException("List is empty");
		return list.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E pop() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException("List is empty");
		return list.deleteFirst();
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@Override
	public void push(E element) {
		list.insertFirst(element);
	}

	/**
	 * Method that returns the size.
	 * 
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();

	}
}
