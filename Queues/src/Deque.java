import java.util.Iterator;
import java.util.NoSuchElementException;;

public class Deque<Item> implements Iterable<Item> {

  private int size;
  private Node<Item> first;
  private Node<Item> last;

  private class Node<Item> {
    private Item item;
    private Node<Item> next;
    private Node<Item> previous;
  }

  public Deque() {
    first = null;
    last = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    Node<Item> oldFirst = first;
    first = new Node<Item>();
    first.item = item;
    first.previous = null;
    if (isEmpty()) {
      first.next = null;
      last = first;
    } else {
      oldFirst.previous = first;
      first.next = oldFirst;
    }
    size++;
  }
  
  public void addLast(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    Node<Item> oldLast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty()) {
      last.previous = null;
      first = last;
    } 
    else {
      last.previous = oldLast;
      oldLast.next = last;
    }
    size++;
  }
  
  public Item removeFirst() {
    Item item;
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    item = first.item;
    if (size == 1) {
      first = null;
      last = null;
    }
    else {
      first = first.next;
      first.previous = null;
    }
    size--;
    return item;
  }
  
  public Item removeLast() {
    Item item;
    if (isEmpty()) {
      throw new NoSuchElementException();      
    }
    item = last.item;
    if (size == 1) {
      last = null;
      first = null;
    }
    else {
      last = last.previous;
      last.next = null;
    }
    size--;
    return item;
  }
  
  private class ListIterator implements Iterator<Item> {

    private Node<Item> current = first;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (current == null) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  
 /* public static void main(String[] args) {
    
  }*/
}
