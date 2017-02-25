package com.bdd.http;
import java.io.*;
public class Fileparse {

   public static String Fileparse(String filepath){
	   String request="";
	   String Encoding= "utf-8";
	   String line;
	    File file=new File(filepath);
	   try{
		   if(file.exists() && file.isFile()){
			   InputStreamReader read= new InputStreamReader(new  FileInputStream(file),Encoding); 
			   BufferedReader bufferedReader=new BufferedReader(read);
			   while((line = bufferedReader.readLine()) != null){
				   request +=line;
				   }
			   read.close();
			   
              
		    }else{
			   System.out.print("文件不存在");
			    
		    }
		   }catch(Exception e){
			   System.out.println("文件内容出错");
	           e.printStackTrace();
		   }
	   
	   return request;
	   
   }



}
