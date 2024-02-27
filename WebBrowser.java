package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * A class simulating a web browser. Using this class, one can visit websites,
 * go forward and backward to visit previously visited sites, and print out your
 * history into an array.
 * 
 * @authors Elijah Potter & William Ngo
 * @version 2/27/2024
 */
public class WebBrowser {
	private Stack<URL> forward = new LinkedListStack<>();
	private Stack<URL> backward = new LinkedListStack<>();
	private URL current;

	/**
	 * The basic constructor of the web browser
	 */
	public WebBrowser() {

	}

	/**
	 * A constructor of web browser which imports a given history.
	 * 
	 * @param history - A singly-linked-list of URL's
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		if (!history.isEmpty()) {
			current = history.getFirst();
			for (int i = history.size() - 1; i > 0; i--) {
				backward.push(history.get(i));
			}
		}
	}

	/**
	 * Simulates visiting a website. Clears the 'forward' button and pushes the
	 * currect webpage into the 'back' button.
	 * 
	 * @param webpage - The webpage to be visited
	 */
	public void visit(URL webpage) {
		forward.clear();
		if (current != null)
			backward.push(current);
		current = webpage;
	}

	/**
	 * Simulates going back to previously visited websites. It pushes any current
	 * website onto the 'forward' button. As well, it returns the website you went
	 * back to. If there are no websites to go back to, an exception is thrown.
	 * 
	 * @return The re-visited website
	 * @throws NoSuchElementException If there are no websites to go back to
	 */
	public URL back() throws NoSuchElementException {
		if (backward.isEmpty())
			throw new NoSuchElementException("There is no previously visited URL!");
		forward.push(current);
		current = backward.pop();
		return current;
	}

	/**
	 * Simulates going forward to previously visited websites. It pushes any current
	 * website onto the 'back' button. As well, it returns the website you went
	 * forward to. If there are no websites to go forward to, an exception is
	 * thrown.
	 * 
	 * @return The re-visited website
	 * @throws NoSuchElementException If there are no websites to go forward to
	 */
	public URL forward() throws NoSuchElementException {
		if (forward.isEmpty())
			throw new NoSuchElementException("There is no URL to visit!");
		backward.push(current);
		current = forward.pop();
		return current;
	}

	/**
	 * Returns the history of the browser including the currect webpage in a singly
	 * linked list. If the webpage is instantiated but no sites are visited, an
	 * empty list is returned.
	 * 
	 * @return The history in a singly linked list
	 */
	public SinglyLinkedList<URL> history() {
		SinglyLinkedList<URL> hist = new SinglyLinkedList<>();
		if (!backward.isEmpty() && backward.peek() != null) {
			int limit = backward.size();
			for (int i = 0; i < limit; i++) {
				hist.insert(i, backward.pop());
			}
			for (int i = hist.size() - 1; i >= 0; i--) {
				backward.push(hist.get(i));
			}
			hist.insertFirst(current);
			return hist;
		} else if (current != null) {
			hist.insertFirst(current);
			return hist;
		} else
			return hist;
	}
}
