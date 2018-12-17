package thinkDataStructures.crawler;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiParser {

    private Elements paragraphs;
    private Deque<String> parenthesisStack;
    
    public WikiParser(Elements p) {
        this.paragraphs = p;
        parenthesisStack = new ArrayDeque<String>();
    }
    
    public Element findFirstLink() {
        for(Element paragraph : paragraphs) {
            Element firstLink = findFirstLinkPara(paragraph);
            if(firstLink != null) {
                return firstLink;
            }
            if(!parenthesisStack.isEmpty()){
            	System.err.println("Warning: unbalanced parentheses."); 
            }
        }
        
        return null;
    }
    
    private Element findFirstLinkPara(Node root) {
        Iterable<Node> iter = new WikiNodeIterable(root);
        for(Node node : iter) {
            if(node instanceof TextNode) {
                processTextNode((TextNode) node);
            }
            
            if(node instanceof Element) {
                Element firstLink = processElement((Element) node);
                if(firstLink != null){
                	return firstLink;
                }
            }
        }
        return null;
    }
    
    private void processTextNode(TextNode node) {
        StringTokenizer st = new StringTokenizer(node.text(), " ()", true); // include delimiter
        while(st.hasMoreTokens()){
        	String token = st.nextToken();
        	if(token.equals("(")){
        		parenthesisStack.push(token);
        	}
        	if(token.equals(")")){
        		if(parenthesisStack.isEmpty()){
        			System.err.println("unbalnced parentheses.");
        		}
        		parenthesisStack.pop();
        	}
        }
    }
    
    private Element processElement(Element element) {
    	if(validLink(element)){
    		return element;
    	}
        return null;
    }
    
    private boolean validLink(Element element){
    	// a 태그존재 유무
    	if(!element.tagName().equals("a")){
    		return false;
    	}
    	// italic체 유무
    	if(isItalic(element)){
    		return false;
    	}
    	// parenthesis 괄호 존재 유무
    	if(isInParens(element)){
    		return false;
    	}
    	// bookmark
    	if(startsWith(element, "#")){
    		return false;
    	}
    	// wikipedia help page
    	if(startsWith(element, "/wiki/Help:")){
    		return false;
    	}
    	
    	return true;
    }
    
    // element가 italic체 인지 검사 
    private boolean isItalic(Element element){
    	while(element != null){
    		if(element.tagName().equals("i") || element.tagName().equals("em")){
    			return true;
    		}
    		element = element.parent();
    	}
    	return false;
    }
    
    private boolean isInParens(Element element){
    	return !parenthesisStack.isEmpty();
    }
    
    private boolean startsWith(Element element, String s){
    	return element.attr("href").startsWith(s);
    }
}
