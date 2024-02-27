package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Class for the tests of the LinkedListStack class.
 * 
 * @author William Ngo and Elijah Potter
 * @version 02/27/2023
 */
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
	void testIsEmptyBase() {
		assertFalse(oneStack.isEmpty());
		assertFalse(bigStack.isEmpty());
	}
	
	@Test
	void testIsEmptyEdge() {
		assertTrue(emptyStack.isEmpty());
	}

	@Test
	void testClearBasic() {
		oneStack.clear();
		assertTrue(oneStack.isEmpty());
		bigStack.clear();
		assertTrue(bigStack.isEmpty());
	}
	
	@Test
	void testClearEdge() {
		emptyStack.clear();
		assertTrue(emptyStack.isEmpty());
	}
	
	@Test
	void testPeekBasic() {
		assertEquals(99, bigStack.peek());
		assertEquals(1, oneStack.peek());
	}
	
	@Test
	void testPeekException() {
		assertThrows(NoSuchElementException.class, () -> {emptyStack.peek();});
		bigStack.clear();
		assertThrows(NoSuchElementException.class, () -> {bigStack.peek();});
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
	void testPushBasic() {
		oneStack.push(2);
		assertEquals(2, oneStack.peek());
		bigStack.push(101);
		assertEquals(101, bigStack.peek());
	}
	
	@Test
	void testPushEdge() {
		emptyStack.push(100);
		assertEquals(100, emptyStack.peek());
	}
	
	@Test
	void testSizeBasic() {
		assertEquals(100, bigStack.size());
		assertEquals(1, oneStack.size());
		
	}
	
	@Test
	void testSizeEdge() {
		assertEquals(0, emptyStack.size());
		bigStack.clear();
		assertEquals(0, bigStack.size());
	}

}
