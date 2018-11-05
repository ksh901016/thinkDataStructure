package thinkDataStructures.map;

import java.util.List;
import java.util.Map;

public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K,V>{

	// 재해쉬하기 전 하위 맵당 평균 엔트리 개수
	private static final double FACTOR = 1.0;
	
	@Override
	public V put(K key, V value){
		V oldValue = super.put(key, value);
		
		if(size() > maps.size() * FACTOR){
			rehash();
		}
		return oldValue;
	}
	
	public void rehash(){
		List<MyLinearMap<K,V>> oldMaps = maps;
		
		// make more maps
		makeMaps(maps.size() * 2);
		
		for(MyLinearMap<K,V> map : oldMaps){
			for(Map.Entry<K, V> entry : map.getEntires()){
				put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public static void main(String[] args){
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for(int i=0; i<10; i++){
			map.put(new Integer(i).toString(), i);
		}
		
		Integer value = map.get("3");
		System.out.println(value);
	}
}
