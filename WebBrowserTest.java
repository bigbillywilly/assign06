package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebBrowserTest {
	SinglyLinkedList<URL> history = new SinglyLinkedList<>();
	SinglyLinkedList<URL> deletedHistory = new SinglyLinkedList<>();
	LinkedListStack<Integer> bruh = new LinkedListStack<>();
	URL youtube, nebula, github, steam, spotify, bingus, apple, radiohead;

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
		WebBrowser chrome = new WebBrowser(deletedHistory);
		assertEquals("[]", Arrays.toString(chrome.history().toArray()), "Failed constructor test");
	}

	@Test
	void testVisit() {
		WebBrowser chrome = new WebBrowser();
		chrome.visit(radiohead);
		chrome.visit(steam);
		chrome.visit(bingus);
		assertEquals("[http://bingus.com/, http://steam.com/, http://radiohead.com/, null]",
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

}
