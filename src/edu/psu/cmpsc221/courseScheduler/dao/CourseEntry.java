/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.psu.cmpsc221.courseScheduler.dao;

/**
 *
 * @author abhinavsenthil
 */
public class CourseEntry {
    private String semester;
    private String courseCode;
    private String description;
    private Integer seats;
    
    public CourseEntry(String semester){
        this.semester = semester;
    }
    
    

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the seats
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    
    
}
