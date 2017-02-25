package com.bdd.step;

import com.bdd.http.*;



public class  testcase{

  
   public static  void main(String[] args){
	   String request;
	   String json=null;
	   Fileparse  file=new  Fileparse();
	   json=file.Fileparse("D:\\work\\Interface test\\file\\json.txt");
	   System.out.println(json);
   
		  
		  
	  }
   /*HttpRequest test=new HttpRequest();
   request=test.sendPostjson("http://127.0.0.1:8088/v1/transfer/transferFiles", json);
   System.out.println(request);*/
   

     

}


