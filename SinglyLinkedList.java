package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing a Singly-Linked List data type.
 * 
 * @param <E> - The data type of the list
 * @authors Elijah Potter & William Ngo
 * @version 2/23/2024
 */
public class SinglyLinkedList<E> implements List<E> {
	private int size = 0;
	private Node<E> head;

	/**
	 * Constructor to create a singly Linked List.
	 */
	public SinglyLinkedList() {

	}

	/**
	 * Inserts an element at the beginning of the list.
	 * 
	 * @param element - the element to add
	 */
	@Override
	public void insertFirst(E element) {
		Node<E> second = this.head;
		Node<E> newHead = new Node<E>(element, second);
		this.head = newHead;

		size++;
	}

	/**
	 * Inserts an element at a specific position in the list. O(N) for a
	 * singly-linked list.
	 * 
	 * @param index   - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index > size())
	 */
	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");

		if (index == 0) {
			insertFirst(element);

		} else {
			Node<E> current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.link;
			}
			Node<E> newNode = new Node<>(element, current.link);
			current.link = newNode;
			size++;
		}
	}

	/**
	 * Gets the first element in the list.
	 * 
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("List is empty.");
		}
		return head.value;
	}

	/**
	 * Gets the element at a specific position in the list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");
		if (index == 0)
			return head.value;

		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.link;
		}

		return current.value;
	}

	/**
	 * Deletes and returns the first element from the list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E deleteFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("List is empty.");
		E tempDeleted = head.value;
		head = head.link;
		size--;
		return tempDeleted;
	}

	/**
	 * Deletes and returns the element at a specific position in the list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");
		if (index == 0)
			deleteFirst();
		Node<E> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.link;
		}
		E tempDeleted = current.link.value;
		current.link = current.link.link;
		size--;

		return tempDeleted;
	}

	/**
	 * Determines the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	@Override
	public int indexOf(E element) {
		Iterator<E> iter = iterator();
		int i = 0;
		while (iter.hasNext()) {
			if (iter.next().equals(element))
				return i;
			i++;
		}
		return -1;
	}

	/**
	 * Method to get the size of the list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Method to determine whether the list is empty or not.
	 * 
	 * @return true if this collection contains no elements; false, otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		this.head = new Node<E>(null, null);
		size = 0;
	}

	/**
	 * Generates an array containing all of the elements in this list in proper
	 * sequence (from first element to last element).
	 * 
	 * @return an array containing all of the elements in this list, in order
	 */
	@Override
	public Object[] toArray() {
		try {
			Object[] arr = new Object[size];
			Iterator<E> iter = iterator();
			for (int i = 0; i < size; i++) {
				arr[i] = iter.next();
			}
			return arr;
		} catch (NullPointerException e) {
			Object[] arr = new Object[0];
			return arr;
		}
	}

	/**
	 * Method to instantiate an iterator on a SinglyLinkedList object.
	 * 
	 * @return an iterator that goes from the first to last element. 
	 */
	@Override
	public Iterator<E> iterator() {
		return new SLLIter();
	}

	/**
	 * This class provides constructor and methods for the SinglyLinkedList
	 * Iterator.
	 */
	private class SLLIter implements Iterator<E> {
		
		private int pointer;
		private boolean nextCalled;

		/**
		 * Constructor to create a SinglyLinkedList Iterator.
		 */
		public SLLIter() {
			this.pointer = 0;
			this.nextCalled = false;
		}

		/**
		 * Method that checks if there is another element in the list.
		 */
		@Override
		public boolean hasNext() {
			return pointer < size;
		}

		/**
		 * Method to iterate once over, if possible.
		 * 
		 * @return E the element that was iterated over. 
		 */
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException("There is no next element.");

			nextCalled = true;
			Node<E> tempNode = head;
			for (int i = 0; i < pointer; i++) {
				tempNode = tempNode.link;
			}
			pointer++;
			return tempNode.value;
		}

		/**
		 * Method to remove the element that the pointer is poiting at.
		 */
		@Override
		public void remove() {
			if (!nextCalled)
				throw new IllegalStateException();
			nextCalled = false;
			Node<E> current = head;
			for (int i = 0; i < pointer - 1; i++)
				current = current.link;

			current.link = current.link.link;
			size--;

		}

	}

	/**
	 * This nested class creates Nodes that hold the value of what is inside and
	 * links to the value of the next node.
	 * 
	 * @param <E> - generic type
	 */
	private static class Node<E> {
		public E value;
		Node<E> link;

		/**
		 * Constructor for a node.
		 * 
		 * @param value - data within the node
		 * @param link - another node that the current Node is pointing to.
		 */
		public Node(E value, Node<E> link) {
			this.value = value;
			this.link = link;
		}

	}

}
