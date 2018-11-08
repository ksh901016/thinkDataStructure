package thinkDataStructures.map;

import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
		// TODO Auto-generated method stub
		return false;
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
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new LinkedHashSet<K>();
		
		return set;
	}

	@Override
	public Collection<V> values() {
		return dfs();
	}
	
	// 깊이우선탐색
	private Set<V> dfs(){
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
	private Set<V> bfs(){
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
		// TODO Auto-generated method stub
		return null;
	}

}
