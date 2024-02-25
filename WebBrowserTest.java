package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing test cases for the WebBrowser class
 */
class WebBrowserTest {
	SinglyLinkedList<URL> history = new SinglyLinkedList<>();
	SinglyLinkedList<URL> historySmall = new SinglyLinkedList<>();
	LinkedListStack<Integer> bruh = new LinkedListStack<>();
	URL youtube, nebula, github, steam, spotify, bingus, apple, radiohead;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		youtube = new URL("http://youtube.com/");
		nebula = new URL("http://nebula.com/");
		github = new URL("http://github.com/");
		steam = new URL("http://steam.com/");
		spotify = new URL("http://spotify.com/");
		bingus = new URL("http://bingus.com/");
		apple = new URL("http://apple.com/");
		radiohead = new URL("http://radiohead.com/");

		history.insertFirst(youtube); // The last website visited
		history.insertFirst(nebula);
		history.insertFirst(github);
		history.insertFirst(steam);
		history.insertFirst(spotify); // The first website visited

		historySmall.insertFirst(radiohead); // The last website visited
		historySmall.insertFirst(youtube);
		historySmall.insertFirst(spotify); // The first website visited
	}

	@Test
	void testConstructor() {
		WebBrowser chrome = new WebBrowser(history);
		assertEquals(
				"[http://spotify.com/, http://steam.com/, http://github.com/, http://nebula.com/, http://youtube.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testConstructorEmpty() {
		WebBrowser chrome = new WebBrowser();
		assertEquals("[]", Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testConstructorBackToFirst() {
		WebBrowser chrome = new WebBrowser();
		chrome.visit(github);
		chrome.visit(steam);
		chrome.back();
		assertEquals("[http://github.com/]", Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testConstructorDupes() {
		WebBrowser chrome = new WebBrowser();
		chrome.visit(youtube);
		chrome.visit(youtube);
		chrome.visit(youtube);
		chrome.visit(youtube);
		chrome.visit(youtube);
		assertEquals(
				"[http://youtube.com/, http://youtube.com/, http://youtube.com/, http://youtube.com/, http://youtube.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testFilledConstructorBackToFirstURL() {
		WebBrowser chrome = new WebBrowser(history);
		chrome.back();
		chrome.back();
		chrome.forward();
		chrome.back();
		chrome.back();
		chrome.back();
		assertEquals("[http://youtube.com/]", Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testVisit() {
		WebBrowser chrome = new WebBrowser();
		chrome.visit(radiohead);
		chrome.visit(steam);
		chrome.visit(bingus);
		chrome.back();
		chrome.forward();
		assertEquals("[http://bingus.com/, http://steam.com/, http://radiohead.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testVisitWithHistory() {
		WebBrowser chrome = new WebBrowser(history);
		chrome.visit(radiohead);
		chrome.visit(steam);
		chrome.visit(bingus);
		assertEquals(
				"[http://bingus.com/, http://steam.com/, http://radiohead.com/, http://spotify.com/, http://steam.com/, http://github.com/, http://nebula.com/, http://youtube.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testVisitClearsForward() {
		WebBrowser chrome = new WebBrowser(historySmall);
		chrome.back();
		chrome.back();
		chrome.visit(nebula);
		assertThrows(NoSuchElementException.class, () -> {
			chrome.forward();
		});
	}

	@Test
	void testForward() {
		WebBrowser chrome = new WebBrowser(history);
		chrome.back();
		chrome.back();
		chrome.back();
		chrome.back();
		chrome.forward();
		chrome.forward();
		chrome.forward();
		chrome.forward();
		assertEquals(
				"[http://spotify.com/, http://steam.com/, http://github.com/, http://nebula.com/, http://youtube.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed forward test");
	}

	@Test
	void testForwardReturn() {
		WebBrowser chrome = new WebBrowser(historySmall);
		chrome.back();
		chrome.back();
		assertEquals(youtube.toString(), chrome.forward().toString(), "Failed forward test");
		assertEquals(spotify.toString(), chrome.forward().toString(), "Failed forward test");
	}

	@Test
	void testForwardFail() {
		WebBrowser chrome = new WebBrowser(historySmall);
		chrome.back();
		chrome.forward();
		assertThrows(NoSuchElementException.class, () -> {
			chrome.forward();
		});
	}

	@Test
	void testBack() {
		WebBrowser chrome = new WebBrowser(history);
		chrome.back();
		chrome.back();
		chrome.back();
		assertEquals("[http://nebula.com/, http://youtube.com/]", Arrays.toString(chrome.history().toArray()),
				"Failed back test");
	}

	@Test
	void testBackWithVisit() {
		WebBrowser chrome = new WebBrowser(history);
		chrome.back();
		chrome.back();
		chrome.back();
		chrome.visit(apple);
		assertEquals("[http://apple.com/, http://nebula.com/, http://youtube.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed back test");
	}

	@Test
	void testBackWithVisitAndForward() {
		WebBrowser chrome = new WebBrowser(history);
		chrome.back();
		chrome.back();
		chrome.forward();
		chrome.back();
		chrome.visit(apple);
		chrome.back();
		chrome.forward();
		chrome.back();
		assertEquals("[http://github.com/, http://nebula.com/, http://youtube.com/]",
				Arrays.toString(chrome.history().toArray()), "Failed back test");
	}

	@Test
	void testBackReduceToOne() {
		WebBrowser chrome = new WebBrowser();
		chrome.visit(github);
		chrome.visit(steam);
		chrome.back();
		assertEquals("[http://github.com/]", Arrays.toString(chrome.history().toArray()), "Failed back test");
	}
	
	@Test
	void testBackFail() {
		WebBrowser chrome = new WebBrowser(historySmall);
		chrome.back();
		chrome.back();
		assertThrows(NoSuchElementException.class, () -> {
			chrome.back();
		});	}

	@Test
	void testHistoryAffectsBack() {
		WebBrowser chrome = new WebBrowser(history);
		Object[] arr1 = new Object[5];
		Object[] arr2 = new Object[5];
		Object[] arr3 = new Object[5];
		arr1 = chrome.history().toArray();
		arr2 = chrome.history().toArray();
		arr3 = chrome.history().toArray();
		assertEquals(Arrays.toString(arr1), Arrays.toString(arr2), "Failed history test");
		assertEquals(Arrays.toString(arr1), Arrays.toString(arr3), "Failed history test");
		assertEquals(Arrays.toString(arr2), Arrays.toString(arr3), "Failed history test");
	}
	
	@Test
	void testHistoryAffectsBackAndMore() {
		WebBrowser chrome = new WebBrowser(history);
		Object[] arr1 = new Object[5];
		arr1 = chrome.history().toArray();
		chrome.back();
		chrome.back();
		chrome.forward();
		chrome.forward();
		chrome.visit(bingus);
		chrome.back();
		assertEquals(Arrays.toString(arr1), Arrays.toString(chrome.history().toArray()), "Failed history test");
	}

}
