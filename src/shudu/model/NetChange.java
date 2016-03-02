package shudu.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NetChange {
	 private String[][]getMyList;
	 private String[][]getOurList;
     public NetChange(){
     };
     public void submitScore(String name_send,String score_send)throws Exception{
    	 String url="http://114.215.134.165/soapTest/services/submitScore.php";
 		//POST的URL
 		HttpPost httppost=new HttpPost(url);
 		//建立HttpPost对象
 		List<NameValuePair> params=new ArrayList<NameValuePair>();
 		//建立一个NameValuePair数组，用于存储欲传送的参数
 		params.add(new BasicNameValuePair("name",name_send));
 		params.add(new BasicNameValuePair("score",score_send));
 		//添加参数
 		httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
 		//设置编码
 		HttpResponse response = HttpClientBuilder.create().build().execute(httppost);
 		//发送Post,并返回一个HttpResponse对象
                 //Header header = response.getFirstHeader("Content-Length");
 		//String Length=header.getValue();
                 // 上面两行可以得到指定的Header
 		if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
 			String result=EntityUtils.toString(response.getEntity());
 			//得到返回的字符串
 			//System.out.println(result);
 			
 			
 			//打印输出
                        //如果是下载文件,可以用response.getEntity().getContent()返回InputStream
 		}
     }
     public String[][] getMyListWay() throws Exception{
    	 String url="http://114.215.134.165/soapTest/services/getMyList.php";
 		//POST的URL
 		HttpPost httppost=new HttpPost(url);
 		//建立HttpPost对象
 		List<NameValuePair> params=new ArrayList<NameValuePair>();
 		//建立一个NameValuePair数组，用于存储欲传送的参数
 		params.add(new BasicNameValuePair("name","Zero"));
 		//添加参数
 		httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
 		//设置编码
 		HttpResponse response = HttpClientBuilder.create().build().execute(httppost);
 		if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
 			String result=EntityUtils.toString(response.getEntity());
 			//得到返回的字符串
 			//System.out.println(result);
 			getMyList=getJsonData(result);
 			//打印输出
                        //如果是下载文件,可以用response.getEntity().getContent()返回InputStream
 		}
    	 return getMyList;
     }
     
     public String[][] getOurListWay() throws Exception{
    	 String url="http://114.215.134.165/soapTest/services/getOurList.php";
  		//POST的URL
  		HttpPost httppost=new HttpPost(url);
  		//建立HttpPost对象
  		List<NameValuePair> params=new ArrayList<NameValuePair>();
  		//建立一个NameValuePair数组，用于存储欲传送的参数
  		params.add(new BasicNameValuePair("name","Zero"));
  		//添加参数
  		httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
  		//设置编码
  		HttpResponse response = HttpClientBuilder.create().build().execute(httppost);
  		System.out.println(response.getStatusLine().getStatusCode());
  		if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
  			String result=EntityUtils.toString(response.getEntity());
  			//得到返回的字符串
  			System.out.println(result);
  			getOurList=getJsonData(result);
  			//打印输出
                         //如果是下载文件,可以用response.getEntity().getContent()返回InputStream
  		}
    	 return getOurList;
     }
     
     public String[][] getJsonData(String jsonString){
    	 String [][]temp;
    	 Gson gson=new Gson();  
    	 Map<String, List<dataType>> getlist = gson.fromJson(jsonString, new TypeToken<Map<String, List<dataType>>>() {}.getType());   
    	 List<dataType> p= getlist.get("data");
    	 int datanum=-1;
         datanum=p.size();
         temp=new String[datanum][2];
         if(datanum==0){
        	 System.out.println("JSON error");  
         }
         else{
        	 for(int i = 0; i < datanum; i++)
             {
        		 temp[i][0]=p.get(i).name;
        		 temp[i][1]=p.get(i).score;
        		 System.out.println(temp[i][0]);
        		 System.out.println(temp[i][1]); 
             }
    	  
         }
    	 return temp;
     }
//     	public static void main(String []arg) throws Exception{
//     		NetChange ss=new NetChange();
//     	   ss.getOurListWay();
//     }
     
}

 class dataType{
	public String name;
	public String score;
}
