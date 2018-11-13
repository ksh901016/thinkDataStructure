package thinkDataStructures.map;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class MyTreeMapExample {

	public static void main(String[] args){
		int n = 16384;
		System.out.println("\nTesting MyTreeMap with random strings");
		putRandomStrings(n);
		
		System.out.println("\nTesting MyTreeMap with timestamps");
		putTimestamps(n);
	}
	
	private static void putRandomStrings(int n){
		MyTreeMap<String, Integer> map = new MyTreeMap<>();
		
		
		final long startTime = System.currentTimeMillis();
		for(int i=0; i<n; i++){
			String uuid = UUID.randomUUID().toString();
			map.put(uuid, 0);
		}
		final long elapsed  = System.currentTimeMillis() - startTime;
		printResults(map, elapsed, map.getHeight());
	}
	
	private static void putTimestamps(int n){
		MyTreeMap<String, Integer> map = new MyTreeMap<String, Integer>();
		
		final long startTime = System.currentTimeMillis();
		for(int i=0; i<n; i++){
			String timestamp = Long.toString(System.nanoTime());
			map.put(timestamp, 0);
		}
		final long elapsed  = System.currentTimeMillis() - startTime;
		printResults(map, elapsed, map.getHeight());
	}
	
	private static void printResults(Map<String, Integer> map, final long elapsed, final int height){
		System.out.println("    Time in milliseconds = " + (elapsed));
		System.out.println("    Final size of MyTreeMap = " + map.size());
		System.out.println("    log base 2 of size of MyTreeMap = " + Math.log(map.size()) / Math.log(2));
		System.out.println("    Final height of MyTreeMap = " + height);
	}
}
