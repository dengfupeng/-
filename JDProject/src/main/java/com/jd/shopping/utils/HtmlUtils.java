package com.jd.shopping.utils;

import com.jd.shopping.entity.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlUtils {
    public static void main(String[] args) throws IOException {
        new HtmlUtils().parseJD("电脑").forEach(System.out::println);

    }
    public List<Content> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyword + "&enc=utf-8&wq="+keyword;
        Document parse = Jsoup.parse(new URL(url),3000);
        Element goodsList = parse.getElementById("J_goodsList");
        //System.out.println(goodsList.html());
        ArrayList<Content> contents = new ArrayList<Content>();
        Elements li = goodsList.getElementsByTag("li");
        for (Element el:li) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String title = el.getElementsByClass("p-name").eq(0).text();
            String price = el.getElementsByClass("p-price").eq(0).text();
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            contents.add(content);
        }
        return contents;
    }

}
