package thinkDataStructures.map;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

public class MyTreeMap<K,V> implements Map<K,V>{
	
	private int size = 0;
	private Node root = null;
	
	protected class Node{
		public K key;
		public V value;
		public Node left = null;
		public Node right = null;
		
		public Node(K key, V value){
			this.key = key;
			this.value = value;
		}
	}
	
	private Node findNode(Object target){
		if(target == null){
			throw new IllegalArgumentException();
		}
		
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>) target;
		
		// 탐색
		Node node = root;
		while(node != null){
			int cmp = k.compareTo(node.key);
			if(cmp < 0){
				node = node.left;
			}else if(cmp > 0){
				node = node.right;
			}else{
				return node;
			}
		}
		
		return null;
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
	public boolean containsKey(Object key) {
		return findNode(key) != null;
	}

	@Override
	public boolean containsValue(Object value) {
		return containsValueHelper(root, value);
	}
	
	private boolean containsValueHelper(Node node, Object value) {
	    if(node == null) {
	        return false;
	    }
	    if(equals(value, node.value)) {
	        return true;
	    }
	    if(containsValueHelper(node.left, value)) {
	        return true;
	    }
	    if(containsValueHelper(node.right, value)) {
	        return true;
	    }
	    return false;
	}
	
	private boolean equals(Object target, Object obj) {
	    if(target == null) {
	        return obj == null;
	    }
	    return target.equals(obj);
	}

	@Override
	public V get(Object key) {
		Node node = findNode(key);
		return node == null ? null : node.value;
	}

	@Override
	public V put(K key, V value) {
		if(key == null){
			throw new IllegalArgumentException();
		}
		
		if(root == null){
			root = new Node(key, value);
			size++;
			return null;
		}
		
		return putHelper(root, key, value);
	}
	
	private V putHelper(Node node, K key, V value){
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>) key;
		
		int cmp = k.compareTo(node.key);
		if(cmp < 0){
			if(node.left == null){
				node.left = new Node(key, value);
				size++;
				return null;
			}else{
				return putHelper(node.left, key, value);
			}
		}
		
		if(cmp > 0){
			if(node.right == null){
				node.right = new Node(key, value);
				size++;
				return null;
			}else{
				return putHelper(node.right, key, value);
			}
		}
		
		V oldValue = node.value;
		node.value = value;
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		// TODO 구현해보기
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for(Entry<? extends K, ? extends V> entry : map.entrySet()) {
		    put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new LinkedHashSet<K>();
		inorder(root, set);
		return set;
	}
	
	// 중위순회 
	public void inorder(Node root, Set<K> set){
	    if(root != null) {
    	    inorder(root.left, set);
    	    set.add(root.key);
    	    inorder(root.right, set);
	    }
	}

	@Override
	public Collection<V> values() {
		return getValuesByDFS();
	}
	
	// 깊이우선탐색
	private Set<V> getValuesByDFS(){
		Set<V> set = new LinkedHashSet<>();
		
		Deque<Node> stack = new LinkedList<>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if(node == null) continue;
			set.add(node.value);
			stack.push(node.left);
			stack.push(node.right);
		}
		return set;
	}
	
	// 넓이우선탐색
	private Set<V> getValuesByBFS(){
		Set<V> set=  new LinkedHashSet<>();
		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			Node node = queue.poll();
			if(node == null) continue; // TODO Break 문도 잘 돌아가는지 확인
			set.add(node.value);
			queue.offer(root.left);
			queue.offer(root.right);
		}
		return set;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
	    throw new UnsupportedOperationException();
	}
	
	public int getHeight(){
		return heightHelper(root);
	}
	
	private int heightHelper(Node node){
		if(node == null){
			return 0;
		}
		int left = heightHelper(node.left);
		int right = heightHelper(node.right);
		return Math.max(left, right) + 1;
	}
	
	// TODO 반복문으로 height를 얻는 코드를 구현해 보기
}
