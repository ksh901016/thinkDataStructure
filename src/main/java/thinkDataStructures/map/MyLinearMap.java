package thinkDataStructures.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyLinearMap<K, V> implements Map<K, V> {
	
	private List<Entry> entries = new ArrayList<>();
	
	public class Entry implements Map.Entry<K, V>{
		private K key;
		private V value;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
		@Override
		public K getKey() {
			return this.key;
		}
		@Override
		public V getValue() {
			return this.value;
		}
		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public boolean containsKey(Object target) {
		return findEntry(target) != null;
	}
	
	private Entry findEntry(Object target){
		// TODO
		return null;
	}

	@Override
	public boolean containsValue(Object target) {
		for(Map.Entry<K, V> entry : entries){
			if(equals(target, entry.getValue())){
				return true;
			}
		}
			
		return false;
	}
	
	private boolean equals(Object target, Object obj){
		if(target == null){
			return obj == null;
		}
		return target.equals(obj);
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for(Entry entry : entries){
			set.add(entry.getKey());
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for(Map.Entry<? extends K, ? extends V> entry : map.entrySet()){
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<V>();
		for(Entry entry : entries){
			set.add(entry.getValue());
		}
		return set;
	}

}
