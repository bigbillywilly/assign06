package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("List is empty.");
		}
		return head.value;
	}

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
		Iterator<E> iter = iterator();

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
		Iterator<E> iter = iterator();
		int i = 0;
		while (iter.hasNext()) {
			if (iter.next().equals(element))
				return i;
		}
		i++;
		return -1;
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
		this.head = new Node<E>(null, null);
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
				tempNode = tempNode.link;
			}
			pointer++;
			return tempNode.value;
		}

		@Override
		public void remove() {
			if (!nextCalled)
				throw new IllegalStateException();
			nextCalled = false;

			if(pointer == 0)
				head = head.link;
			
			Node<E> current = head;
			for(int i = 0; i < pointer-1; i++)
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

		public Node(E value, Node<E> link) {
			this.value = value;
			this.link = link;
		}

	}

}
