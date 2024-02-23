package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing a Singly-Linked List data type.
 * 
 * @param <E> - The data type of the list
 * @authors Elijah Potter & William Ngo
 * @version 2/22/2024
 */
public class SinglyLinkedList<E> implements List<E> {
	private int size = 0;
	private Node<E> head;

	public SinglyLinkedList() {

	}

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
		if (index > size() || index < 0)
			throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");
		if (index == 0) {
			insertFirst(element);
		} else {
			int tempSize = size;
			int tempTracker = 0;
			for (int i = size - 1; i >= index; i--, tempTracker++) {
				insertFirst(get(tempSize - 1));
			}
			insertFirst(element);
			for (int i = tempSize - tempTracker; i > 0; i--) {
				insertFirst(get(tempSize));
			}
			this.size = tempSize + 1;
		}
	}

	// @Override
	// public void insert(int index, E element) throws IndexOutOfBoundsException {
	// 	if (index > size && index < 0)
	// 		throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");

	// 	if (index == 0) {
	// 		insertFirst(element);
	// 	} else {
	// 		Node<E> current = head;
	// 		for (int i = 0; i < index - 1; i++) {
	// 			current = current.link;
	// 		}
	// 		Node<E> newNode = new Node<>(element, current.link);
	// 		current.link = newNode;
	// 		size++;
	// 	}
	// }

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("List is empty.");
		}
		return get(0);
	}


	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index > size() || index < 0)
			throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");
		Iterator<E> iter = iterator();

		for (int i = 0; i < index; i++) {
			iter.next();
		}
		return iter.next();
	}

	@Override
	public E deleteFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("List is empty.");
		E tempDeleted = head.value;
		head = head.link;
		size--;
		return tempDeleted;
	}

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

	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		Iterator<E> iter = iterator();
		for (int i = 0; i < size; i++) {
			arr[i] = iter.next();
		}
		return arr;
	}

	@Override
	public Iterator<E> iterator() {
		return new SLLIter();
	}

	private class SLLIter implements Iterator<E> {
		private int pointer;
		private boolean nextCalled;

		public SLLIter() {
			this.pointer = 0;
			this.nextCalled = false;
		}

		@Override
		public boolean hasNext() {
			return pointer < size;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();

			nextCalled = true;
			Node<E> tempNode = head;
			for (int i = 0; i < pointer; i++) {
				tempNode = tempNode.getLink();
			}
			pointer++;
			return tempNode.getValue();
		}

		@Override
		public void remove() {
			if (!nextCalled)
				throw new IllegalStateException();
			nextCalled = false;
			// TODO remove "delete"
			delete(pointer);
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

		public Node(E value, Node<E> link) {
			this.value = value;
			this.link = link;
		}

		public Node<E> getLink() {
			return link;
		}

		public E getValue() {
			return value;
		}

	}

}
