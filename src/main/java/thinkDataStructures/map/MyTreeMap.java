package thinkDataStructures.map;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
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
		
		Comparable<? super K> k = (Comparable<? super K>) target;
		
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
