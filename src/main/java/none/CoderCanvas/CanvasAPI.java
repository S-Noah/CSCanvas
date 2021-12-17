package none.CoderCanvas;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 *
 * @author Noahb
 */
public class CanvasAPI {
    public static String host = "https://canvas.endicott.edu";
    public static String header_key = "Authorization";
    public static String token_prefix = "Bearer ";
    public static String token = "";
    //public static String token = "2548~MGFHL0kFy5Ur7EAwHZS6jQD6vLkCYiF4wcCm81ywJhvAVfIKG5EhQOMcWeMcBLjp";
    
    public static void setToken(String newToken){
        token = newToken;
    }
    
    public static Request call(String fx){
         Request request = new Request.Builder()
            .url(host + fx)
            .header(header_key, token_prefix + token)
            .build();
        return request;
    }
    public static Request file_token(String fx, String filename) {
        RequestBody formBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", filename)
            .build();
        Request request = new Request.Builder()
            .post(formBody)
            .url(host + fx)
            .header(header_key, token_prefix + token)
            .build(); 
        return request;
    }
    public static Request file_upload(File file, String filename, String fileType, String url){
        RequestBody formBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM) 
            .addFormDataPart("file", file.getName(),
                RequestBody.create(MediaType.parse(fileType), file))
            .build();
        Request request = new Request.Builder()
            .post(formBody)
            .url(url)
            .build();
        return request;
    }
}
