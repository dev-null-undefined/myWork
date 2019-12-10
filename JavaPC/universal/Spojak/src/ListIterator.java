import java.util.Iterator;

public class ListIterator implements Iterator<Element> {

    private Element current;
    
    public ListIterator(Element first) {
        current = first;
    }
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return current!=null;
	}

	@Override
	public Element next() {
		// TODO Auto-generated method stub
		Element tempo = current;
        current = current.getNext();
        return tempo;
	}

}
