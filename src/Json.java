

import java.util.ArrayList;
import net.sf.json.*;

public class Json {
	
	private ArrayList<SHU>bklist;
	private int json_length;
	private JSONObject jsonobj;
	
	public Json(){
		bklist=null;
		json_length=0;
		jsonobj=null;
	}
	
	public ArrayList<SHU> getBklist()
	{
		return bklist;
	}
	
	public void Json2java(String str)
	{
		try
		{
			
	     JSONObject jar=JSONObject.fromObject(str);
	     JSONArray array=jar.getJSONArray("books"); 
	     json_length=array.size();    
	     System.out.println(json_length);
	     
	     bklist=new  ArrayList<SHU>();
	    for(int i=0;i<json_length;i++)
	    {
	    	System.out.print(i+"¡¢");
	    	System.out.println(array.get(i));
	    	jsonobj =  array.getJSONObject(i);
	    	SHU bk=new SHU();
	    	bk.setId(jsonobj.getString("id"));
	    	bk.setTitle(jsonobj.getString("title"));
	    	bk.setIsbn10(jsonobj.getString("isbn10"));
	      //bk.setIsbn13(jsonobj.getString("isbn13"));
	    	bk.setImagePath(jsonobj.getString("image"));
	    	bk.setUrl(jsonobj.getString("url"));
	    	bk.setAuthor(jsonobj.getString("author"));
	    	bk.setBinding(jsonobj.getString("binding"));
	    	bk.setPages(jsonobj.getString("pages"));
	    	bk.setPrice(jsonobj.getString("price"));
	    	bk.setPubdate(jsonobj.getString("pubdate"));
	    	bk.setPublisher(jsonobj.getString("publisher"));
	    	bk.setSummary(jsonobj.getString("summary"));	
	    	//bklist=new  ArrayList<DouBan_Book>();
	    	bklist.add(bk); 	
	    }
	    
		}catch(Exception r)
		{
			r.printStackTrace();
		}
		System.out.println(bklist.size());
	}
	
	

}
