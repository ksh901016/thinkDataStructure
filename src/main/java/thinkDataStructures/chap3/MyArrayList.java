package thinkDataStructures.chap3;

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
        // TODO Auto-generated method stub
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
    	boolean flag = false;
    	for(Object obj : c){
    		flag |= remove(obj);
    	}
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
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
