package com.example.simplerssreader4.parser;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/**
 * Created by thomas on 2/24/14.
 */
public class DOMParser {

    private RSSFeed _feed = new RSSFeed();

    public RSSFeed parseXml(String xml) {

        URL url = null;
        try {
            url = new URL(xml);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nl = doc.getElementsByTagName("item");
            int length = nl.getLength();

            for (int i = 0; i < length; i++) {
                Node currentNode = nl.item(i);
                RSSItem _item = new RSSItem();

                NodeList nchild = currentNode.getChildNodes();
                int clength = nchild.getLength();

                for (int j = 1; j < clength; j = j + 2) {
                    Node thisNode = nchild.item(j);
                    String theString = null;
                    String nodeName = thisNode.getNodeName();

                    theString = nchild.item(j).getFirstChild().getNodeValue();

                    if(theString != null) {
                        if("title".equals(nodeName)) {
                            _item.setTitle(theString);
                        } else if("description".equals(nodeName)) {
                            _item.setDescription(theString);

                            String html = theString;
                            org.jsoup.nodes.Document docHtml = Jsoup.parse(html);
                            Elements imgEle = docHtml.select("img");
                            _item.setImage(imgEle.attr("src"));

                        } else if ("pubDate".equals(nodeName)) {
                            String formatedDate = theString.replace(" +0000", "");
                            _item.setDate(formatedDate);
                        }
                    }
                }
                _feed.addItem(_item);
            }
        } catch (Exception e) {}
        return _feed;
    }
}
