package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * A class containing test cases for the SinglyLinkedList class
 * 
 * @authors Elijah Potter & William Ngo
 * @version 2/23/2024
 */
class SinglyLinkedListTest {

	@Test
	void testInsertFirst() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals("[5, 4, 3, 2, 1]", Arrays.toString(testList.toArray()), "Failed insert first test");
	}

	@Test
	void testInsert() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		testList.insert(2, 77);
		assertEquals("[5, 4, 77, 3, 2, 1]", Arrays.toString(testList.toArray()), "Failed insert test");
	}

	@Test
	void testInsertMultiple() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		testList.insert(2, 99);
		testList.insert(4, 18);
		testList.insert(1, 37);
		assertEquals("[5, 37, 4, 99, 3, 18, 2, 1]", Arrays.toString(testList.toArray()), "Failed insert test");
	}

	@Test
	void testInsertEnds() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		testList.insert(5, 99);
		testList.insert(0, 77);
		assertEquals("[77, 5, 4, 3, 2, 1, 99]", Arrays.toString(testList.toArray()), "Failed insert test");
	}

	@Test
	void testInsertError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.insert(-1, 99);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.insert(6, 99);
		});
	}

	@Test
	void testGetFirst() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals(5, testList.getFirst(), "Failed get first test");
	}

	@Test
	void testGetFirstError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		assertThrows(NoSuchElementException.class, () -> {
			testList.getFirst();
		});
	}

	@Test
	void testGet() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals(5, testList.get(0), "Failed get test");
		assertEquals(4, testList.get(1), "Failed get test");
		assertEquals(3, testList.get(2), "Failed get test");
		assertEquals(2, testList.get(3), "Failed get test");
		assertEquals(1, testList.get(4), "Failed get test");
	}

	@Test
	void testGetError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.get(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.get(5);
		});
	}

	@Test
	void testDeleteFirst() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals(5, testList.deleteFirst(), "Failed delete first test");
		assertEquals(4, testList.deleteFirst(), "Failed delete first test");
		assertEquals(3, testList.deleteFirst(), "Failed delete first test");
		assertEquals("[2, 1]", Arrays.toString(testList.toArray()), "Failed delete first test");
	}

	@Test
	void testDeleteFirstError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		testList.insertFirst(1);
		testList.deleteFirst();
		assertThrows(NoSuchElementException.class, () -> {
			testList.deleteFirst();
		});
	}

	@Test
	void testDelete() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals(2, testList.delete(3), "Failed delete test");
		assertEquals(4, testList.delete(1), "Failed delete test");
		assertEquals("[5, 3, 1]", Arrays.toString(testList.toArray()), "Failed delete test");
	}

	@Test
	void testDeleteError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.delete(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testList.delete(5);
		});
	}

	@Test
	void testIndexOf() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals(4, testList.indexOf(1));
		assertEquals(3, testList.indexOf(2));
		assertEquals(2, testList.indexOf(3));
		assertEquals(1, testList.indexOf(4));
		assertEquals(0, testList.indexOf(5));
	}

	@Test
	void testIndexOfDuplicates() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		testList.insert(2, 2);
		assertEquals(2, testList.indexOf(2));
	}

	@Test
	void testFalseIndex() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertEquals(-1, testList.indexOf(6));
		assertEquals(-1, testList.indexOf(-2));
		assertEquals(-1, testList.indexOf(0));
	}

	@Test
	void testSize() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> testListSmall = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> testListBig = new SinglyLinkedList<>();

		for (int i = 1; i < 11; i++) {
			testList.insertFirst(i);
		}
		for (int i = 1; i < 3; i++) {
			testListSmall.insertFirst(i);
		}
		for (int i = 1; i < 101; i++) {
			testListBig.insertFirst(i);
		}
		assertEquals(10, testList.size());
		assertEquals(2, testListSmall.size());
		assertEquals(100, testListBig.size());
	}

	@Test
	void testSizeEmpty() {
		SinglyLinkedList<Integer> empty = new SinglyLinkedList<>();
		assertEquals(0, empty.size());
	}

	@Test
	void testIsEmpty() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 3; i++) {
			testList.insertFirst(i);
		}
		assertFalse(testList.isEmpty());
		testList.deleteFirst();
		testList.deleteFirst();
		assertTrue(testList.isEmpty());
	}

	@Test
	void testIsEmptyEmpty() {
		SinglyLinkedList<Integer> empty = new SinglyLinkedList<>();
		assertTrue(empty.isEmpty());
		empty.insertFirst(1);
		assertFalse(empty.isEmpty());
	}

	@Test
	void testClear() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 10; i++) {
			testList.insertFirst(i);
		}
		testList.clear();
		assertEquals("[]", Arrays.toString(testList.toArray()), "Failed clear test");
		testList.insertFirst(1);
		assertEquals("[1]", Arrays.toString(testList.toArray()), "Failed clear test");
	}

	@Test
	void testToArray() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 10; i++) {
			testList.insertFirst(i);
		}
		assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1]", Arrays.toString(testList.toArray()), "Failed array test");
	}

	@Test
	void testToArrayEmpty() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		assertEquals("[]", Arrays.toString(testList.toArray()), "Failed array test");
	}

	@Test
	void testHasNext() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		Iterator<Integer> iter = testList.iterator();
		for (int i = 1; i < 10; i++) {
			testList.insertFirst(i);
		}
		for (int i = 0; i < 9; i++) {
			assertTrue(iter.hasNext());
			iter.next();
		}
		assertFalse(iter.hasNext());
	}

	@Test
	void testHasNextEmpty() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		Iterator<Integer> iter = testList.iterator();
		assertFalse(iter.hasNext());
	}

	@Test
	void testNext() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		Iterator<Integer> iter = testList.iterator();
		for (int i = 1; i < 10; i++) {
			testList.insertFirst(i);
		}
		assertEquals(9, iter.next(), "Failed next test");
		assertEquals(8, iter.next(), "Failed next test");
		assertEquals(7, iter.next(), "Failed next test");
	}

	@Test
	void testNextError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		Iterator<Integer> iter = testList.iterator();
		assertThrows(NoSuchElementException.class, () -> {
			iter.next();
		});
	}
	
	@Test
	void testRemove() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		Iterator<Integer> iter = testList.iterator();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		assertEquals("[5, 4, 3, 1]", Arrays.toString(testList.toArray()), "Failed remove test");
	}
	
	@Test
	void testRemoveError() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		Iterator<Integer> iter = testList.iterator();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		assertThrows(IllegalStateException.class, () -> {
			iter.remove();
		});
		iter.next();
		iter.remove();
		assertThrows(IllegalStateException.class, () -> {
			iter.remove();
		});
	}

}
