package thinkDataStructures.crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPhilosophy {
	
	final static ArrayList<String> visited = new ArrayList<>(); // 방문한  url 표시
	final static WikiFetcher wf = new WikiFetcher();
	
	 /**
	  * 각 페이지에서 첫번째 link <a태그>를 찾아서 그 link로 이동한다.
	  * source 는 시작페이지이며,
	  * destination은 최종도착 페이지이다. 
	 */
	public static void main(String[] args) throws IOException {
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        String destination = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        
        moveFirstLink(source, destination, 10);
    }
	
	
	/**
	 * source에서 limit까지 이동하여, destination과 맞는지 확인 후 결과를 print 해준다.
	 * @param source
	 * @param destination
	 * @param limit
	 * @throws IOException 
	 */
	public static void moveFirstLink(String source, String destination, int limit) throws IOException{
		String url = source;
		for(int i=0; i<limit; i++){
			if(visited.contains(url)){
				System.out.println("무한 루프 발생. 이미 방문한 페이지입니다.");
				return;
			}else{
				visited.add(url);
			}
			
			Element element = getFirstValidLink(url);
			if(element == null){
				System.out.println("유효한 링크가 존재하지 않습니다.");
				return;
			}
			
			System.out.println("** " + element.text() + " **");
			url = element.attr("abs:href"); // 전체경로(API 참고)
			
			if(url.equals(destination)){
				System.out.println("발견!");
				break;
			}
		}
	}
	
	/**
	 * url로 부터 첫번째 유효한 link(a 태그)를 얻어온다.
	 * @param url
	 * @return Element (html Element 객체)
	 * @throws IOException 
	 */
	public static Element getFirstValidLink(String url) throws IOException{
		System.out.println("Fetching ....." + url);
		Elements paragraphs = wf.fetchWikipedia(url);
		WikiParser parser = new WikiParser(paragraphs);
		Element element = parser.findFirstLink();
		return element;
	}
    
}
