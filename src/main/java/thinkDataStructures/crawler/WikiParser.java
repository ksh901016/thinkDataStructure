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
            
        }
        
        return null;
    }
    
    private Element findFirstLinkPara(Node root) {
        Iterable<Node> iter = new WikiNodeIterable(root);
        for(Node node : iter) {
            if(node instanceof TextNode) {
                processTextNode((TextNode)node);
            }
            
            if(node instanceof Element) {
                processElement(node);
            }
        }
        return null;
    }
    
    private void processTextNode(TextNode node) {
        StringTokenizer st = new StringTokenizer(node.text(), " ()", true); // include delimiter
    }
    
    private Element processElement(Node node) {
        return null;
    }
}
