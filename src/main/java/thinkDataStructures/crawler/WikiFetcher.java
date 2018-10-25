package thinkDataStructures.crawler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiFetcher {
    private long lastRequestTime = -1;
    private long minInterval = 1000;
    
    /** 
     * Fetches and parses a URL string
     * returning a list of paragraph elements.
     * @param url
     * @throws IOException 
     */
    public Elements fetchWikipedia(String url) throws IOException {
        sleppIfNeeded();
        
        // download and parse the document
        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();
        
        // select the context text and pull out the paragraphs.
        Element content = doc.getElementById("mw-content-text");
        
        // TODO : avoid selecting paragraphs from sidebars and boxouts
        Elements paras = content.select("p");
        
        return paras;
    }
    
    /**
     * Reads the contents of a Wikipedia page from src/resources.
     * @param : url
     * @return : Elements
     * @throws IOException 
     */
    public Elements readWikipedia(String url) throws IOException {
        URL realURL = new URL(url);
        
        // assemble the file name.
        String slash = File.separator;
        String filename = "resources" + slash + realURL.getHost() + realURL.getPath();
        
        // read the file
        InputStream stream = WikiFetcher.class.getClassLoader().getResourceAsStream(filename);
        Document doc = Jsoup.parse(stream, "UTF-8", filename);
        
        // parse the contents of the file
        Element content = doc.getElementById("mw-content-text");
        Elements paras = content.select("p");
        return paras;
    }
    
    /**
     * Rate limits by waiting as least the minmum interval between requests.
     */
    private void sleppIfNeeded() {
        if(lastRequestTime != -1) {
            long currentTime = System.currentTimeMillis();
            long nextRequestTime = lastRequestTime + minInterval;
            if(currentTime < nextRequestTime) {
                try {
                    long waitingTime = nextRequestTime - currentTime;
                    System.out.println("Thread waiting : " + waitingTime);
                    Thread.sleep(waitingTime);
                } catch (InterruptedException e) {
                    System.err.println("Warning: sleep interrupted in fetchWikipedia.");
                }
            }
            
        }
        
        lastRequestTime = System.currentTimeMillis();
    }
    
    public static void main(String[] args) throws IOException {
        WikiFetcher wf = new WikiFetcher();
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paragraphs = wf.readWikipedia(url);
        
        for(Element paragraph : paragraphs) {
            System.out.println(paragraph);
        }
    }
}
