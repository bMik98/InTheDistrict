package WebParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.IOException;

public class Parser {


    public Parser() throws IOException {
        Document doc = Jsoup.connect("http://eclipse.org").get();
        String title = doc.title();
        System.out.println("Title : " + title);
    }


}
