package thinkDataStructures.crawler;

import java.util.StringTokenizer;

public class StringTokenizerTest {
    public static void main(String[] args) {
        String str = "a|b&c|d&e";
        StringTokenizer st = new StringTokenizer(str, "|&", true);
        while(st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}
