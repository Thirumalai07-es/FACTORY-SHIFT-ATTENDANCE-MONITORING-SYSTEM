package com.examly.springapp.service;

import org.springframework.stereotype.Service;
import com.examly.springapp.model.AttendanceLog;
import com.examly.springapp.repository.AttendanceLogRepository;

import java.util.List;

@Service
public class AttendanceLogService {
    
    private final AttendanceLogRepository attendanceLogRepository;
    
    public AttendanceLogService(AttendanceLogRepository attendanceLogRepository) {
        this.attendanceLogRepository = attendanceLogRepository;
    }
    
    public AttendanceLog addAttendanceLog(AttendanceLog attendanceLog) {
        return attendanceLogRepository.save(attendanceLog);
    }
    
    public AttendanceLog updateAttendanceLog(AttendanceLog attendanceLog) {
        return attendanceLogRepository.save(attendanceLog);
    }
    
    public List<AttendanceLog> getAllAttendanceLogs() {
        return attendanceLogRepository.findAll();
    }
}
