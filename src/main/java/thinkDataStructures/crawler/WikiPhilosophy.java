package thinkDataStructures.crawler;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class WikiPhilosophy {
    public static void main(String[] args) throws IOException {
        WikiFetcher fetcher = new WikiFetcher();
        
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        
        Elements paras = fetcher.fetchWikipedia(source);
        Element firstData = paras.get(1);
        
        // DFS로 탐색해야함 DOM트리 구조를 읽어야하기 때문에 
        
    }
    
    public static Element searchFirstLink(Element root) {
        Deque<Element> stack = new ArrayDeque<>();
        stack.push(root);
        
        while(!stack.isEmpty()) {
            Element element = stack.pop();
            System.out.println("tagName : " + element.tagName());
            if("a".equals(element.tagName())) {
                return element;
            }
            
            List<Node> nodeList = element.childNodes();
            Collections.reverse(nodeList);
            //Node Element..?
        }
        
        return null;
    }
}
