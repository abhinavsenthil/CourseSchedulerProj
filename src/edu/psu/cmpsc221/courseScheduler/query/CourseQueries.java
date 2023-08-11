/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.psu.cmpsc221.courseScheduler.query;

import edu.psu.cmpsc221.courseScheduler.connection.DBConnection;
import edu.psu.cmpsc221.courseScheduler.dao.CourseEntry;
import edu.psu.cmpsc221.courseScheduler.dao.StudentEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhinavsenthil
 */
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourses;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (semester, courseCode, description, seats) values (?,?,?,?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<CourseEntry> getAllCourses(String semester){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            System.out.println("Get All Courses Invoked for: "+semester);
            getAllCourses = connection.prepareStatement("select courseCode, description, seats from app.course where semester = ? order by courseCode");
            getAllCourses.setString(1, semester);
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                CourseEntry course = new CourseEntry(semester);
                course.setCourseCode(resultSet.getString(1));
                course.setDescription(resultSet.getString(2));
                course.setSeats(resultSet.getInt(3));
                
                courses.add(course);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try {
            getAllCourseCodes = connection.prepareStatement("select courseCode from app.course where semester = ? order by courseCode");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next()){
                courseCodes.add(resultSet.getString(1));  
            }
                
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        
        return courseCodes;
    }
    
    public static Integer getCourseSeats(String semester, String courseCode){
        Integer num = 0;
        connection = DBConnection.getConnection();
        try {

            getCourseSeats = connection.prepareStatement("select seats from app.course where semester = ? and coursecode = ?");
            getCourseSeats.setString(1, semester);
            getCourseSeats.setString(2, courseCode);
            resultSet = getCourseSeats.executeQuery();
                while(resultSet.next())
            {
                num = resultSet.getInt(1);
            }
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return num;   
        
    }
    
    
    private static void dropCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try {
            dropCourse = connection.prepareStatement("delete from course where semester = ? and courseCode = ?");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, courseCode);
            dropCourse.executeUpdate(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
