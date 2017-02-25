package com.bdd.step;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.http.ParseException;

import com.bdd.http.*;



public class  testcase{

  
   public static  void main(String[] args) throws ParseException, IOException, Exception {
	   String request;
	   String reques1;
	  // String json=null;
	  // Fileparse  file=new  Fileparse();
	   //json=file.Fileparse("D:\\work\\Interface test\\file\\json.txt");
	   //System.out.println(json);
	   //HttpRequest test=new HttpRequest();
	   //request=test.sendPostjson("http://127.0.0.1:8088/v1/transfer/transferFiles", json);
	   //Class c=Class.forName("com.bdd.http.HttpClient");
	   Class c= new HttpClient().getClass();
	   //Method method = c.getMethod("get");
	   //System.out.println(method);
	   
	    
	   //reques1=HttpClient.stringsplit(request,"(?<=BDORZ=).*(?=; max-age)");
	   //System.out.println(reques1);
	   
	   
	   
   
		  
		  
	  }

   

     

}


