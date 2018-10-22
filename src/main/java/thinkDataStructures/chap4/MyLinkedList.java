package thinkDataStructures.chap4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {

	private int size;   // 요소의 개수를 추적
	private Node head;  // 첫 번째 노드에 대한 참조
	
	public MyLinkedList(){
		head = null;
		size = 0;
	}
	
	private class Node{
		public E data;
		public Node next;
		
		public Node(E data){
			this.data = data;
			this.next = null;
		}
		
		public Node(E data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		if(head == null){
			head = new Node(e);
		}else{
			Node node = head;
			while(node.next != null){
				node = node.next;
			}
			node.next = new Node(e);
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, E element) {
		if(index == 0){
			head = new Node(element, head);
		}else{
			Node node = getNode(index-1);
			node.next = new Node(element, node.next);
		}
		size++;
	}
	
	
	private Node getNode(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for(int i=0; i<index; i++){
			node = node.next;
		}
		return node;
	}

	@Override
	public E remove(int index) {
		E element = get(index);
		if(index == 0){
			head = head.next;
		}else{
			Node node = getNode(index-1);
			node.next = node.next.next;
		}
		size--;
		return element;
	}

	@Override
	public int indexOf(Object o) {
		Node node = head;
		for(int i=0; i<size; i++){
			if(equals(o, node.data)){
				return i;
			}
			node = node.next;
		}
		return -1;
	}
	
	private boolean equals(Object target, Object element){
		if(target == null){
			return element == null;
		}else{
			return target.equals(element);
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
