/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.psu.cmpsc221.courseScheduler.query;

import edu.psu.cmpsc221.courseScheduler.connection.DBConnection;
import edu.psu.cmpsc221.courseScheduler.dao.ScheduleEntry;
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
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduleStudentCount;
    private static PreparedStatement getScheduledStudentCount;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry schedule) throws SQLException
    {
        connection = DBConnection.getConnection();
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, courseCode, studentID, status, timestamp_) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, schedule.getSemester());
            addScheduleEntry.setString(2, schedule.getCourseCode());
            addScheduleEntry.setString(3, schedule.getStudentID());
            addScheduleEntry.setString(4, schedule.getStatus());
            addScheduleEntry.setTimestamp(5, schedule.getTimestamp());
            addScheduleEntry.executeUpdate();
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select courseCode, studentID, Status, timestamp_ from app.schedule where semester = ? and studentid = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next()){
                schedules.add(createScheduleObject(semester, resultSet));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedules;
    }
    
    private static ScheduleEntry createScheduleObject(String semester, ResultSet resultSet) throws SQLException{
        ScheduleEntry schedule = new ScheduleEntry(semester);
        schedule.setCourseCode(resultSet.getString(1));
        schedule.setStudentID(resultSet.getString(2));
        schedule.setStatus(resultSet.getString(3));
        schedule.setTimestamp(resultSet.getTimestamp(4));
        
        return schedule;
    }
    
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        connection = DBConnection.getConnection();
        Integer num = 0;
        try {
            getScheduledStudentCount = connection.prepareStatement("select count(studentid) as recCount from app.schedule where semester = ? and status = 'S' and courseCode = ?");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
                while(resultSet.next())
            {
                num = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }


}
