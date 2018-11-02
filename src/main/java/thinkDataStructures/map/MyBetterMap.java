package thinkDataStructures.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyBetterMap<K, V> implements Map<K, V> {
	
	protected List<MyLinearMap<K, V>> maps;
	
	MyBetterMap(int k){
		makeMaps(k);
	}
	
	public MyBetterMap() {
		makeMaps(2);
	}

	protected void makeMaps(int k){
		maps = new ArrayList<>(k);
		for(int i=0; i<k; i++){
			maps.add(new MyLinearMap<>());
		}
	}
	
	protected MyLinearMap<K, V> chooseMap(Object key){
		int index = 0;
		if(key != null){
			Math.abs(key.hashCode());
		}
		return maps.get(index);
	}

	@Override
	public int size() {
		int size = 0;
		for(MyLinearMap<K,V> map : maps){
			size += map.size();
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return maps.size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		MyLinearMap<K,V> map = chooseMap(key);
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		for(MyLinearMap<K,V> map : maps){
			if(map.containsValue(value)){
				return true;
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		MyLinearMap<K, V> map = chooseMap(key);
		return map.get(key);
	}

	@Override
	public V put(K key, V value) {
		MyLinearMap<K, V> map = chooseMap(key);
		return map.put(key, value);
	}

	@Override
	public V remove(Object key) {
		MyLinearMap<K,V> map = chooseMap(key);
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for(Map.Entry<? extends K, ? extends V> entry : map.entrySet()){
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		// clear the sub-maps
		for(int i=0; i<maps.size(); i++){
			maps.get(i).clear();
		}
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		for(MyLinearMap<K, V> map : maps){
			set.addAll(map.keySet());
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<>();
		for(MyLinearMap<K, V> map : maps){
			set.addAll(map.values());
		}
		return set;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<>();
		for(MyLinearMap<K,V> map : maps){
			set.addAll(map.entrySet());
		}
		return set;
	}
	
	public static void main(String[] args) {
		Map<String, Integer> map = new MyBetterMap<String, Integer>();
		map.put("Word1", 1);
		map.put("Word2", 2);
		Integer value = map.get("Word1");
		System.out.println(value);

		for (String key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}
}
