package com.examly.springapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;
    @ManyToOne
    private Employee employee;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    public AttendanceRecord(){}
    public AttendanceRecord(Long attendanceId,Employee employee,LocalDate date,LocalTime checkInTime,LocalTime checkOutTime){
        this.attendanceId=attendanceId;
        this.employee=employee;
        this.date=date;
        this.checkInTime=checkInTime;
        this.checkOutTime=checkOutTime;
    }
    public Long getAttendanceId(){
        return attendanceId;
    }
    public void setAttendanceId(Long attendanceId){
        this.attendanceId=attendanceId;
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee=employee;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    public LocalTime getCheckInTime(){
        return checkInTime;
    }
    public void setCheckInTime(LocalTime checkInTime){
        this.checkInTime=checkInTime;
    }
    public LocalTime getCheckOutTime(){
        return checkOutTime;
    }
    public void setCheckOutTime(LocalTime checkOutTime){
        this.checkOutTime=checkOutTime;
    }

}
