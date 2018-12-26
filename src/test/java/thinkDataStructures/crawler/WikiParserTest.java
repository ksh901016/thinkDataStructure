package thinkDataStructures.crawler;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

public class WikiParserTest {

	final static WikiFetcher wf = new WikiFetcher();

	@Test
	@Ignore
	public void isItalicTest() {
		Element italic = new Element(Tag.valueOf("i"), "");
		italic.appendText("italic");
		
		Element emphasize = new Element(Tag.valueOf("em"), "");
		emphasize.appendText("emphasize");
		
		assertEquals(true, isItalic(italic));
		assertEquals(true, isItalic(emphasize));
	}
	
	@Test
	@Ignore
	public void startsWithTest(){
		Element aTag = new Element(Tag.valueOf("a"), "");
		aTag.attr("href", "#test");
		
		assertEquals(true, startsWith(aTag, "#"));
	}
	
	@Test
	public void getFirstLink() throws IOException{
		String url="https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.fetchWikipedia(url);
		
		WikiParser wp = new WikiParser(paragraphs);
		Element ele = wp.findFirstLink();
		String href = ele.attr("href");
		System.out.println("href : " + href);
		System.out.println(ele.attr("abs:href"));
		assertEquals("/wiki/Programming_language", href);
	}
	
	
	private boolean isItalic(Element element){
    	while(element != null){
    		if(element.tagName().equals("i") || element.tagName().equals("em")){
    			return true;
    		}
    		element = element.parent();
    	}
    	return false;
    }
    
    private boolean startsWith(Element element, String s){
    	return element.attr("href").startsWith(s);
    }

}
