/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package none.CoderCanvas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Noahb
 */
public class Main {
    public static Gson gson;
    /**
     * @param args the command line arguments
     * @throws IOException
     */
    public static String getFileContents(String filepath){
        try{
            BufferedReader testFile = new BufferedReader(new FileReader(filepath));
            String contents = "";
            String curr_line;
            while((curr_line = testFile.readLine()) != null){
                contents+=curr_line + '\n';
            }
            testFile.close();
            return contents;
        }
        catch(Exception e){}
        return "";
    }
    public static String getFileContents(File file){
        try{
            BufferedReader testFile = new BufferedReader(new FileReader(file));
            String contents = "";
            String curr_line;
            while((curr_line = testFile.readLine()) != null){
                contents+=curr_line + '\n';
            }
            testFile.close();
            return contents;
        }
        catch(Exception e){}
        return "";
    }
    public static void startApp() throws IOException{
        User user = User.init();
        MainMenu mm = new MainMenu(user);
        mm.setVisible(true);

        Thread init = new Thread(){
            public void run(){
                try{
                    user.initCourses();
                    user.initAssignments();
                    mm.threadDone();
                }
                catch(Exception e){}
            }
        };
        init.start();
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
       
        String token = User.loadToken();
        if(token.equals("")){
            User.makeDirs();
            ChangeTokenWin ctw = new ChangeTokenWin();
            ctw.setVisible(true);
        }
        else{
            CanvasAPI.setToken(token);
            startApp();
        }    
    }
}
