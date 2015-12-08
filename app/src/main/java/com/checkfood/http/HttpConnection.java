package com.checkfood.http;

<<<<<<< HEAD
import android.util.Log;

=======
>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class HttpConnection {

    public static String getSetDataWeb(String url, String method, String data){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String answer = "";

        try{
            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
<<<<<<< HEAD
            valores.add(new BasicNameValuePair("",data));
            Log.i("Script", "Url: " + httpPost.getURI());
            Log.i("Script","Method: "+httpPost.getMethod());
            Log.i("Script", "Json: " + valores.toString());
=======
            valores.add(new BasicNameValuePair("method",method));
            valores.add(new BasicNameValuePair("json", data));

>>>>>>> fb130ec6ebe1a2923c345a67f130dd6005867357
            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            HttpResponse resposta = httpClient.execute(httpPost);
            answer = EntityUtils.toString(resposta.getEntity());
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return answer;
    }
}
