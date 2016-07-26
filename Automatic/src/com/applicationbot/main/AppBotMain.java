package com.applicationbot.main;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openqa.selenium.support.ui.Sleeper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.applicatiobot.dao.impl.DetailsDAOImpl;
import com.applicatiobot.dao.impl.FailedDetailsDAOImpl;
import com.applicationbot.dao.DetailsDAO;
import com.applicationbot.dao.FailedDetailsDAO;
import com.applicationbot.model.Details;
import com.applicationbot.model.FailedDetails;
import com.applicationbot.scraper.EmailRetriever;
import com.applicationbot.sendemail.EmailSender;
import com.harsha.rssreader.JobFetcherFromRSS;


public class AppBotMain{ 
	
	
	 static DetailsDAOImpl dao;
	 static FailedDetailsDAOImpl fdao;
	 
	 
 public static void main (String[] args) throws ParserConfigurationException{
	 List<String> cityURLs = new ArrayList<String>();
	 cityURLs.add("http://seattle.craigslist.org/search/sof?format=rss&query=angular");
	 
	  dao = new DetailsDAOImpl();
	  fdao = new FailedDetailsDAOImpl();
	 
	 for(String cityURL : cityURLs)
     processJobsFromOneCity(cityURL);
 }
private static void processJobsFromOneCity(String cityURL) throws ParserConfigurationException
		 {
	
	List<String> list= JobFetcherFromRSS.getJobURLsFromCityRss(cityURL) ;
	 
     
     int i=0;
     for(String s:list)
		try {
			{
				 
				 
				 Details d = new Details(s);
				 FailedDetails fd = new FailedDetails(s);
				
				 if(dao.find(d) || fdao.find(fd))
					 continue;
				 String emailAddress = EmailRetriever.getEmailAddressFromCLURL(d.getJobId());
				 boolean isEmailSent = false;
				 
				 i++;
				 
				 if(i==14)
				 break;
				 
				 if(!emailAddress.equals(""))
					 isEmailSent=EmailSender.sendResume(emailAddress);
				 if(isEmailSent)
				 dao.save(d);
				 else
			     fdao.save(new FailedDetails(d.getJobId()));
				 
				 Thread.sleep(90*1000);
				 
				 
			 }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
} 
 
 }