/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package none.CoderCanvas;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Noahb
 */
public class User {
    private static String folder_dir = System.getProperty("user.home") + "\\Desktop\\SargeFinal\\user";
    public static void makeDirs(){
        File folder = new File(System.getProperty("user.home") + "\\Desktop\\SargeFinal");
        folder.mkdir();
        File user = new File(folder_dir);
        user.mkdir();
        File todo = new File(System.getProperty("user.home") + "\\Desktop\\SargeFinal\\todo");
        todo.mkdir();
    }
    public static User init() throws IOException{
        String response = APIClient.fire(CanvasAPI.call("/api/v1/users/self"));
        //System.out.println(response);
        User user = Main.gson.fromJson(response, User.class);
        user.loadTodo();
        return user;
    }
     public void initCourses()throws IOException{
        String response = APIClient.fire(CanvasAPI.call("/api/v1/users/self/courses"));
        int page = 1;
        while(!response.equals("[]")){
            Course[] temp = Main.gson.fromJson(response, Course[].class);
            for(Course course : temp){
                courses.add(course);
            }
            page += 1;
            response = APIClient.fire(CanvasAPI.call("/api/v1/users/self/courses?page=" + page));
        }
        Collections.sort(courses);
        
        int current_enrollment_term = courses.get(0).getEnrollment_term_id();
        
        int i = courses.size() -1;
        
        while(i > 0 && courses.get(i).getEnrollment_term_id() != current_enrollment_term){
            courses.remove(i);
            i--;
        }
    }
    public void initAssignments() throws IOException{
        for(Course course : courses){
            System.out.println(course.getId());
            String response = APIClient.fire(CanvasAPI.call("/api/v1/users/self/courses/" + course.getId() + "/assignments"));
            int page = 1;
            
            while(!response.equals("[]")){
                Assignment[] temp = Main.gson.fromJson(response, Assignment[].class);
                for(Assignment assignment : temp){
                    course.addAssignment(assignment);
                    if(todoIDs.contains((double)assignment.getId())){
                        assignment.markTodo(Main.gson);
                        todo.add(assignment);
                    }
                }
                page += 1;
                response = APIClient.fire(CanvasAPI.call("/api/v1/users/self/courses/" + course.getId() + "/assignments?page=" + page));
            }
        }
    }
    public static String loadToken(){
        File file = new File(folder_dir + "\\token.json");
        String contents = Main.getFileContents(file);
        contents = contents.trim();
        return contents;
    }
    
    public static void saveToken(String token) throws IOException{
        File file = new File(folder_dir + "\\token.json");
        FileWriter  w = new FileWriter(file);
        w.write(token);
        w.close();
    }
    public void saveTodo() throws IOException{
        String contents = Main.gson.toJson(todoIDs, ArrayList.class);
        File file = new File(folder_dir + "\\" + this.id + ".json");
        FileWriter w = new FileWriter(file);
        w.write(contents);
        w.close();
    }
    public void loadTodo(){
        Gson gson = new Gson();
        String contents = Main.getFileContents(folder_dir + "\\" + this.id + ".json");
        todoIDs = gson.fromJson(contents, ArrayList.class);
        if(todoIDs == null){
            todoIDs = new ArrayList();
        }
    }
    
    @Expose (serialize = true, deserialize = true) 
    private int id;
    
    @Expose (serialize = true, deserialize = true)
    private String name;
    
    @Expose (serialize = true, deserialize = true)
    private String created_at;
    
    @Expose (serialize = true, deserialize = true)
    private String sortable_name;
    
    @Expose (serialize = true, deserialize = true)
    private String short_name;
    
    @Expose (serialize = true, deserialize = true)
    private String avatar_url;
   
    private ArrayList<Course> courses = new ArrayList();
    private ArrayList<Double> todoIDs; 
    private ArrayList<Assignment> todo = new ArrayList(); 
     
    public void addTodoId(double idx){
        todoIDs.add(idx);
    }
    public void addTodo(Assignment a){
        todo.add(a);
    }
    public void removeTodoId(int idx){
        todoIDs.remove(idx);
    }
    public void removeTodo(int idx){
        todo.remove(idx);
    }
    
    
    public ArrayList<Assignment> getTodo(){
        return todo;
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }
    public String getName(){
        return name;
    }
    public int getID(){
        return id;
    }
   public String getAvatar_Url(){
       return avatar_url;
   }
 
    public void dump(){
        /*
        System.out.println(id);
        System.out.println(name);
        System.out.println(created_at);
        System.out.println(sortable_name);
        System.out.println(short_name);
        System.out.println(avatar_url);
        System.out.println(courses.size());
        */
        for(double x: todoIDs){
            System.out.println((int)x);
        }
    }
    
    
}
