package com.bdd.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClient {




	public static  void PostformParams() throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
    	CloseableHttpResponse response;
    	
    	HttpPost httpPost=new HttpPost("https://www.baidu.com/");
    	List<NameValuePair> formParams=new ArrayList<NameValuePair>();
    	formParams.add(new BasicNameValuePair("username", "sun"));
    	formParams.add(new BasicNameValuePair("password","sun"));
    	formParams.add(new BasicNameValuePair("csrf_token", "value"));
    		
    	UrlEncodedFormEntity entity = null;
    	try {
			entity=new UrlEncodedFormEntity(formParams,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	httpPost.setEntity(entity);
    	//发送http请求
    	response=httpClient.execute(httpPost);
    	
    	//tp请求后的实体信息
    	HttpEntity httpEntity=response.getEntity();
    	//将http请求返回的实体信息转码为utf-8
    	String a=EntityUtils.toString(httpEntity, "utf-8");
    	Header[] h=response.getAllHeaders();
    	try{
    		String b=response.getFirstHeader("Status").toString();
    		System.out.print(b);
    	}catch(Exception e){
    	
    		System.out.print(e);
    	
    	}finally{
    		httpClient.close();
    	}
		
		
	}

	public  String get(String url) throws ParseException, IOException 
	{
		//String url=null;
		String header=null;
		String Entity=null;
		CloseableHttpClient httpClient=HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet HttpGet=new HttpGet(url);
		try {
			response=httpClient.execute(HttpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		HttpEntity httpEntity=response.getEntity();
    	header=response.getFirstHeader("Status").toString();
    	Entity=EntityUtils.toString(httpEntity, "utf-8");
		
		return Entity;
	}


	
	
	
	
	public static String stringsplit(String string,String regEx){
	    
		System.out.println(string); 
	    //String regEx = "(?<=BDORZ=).*(?=; max-age)";
		String split=null;
	    //String regEx = "(?<=csrf_tocken).*(?=; path)";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(string);
		
		while(matcher.find()){ 
			split=matcher.group();
		     
		  } 
		  return split;
		}


}
