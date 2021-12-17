package none.CoderCanvas;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Noahb
 */
public class Assignment {
    public class assignmentFile{
        @Expose (serialize = true, deserialize = true) 
        public int progress_value;
        @Expose (serialize = true, deserialize = true) 
        public String notes_value;
    }
    
    private static String folder_dir = System.getProperty("user.home") + "\\Desktop\\SargeFinal\\todo\\";
    
    @Expose (serialize = true, deserialize = true)
    private int id;
    @Expose (serialize = true, deserialize = true)
    private String description;
    @Expose (serialize = true, deserialize = true)
    private Date due_at;
    @Expose (serialize = true, deserialize = true)
    private int possible_points;
    @Expose (serialize = true, deserialize = true)
    private String name;
    @Expose (serialize = true, deserialize = true)
    private boolean has_submitted_submissions;
    
    private int progress;
    private String notes;
    
    public String getTodoDir(){
        return folder_dir + this.id + "\\";
    }
    public void markTodo(Gson gson){
       File folder = new File(getTodoDir());
       File file = new File(getTodoDir() + this.id + ".json");
       try{
           if(folder.mkdir()){   
               file.createNewFile();
               saveTodo();
           }
           else{
               loadTodo(gson);
           }
       }
       catch(Exception e){}
    }
    public void saveTodo() throws IOException{
        File file = new File(getTodoDir() + "\\" + this.id + ".json");
        FileWriter w = new FileWriter(file);
        String json = "{\"progress_value\":" + progress + ", \"notes_value\":\"" + notes +"\"}";
        w.write(json);
        w.close();
    }
    public void loadTodo(Gson gson) throws IOException{
        File file = new File(getTodoDir() + "\\" + this.id + ".json");
        String contents = Main.getFileContents(file);
        assignmentFile f = gson.fromJson(contents, assignmentFile.class);
        progress = f.progress_value;
        notes = f.notes_value;
    }
    
    public boolean isDone(){
        return has_submitted_submissions;
    }
     
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getProgress(){
        return progress;
    }
    public String getNotes(){
        return notes;
    }
    public Date getDueAt(){
        return due_at;
    }
    public String getDesc(){
        return description;
    }
    
    public void setProgress(int amt){
        progress = amt;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
