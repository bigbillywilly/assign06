package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * A class simulating a webbrowser
 */
public class WebBrowser {
	private Stack<URL> forward = new LinkedListStack<>();
	private Stack<URL> backward = new LinkedListStack<>();
	private URL current;

	public WebBrowser() {

	}

	public WebBrowser(SinglyLinkedList<URL> history) {
		if (!history.isEmpty()) {
			current = history.getFirst();
			for (int i = history.size() - 1; i > 0; i--) {
				backward.push(history.get(i));
			}
		}
	}

	public void visit(URL webpage) {
		forward.clear();
		if (current != null)
			backward.push(current);
		current = webpage;
	}

	public URL back() throws NoSuchElementException {
		if (backward.isEmpty())
			throw new NoSuchElementException("There is no previously visited URL!");
		forward.push(current);
		current = backward.pop();
		return current;
	}

	public URL forward() throws NoSuchElementException {
		if (forward.isEmpty())
			throw new NoSuchElementException("There is no URL to visit!");
		backward.push(current);
		current = forward.pop();
		return current;
	}

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
