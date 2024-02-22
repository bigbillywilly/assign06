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
	 * Inserts an element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 */
	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if (index > size() && index < 0)
			throw new IndexOutOfBoundsException("Index must be positive and within the size of the list.");
		
		if(on last node) {
			Node<E> newNode = new Node<E>(element, null);
		}
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E deleteFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
			E temp = get(pointer);
			pointer++;
			return temp;
		}

		@Override
		public void remove() {
			if (!nextCalled)
				throw new IllegalStateException();

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

	}

}
