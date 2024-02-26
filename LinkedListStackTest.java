package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListStackTest {

	LinkedListStack<Integer> emptyStack = new LinkedListStack<>();
	LinkedListStack<Integer> oneStack = new LinkedListStack<>();
	LinkedListStack<Integer> bigStack = new LinkedListStack<>();

	@BeforeEach
	void setUp() {
		for (int i = 0; i < 100; i++)
			bigStack.push(i);

		oneStack.push(1);
	}

	@Test
	void testIsEmpty() {
		assertTrue(emptyStack.isEmpty());
		assertFalse(oneStack.isEmpty());
		assertFalse(bigStack.isEmpty());
	}

	@Test
	void testClear() {
		oneStack.clear();
		assertTrue(oneStack.isEmpty());
		bigStack.clear();
		assertTrue(bigStack.isEmpty());
		emptyStack.clear();
		assertTrue(emptyStack.isEmpty());
	}
	
	@Test
	void testPeekEdgeAndBasic() {
		assertEquals(99, bigStack.peek());
		assertEquals(1, oneStack.peek());
	}
	
	@Test
	void testPeekException() {
		assertThrows(NoSuchElementException.class, () -> {emptyStack.peek();});
	}
	
	@Test
	void testPop() {
		assertEquals(99, bigStack.pop());
		assertEquals(98, bigStack.pop());
		assertEquals(1, oneStack.pop());
	}
	
	@Test
	void testPopException() {
		assertThrows(NoSuchElementException.class, () -> {emptyStack.pop();});
	}
	
	@Test
	void testPushEdgeAndBasic() {
		oneStack.push(2);
		assertEquals(2, oneStack.peek());
		emptyStack.push(100);
		assertEquals(100, emptyStack.peek());
		bigStack.push(101);
		assertEquals(101, bigStack.peek());
	}
	
	
	@Test
	void testSizeEdgeAndBasic() {
		assertEquals(100, bigStack.size());
		assertEquals(1, oneStack.size());
		assertEquals(0, emptyStack.size());
	}

}
