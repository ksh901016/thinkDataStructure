package thinkDataStructures.chap3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {
    int size; // 요소의 개수 
    private E[] array; // 요소를 저장 
    
    public MyArrayList() {
        // JAVA는 타입 파라미터로 배열을 초기화 할 수 없기 때문에 type casting을 해야 한다 
        array = (E[]) new Object[10];
        size = 0;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        if(size >= array.length) {
            E[] bigger = (E[]) new Object[array.length * 2];
            System.arraycopy(array, 0, bigger, 0, array.length);
            array = bigger;
        }
        array[size] = e;
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
    	for(Object o : c){
    		if(!contains(o)){
    			return false;
    		}
    	}
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
    	boolean flag = true;
    	for(E o : c){
    		flag &= add(o);
    	}
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
    	boolean flag = false;
    	for(Object obj : c){
    		flag |= remove(obj);
    	}
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public E set(int index, E element) {
    	E old = get(index);
    	array[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
    	if(index <0 || index > size){
    		throw new IndexOutOfBoundsException();
    	}
    	// 크기 조정을 통해 요소 추가
        add(element);
        // 다른 요소를 시프트
        for(int i = size-1; i>index; i--){
        	array[i] = array[i-1];
        }
        // 올바른 자리에 새로운 값을 넣습니다.
        array[index] = element;
    }

    @Override
    public E remove(int index) {
    	E element = get(index);
    	for(int i = index; i<size-1; i++){
    		array[i] = array[i+1];
    	}
    	size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
    	for(int i=0; i<size; i++){
    		if(equals(o, array[i])){
    			return i;
    		}
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
    	for(int i=size-1; i>=0; i--){
    		if(equals(o, array[i])){
    			return i;
    		}
    	}
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
    	// make a copy of the array
    	E[] copy = Arrays.copyOf(array, size);
    	// make a list and return an iterator
        return Arrays.asList(copy).listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
    	E[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
    	if(fromIndex < 0 || toIndex >= size || fromIndex > toIndex){
    		throw new IndexOutOfBoundsException();
    	}
    	E[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
        return Arrays.asList(copy);
    }

}
