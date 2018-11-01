package thinkDataStructures.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


public class TermCounter {
	private Map<String, Integer> map;
	private String label;
	
	public TermCounter(String label){
		this.label = label;
		this.map = new HashMap<>();
	}
	
	public void put(String term, int count){
		map.put(term, count);
	}
	
	public Integer get(String term){
		Integer counter = map.get(term);
		return counter == null ? 0 : counter;
	}
	
	public void incrementTermCount(String term){
		put(term, get(term) + 1);
	}
	
	/** 웹 페이지를 인덱싱하는 보조 메서드 들 **/
	
	public void processElements(Elements paragraphs){
		for(Node node : paragraphs){
			processTree(node);
		}
	}
	
	public void processTree(Node root){
		for(Node node : new WikiNodeIterable(root)){
			if(node instanceof TextNode){
				processText(((TextNode)node).text());
			}
		}
	}
	
	public void processText(String text){
		System.out.println("Before text : " + text);
		String[] array = text.replaceAll("\\pP", " ").toLowerCase().split("\\s+");
		System.out.println("==== arrayStr Start ====");
		for(String str : array){
			System.out.print(str);
		}
		System.out.println("==== arrayStr End ====");
		
		for(int i=0; i<array.length; i++){
			String term = array[i];
			incrementTermCount(term);
		}
	}
	
	public Set<String> keySet(){
		return map.keySet();
	}
	
	public void printCounts(){
		for(String key : keySet()){
			Integer count = get(key);
			System.out.println(key + " , " + count);
		}
		System.out.println("Total of all counts = " + size());
	}
	
	public int size(){
		return keySet().size();
	}
	
	public static void main(String[] args) throws IOException{
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		WikiFetcher wf = new WikiFetcher();
		Elements paragraphs = wf.fetchWikipedia(url);
		
		TermCounter tc = new TermCounter(url);
		tc.processElements(paragraphs);
		tc.printCounts();
	}
}
