/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.psu.cmpsc221.courseScheduler.query;

import edu.psu.cmpsc221.courseScheduler.connection.DBConnection;
import edu.psu.cmpsc221.courseScheduler.dao.StudentEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author abhinavsenthil
 */
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudents;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentid, firstname, lastname) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid, firstname, lastname from app.student order by firstname, lastname");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next()){
                students.add(createStudentObject(resultSet, resultSet.getString(1)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    
    
    
    public static StudentEntry getStudent(String studentID){
        connection = DBConnection.getConnection();
        try {
            getStudents = connection.prepareStatement("select firstname, lastname from app.student where studentid = ?");
            getStudents.setString(1, studentID);
            resultSet = getStudents.executeQuery();
            return createStudentObject(resultSet, studentID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;   
        
    }
    
    private static StudentEntry createStudentObject(ResultSet resultSet, String studentID) throws SQLException{
        StudentEntry student = new StudentEntry(studentID);
        student.setFirstName(resultSet.getString(2));
        student.setLastName(resultSet.getString(3));
        
        return student;
    }
    
    
    private static void dropStudent(String studentID){
        connection = DBConnection.getConnection();
        try {
            dropStudent = connection.prepareStatement("delete from student where studentid = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
