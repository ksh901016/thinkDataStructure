package thinkDataStructures.linkedList;

import java.util.Arrays;
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
		
		public String toString(){
			return "Node (" + data.toString() + ")";
		}
	}
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public Iterator<E> iterator() {
		@SuppressWarnings("unchecked")
		E[] array = (E[]) toArray();
		return Arrays.asList(array).iterator();
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i=0;
		for(Node node = head; node != null; node = node.next){
			array[i] = node.data;
			i++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
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
		int index = indexOf(o);
		if(index == -1){
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object obj : c){
			if(contains(obj)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean flag = true;
		for(E element : c){
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean flag = true;
		for(Object obj : c){
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return node.data;
	}

	@Override
	public E set(int index, E element) {
		Node node = getNode(index);
		E old = node.data;
		node.data = element;
		return old;
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
		Node node = head;
		int index = -1;
		for(int i=0; i<size; i++){
			if(equals(o, node.data)){
				index = i;
			}
			node = node.next;
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if(fromIndex < 0 || toIndex >= size || fromIndex > toIndex){
			throw new IndexOutOfBoundsException();
		}
		
		MyLinkedList<E> list = new MyLinkedList<>();
		int i = 0;
		for(Node node = head; node != null ; node = node.next){
			if(i >=fromIndex && i <= toIndex){
				list.add(node.data);
			}
			i++;
		}
		return list;
	}

}
