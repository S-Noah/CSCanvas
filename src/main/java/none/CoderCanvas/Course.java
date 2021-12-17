package none.CoderCanvas;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Noahb
 */
public class Course implements Comparable{
    @Expose (serialize = true, deserialize = true) 
    private int id;
    @Expose (serialize = true, deserialize = true) 
    private String name;
    @Expose (serialize = true, deserialize = true) 
    private int enrollment_term_id;
    @Expose (serialize = true, deserialize = true) 
    private String course_code;
    @Expose (serialize = true, deserialize = true) 
    private String start_at;
    @Expose (serialize = true, deserialize = true) 
    private String end_at;
    
    private ArrayList<Assignment> assignments;

    public Course(int id, String name, String course_code) {
        this.id = id;
        this.name = name;
        this.course_code = course_code;
    }
 
    public void addAssignment(Assignment a){
        if(assignments == null){
            assignments = new ArrayList();
        }
        assignments.add(a);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEnrollment_term_id() {
        return enrollment_term_id;
    }
    public String getStart_At() {
        return start_at;
    }
    public String getCourse_code() {
        return course_code;
    }
    public Assignment getAssignment(int i){
        return assignments.get(i);
    }
    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }
    public String getEnd_At(){
        return end_at;
    }
    
    public void dump(){
        System.out.println("id           : " + id);
        System.out.println("name         : " + name);
        System.out.println("enrollment_term_id   : " + enrollment_term_id);
        System.out.println("course_code: " + course_code);
        System.out.println(start_at);
    }
    @Override
    public String toString(){
        return course_code;
    }
    @Override
    public int compareTo(Object c){
        Course course = (Course)c;
        return course.getEnrollment_term_id() - this.enrollment_term_id;
    }
}
    
    
