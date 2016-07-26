package com.harsha.rssreader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JobFetcherFromRSS {

	public static List<String> getJobURLsFromCityRss (String cityUrl) throws ParserConfigurationException{
	    //File fXmlFile = new File("/Users/harsha/Desktop/Folder/angular.txt");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = null;
	    
	    //"http://seattle.craigslist.org/search/sof?format=rss&query=angular"
	    try {
	    	//FileInputStream in = new FileInputStream(fXmlFile);
	    	
	    	URL authenticateURL = new URL(cityUrl);
	    	  URLConnection ivyConnection = authenticateURL.openConnection();
	    	  HttpURLConnection ivyHttp = (HttpURLConnection) ivyConnection;
	    	  System.out.println("Response code ==>" + ivyHttp.getResponseCode());
	    	 //InputStream inputStream= new FileInputStream("/Users/harsha/Desktop/Folder/angular.txt");
	    	 InputStream inputStream= ivyConnection.getInputStream();
	         Reader reader = new InputStreamReader(inputStream,"UTF-8");
	         InputSource is = new InputSource(reader);
	         is.setEncoding("UTF-8");
	    	
	        doc = dBuilder.parse(is);
	    } catch (SAXException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    doc.getDocumentElement().normalize();

	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	    NodeList nList = doc.getElementsByTagName("items");
	    Node rdf = nList.item(0);
	    Element e = (Element) rdf;
	    NodeList urlList = e.getElementsByTagName("rdf:li");
	    
	    List<String> list = new ArrayList<String>();
	    for(int i=0;i<urlList.getLength();i++)
	    {
	    	Node nNode = urlList.item(i);
	    	Element el = (Element) nNode;
	    	System.out.println(el.getAttribute("rdf:resource"));
	    	list.add(el.getAttribute("rdf:resource"));
	    }
	    return list;
	 }
	
}
