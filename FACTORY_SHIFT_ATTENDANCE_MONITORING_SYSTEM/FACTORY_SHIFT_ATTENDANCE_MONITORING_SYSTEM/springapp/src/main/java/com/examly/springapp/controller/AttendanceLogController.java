package com.examly.springapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.AttendanceLog;
import com.examly.springapp.service.AttendanceLogService;

import java.util.List;

@RestController
@RequestMapping("/attendancelogs")
public class AttendanceLogController {
    
    private final AttendanceLogService attendanceLogService;
    
    public AttendanceLogController(AttendanceLogService attendanceLogService) {
        this.attendanceLogService = attendanceLogService;
    }
    
    @PostMapping
    public ResponseEntity<AttendanceLog> addAttendanceLog(@RequestBody AttendanceLog attendanceLog) {
        AttendanceLog savedLog = attendanceLogService.addAttendanceLog(attendanceLog);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceLog> updateAttendanceLog(@PathVariable Long id, @RequestBody AttendanceLog attendanceLog) {
        attendanceLog.setId(id);
        AttendanceLog updatedLog = attendanceLogService.updateAttendanceLog(attendanceLog);
        return new ResponseEntity<>(updatedLog, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<AttendanceLog>> getAllAttendanceLogs() {
        List<AttendanceLog> logs = attendanceLogService.getAllAttendanceLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}
