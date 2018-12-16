package thinkDataStructures.crawler;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class WikiPhilosophyTest {

    WikiFetcher fetcher;
    String url;
    Element secondParas;
    
    @Before
    public void setup() throws IOException {
        fetcher = new WikiFetcher();
        url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paras = fetcher.fetchWikipedia(url);
        secondParas = paras.get(1);
    }

    @Test
    public void printSecondPara() {
        System.out.println(secondParas);
        Iterable<Node> iter = new WikiNodeIterable(secondParas);
        for(Node node : iter) {
            System.out.println(node);
            /*if(node instanceof TextNode) {
                System.out.println("textNode : " + node);
            }
            if(node instanceof Element) {
                System.out.println("element  : " + node);
            }*/
        }
    }
    

}
