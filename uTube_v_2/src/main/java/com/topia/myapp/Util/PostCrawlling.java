package com.topia.myapp.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.Matches;

import com.topia.myapp.entity.Post;

public class PostCrawlling {
	
	public void crawlling(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements ele = doc.select("script");
		String str = ele.get(18).toString();
		//System.out.println(str);
		//description\":*\"simpleText\\\":(.*?)},\\\"lengthSeconds\":
		Pattern p = Pattern.compile("description\":(.*?)\"lengthSeconds\"");
		Matcher m = p.matcher(str);
		while(m.find()) {
			System.out.println(m.group());
			
		}
		
	}
	
	public static void main(String[] args) {
		PostCrawlling pc = new PostCrawlling();
		pc.crawlling("https://www.youtube.com/watch?v=0jmOQ_qjJ4A");
	}
}
