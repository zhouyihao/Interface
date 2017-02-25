package com.bdd.http;
import java.util.Iterator;

import net.sf.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONparsing {
  

   public  static String JsonRequest(String jsonstr,String keystr){
	    String result=null;
	    JSONObject json=new JSONObject().fromObject(jsonstr);
	    //JSONArray jsonlist=json.getJSONArray(key);
	    for(Iterator iter = json.keys(); iter.hasNext();){
	    	String key = (String)iter.next();
	    	if(key.equals(keystr)){
	    		result=json.getString(key);
	    		break;
	    	}
	   
	    	}
	    if(result==null){
	    	return "没有该字段";
	    }
	   
	 return result;
	   
	   
	   }

   




}
