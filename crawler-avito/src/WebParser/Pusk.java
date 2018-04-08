package WebParser;

import com.google.gson.Gson;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Pusk {
    public static void main(String[] args) throws InterruptedException, IOException {
        for(int i=1; i<10; i++){
        ParserStr(i);
        }
    }

    public static void ParserStr(int list) throws InterruptedException, IOException {


        Document doc =null;
        ArrayList<String> SsOb= new ArrayList<String>();
        if(list>1){

            try {
                doc = Jsoup.connect("https://www.avito.ru/rossiya/predlozheniya_uslug?p=" + list + "&q=%D0%BF%D0%BE%D0%B6%D0%B0%D1%80%D0%BD%D1%8B%D0%B5").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
        try {
             doc = Jsoup.connect("https://www.avito.ru/rossiya/predlozheniya_uslug?q=%D0%BF%D0%BE%D0%B6%D0%B0%D1%80%D0%BD%D1%8B%D0%B5").get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        String title = doc.title();
        System.out.println("Title : " + title);
        Elements joba = doc.body().getElementsByClass("js-catalog_after-ads");
        Elements jobb = doc.body().getElementsByClass("js-catalog_before-ads");
        List<Element> jobs2 = jobb.get(0).getElementsByAttributeValue("data-type","2");
        List<Element> jobs1 = jobb.get(0).getElementsByAttributeValue("data-type","1");
        List<Element> jobs3 = joba.get(0).getElementsByAttributeValue("data-type","2");
        List<Element> jobs4 = joba.get(0).getElementsByAttributeValue("data-type","1");
        SsOb.addAll(ssilki(jobs1));
        SsOb.addAll(ssilki(jobs2));
        SsOb.addAll(ssilki(jobs3));
        SsOb.addAll(ssilki(jobs4));
        //Thread.sleep(2000);
        FileWriter nFile = new FileWriter("C:\\Avito\\Advert"+list+".txt");
        int i =0;
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\yazubkov\\Downloads\\2_7\\chromedriver.exe");
       // ChromeDriver driver = new ChromeDriver();
        for (String p :SsOb
             ) {

            Advert ad = new Advert();
            try {
                Thread.sleep(2000);
                doc = Jsoup.connect(p).get();
                i++;
                Elements val = doc.getElementsByClass("seller-info-name");
                if(val.size()>0){ad.setUrl(p);}
                if(val.size()>0){ad.setName(val.text());}
                val = doc.getElementsByClass("seller-info-value");
                if(val.size()>0){ad.setDateStart(val.text());}



                //driver.get(p);
               // WebElement el = (WebElement) driver.findElementsByClassName("button item-phone-button js-item-phone-button button-origin button-origin-blue button-origin_full-width button-origin_large-extra item-phone-button_hide-phone item-phone-button_card js-item-phone-button_card");
              //  el.click();
               // val = doc.getElementsByClass("item-phone-number js-item-phone-number");

                //String SStel = val.get(0).getElementsByTag("img").attr("src");
                //ad.setNomer(getImages(SStel));
                val = doc.getElementsByClass("item-map-location");
                if(val.size()>0){ad.setAdres(val.get(0).text());}
                val = doc.getElementsByClass("item-description-text");
                if(val.size()>0){ad.setText(val.get(0).text());}


            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                Gson gson = new Gson();
                String json = gson.toJson(ad);

                nFile.write(json+"\n");
                System.out.println(i);
                System.out.println(json);
                //nFile.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        nFile.close();


    }

    public static ArrayList<String> ssilki(List<Element> jobs){
        ArrayList<String> rez =new ArrayList<String>();
        for (Element job:jobs){
            Elements ss = job.getElementsByClass("item-description-title-link");
            rez.add("https://www.avito.ru" + ss.get(0).attr("href").toString());

       }


        return rez;
    }
    private static String getImages(String src) throws IOException {

        String folder = null;
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);

        }

        indexname = src.lastIndexOf("/");

        String name = src.substring(indexname, src.length());

        System.out.println(name);

        URL url = new URL(src);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream( "C:\\Avito\\" + name));
        for (int b; (b = in.read()) != -1;) {

            out.write(b);

        }

        out.close();

        in.close();
        return name;
    }

}
