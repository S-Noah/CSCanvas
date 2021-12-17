/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package none.CoderCanvas;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Map;
/**
 *
 * @author Noahb
 */
public class APIClient {
    public static OkHttpClient client = new OkHttpClient();
    public static Gson gson = new Gson();
    
    public static Request request(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }
    
    public static Request authedRequest(String url, String headerKey, String token){
        Request request = new Request.Builder()
                .url(url)
                .header(headerKey, token)
                .build();
        return request;
    }
    
    public static String fire(Request request) throws IOException{
        Response response = client.newCall(request).execute();
        //System.out.println(request);
        return response.body().string();
    }
    
    public static Map<String, Object> jsonStringToMap(String js){
        return gson.fromJson(js, Map.class);
    }
}   
