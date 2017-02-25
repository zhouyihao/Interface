package com.bdd.step;

import java.io.IOException;

import org.apache.http.ParseException;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.bdd.http.HttpClient;
import static org.junit.Assert.*;
public class InterfaceSteps {

	@When("发送get请求$url，返回结果应该存在$request")
	public void GetUrl(String url,String request) throws Exception
	{
	 String getresquest;
	 String check;
     HttpClient httpclient=new  HttpClient();
     getresquest=httpclient.get(url);
     check=httpclient.stringsplit(getresquest, "(?<=<!--).*(?=-->)");
     assertEquals(check,request);
     }



}
