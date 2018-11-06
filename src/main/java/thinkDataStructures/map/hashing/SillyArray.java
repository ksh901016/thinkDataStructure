package thinkDataStructures.map.hashing;

import java.util.Arrays;

public class SillyArray {
	private final char[] array;
	
	public SillyArray(char[] array){
		this.array = array;
	}
	
	public String toString(){
		return Arrays.toString(array);
	}
	
	@Override
	public boolean equals(Object other){
		return this.toString().equals(other.toString());
	}
	
	@Override
	public int hashCode(){
		int total = 0;
		for(int i=0; i<array.length; i++){
			total += array[i];
		}
		System.out.println(total);
		return total;
	}
	
	public void setChar(int i, char c){
		this.array[i] = c;
	}
}
