package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * A class containing test cases for the SinglyLinkedList class
 * 
 * @authors Elijah Potter & William Ngo
 * @version 2/22/2024
 */
class SinglyLinkedListTest {

	@Test
	void testBasic() {
		SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 1; i < 6; i++) {
			testList.insertFirst(i);
		}
		System.out.println(Arrays.toString(testList.toArray()));
		testList.insert(5, 99);
		testList.insert(2, 4);
		System.out.println(Arrays.toString(testList.toArray()));
	}

}
