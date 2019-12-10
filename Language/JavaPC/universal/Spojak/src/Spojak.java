import java.util.Iterator;

public class Spojak <T> implements Iterable<Element>{
	public Element<T> prvniElement;

	public Spojak(Element<T> prvniElement) {
		super();
		this.prvniElement = prvniElement;
	}

	public Element<T> getPrvniElement() {
		return prvniElement;
	}

	public void setPrvniElement(Element<T> prvniElement) {
		this.prvniElement = prvniElement;
	}
	
	public void addFirst(T value) {
		prvniElement=new Element<T>(value, prvniElement);
	}
	public int size() {
		return prvniElement.size();
	}

	@Override
	public Iterator<Element> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator(prvniElement);
	}
}
