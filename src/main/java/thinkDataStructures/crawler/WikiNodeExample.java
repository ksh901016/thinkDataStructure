package thinkDataStructures.crawler;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiNodeExample {
	public static void main(String[] args) throws IOException{
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		
		// download and parse the document
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		// select the content text and pull out the paragraphs.
		Element content = doc.getElementById("mw-content-text");
		Elements paragraphs = content.select("p");
		Element firstPara = paragraphs.get(1);
		System.out.println(firstPara.toString());

		//recursiveDFS(firstPara);
		System.out.println();
		
		Iterable<Node> iter = new WikiNodeIterable(firstPara);
		for (Node node: iter) {
			if (node instanceof TextNode) {
				System.out.println("TextNode ============" + node);
			}
			if(node instanceof Element){
				System.out.println("Element ============" + node);
			}
		}
	}
	
	// 재귀 DFS
	private static void recursiveDFS(Node node){
		if(node instanceof TextNode){
			System.out.print(node);
		}
		for(Node child : node.childNodes()){
			recursiveDFS(child);
		}
	}
	
	// 반복적 DFS
	private static void iterativeDFS(Node root){
		Deque<Node> stack = new ArrayDeque<>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if(node instanceof TextNode){
				System.out.print(node);
			}
			
			List<Node> nodes = new ArrayList<>(node.childNodes());
			Collections.reverse(nodes); // 스택은 후입선출이기 때문 
			
			for(Node child : nodes){
				stack.push(child);
			}
		}
	}
}
