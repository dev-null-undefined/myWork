import java.util.Iterator;

public class ListIterator implements Iterator<Element> {

    private Element current;

    public ListIterator(Element first) {
        current = first;
    }
	@Override
	public boolean hasNext() {
		return current!=null;
	}

	@Override
	public Element next() {
		Element tempo = current;
        current = current.getNext();
        return tempo;
	}

}
