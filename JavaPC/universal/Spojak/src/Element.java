
public class Element <T> {
	public T value;
	public Element<T> next;
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Element<T> getNext() {
		return next;
	}
	public void setNext(Element<T> next) {
		this.next = next;
	}
	public Element(T value, Element<T> next) {
		super();
		this.value = value;
		this.next = next;
	}
	public int size() {
		if(next!=null) {
			return next.size()+1;
		}
		return 1;
	}
	
}
