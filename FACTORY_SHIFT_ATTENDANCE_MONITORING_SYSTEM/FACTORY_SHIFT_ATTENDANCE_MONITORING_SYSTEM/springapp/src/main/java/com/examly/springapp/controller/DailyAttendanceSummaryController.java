package com.examly.springapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.DailyAttendanceSummary;
import com.examly.springapp.repository.DailyAttendanceSummaryRepository;

@RestController
@RequestMapping("/daily-summary")
public class DailyAttendanceSummaryController {

    @Autowired
    private DailyAttendanceSummaryRepository repository;

    @GetMapping("/employee/{id}")
    public Page<DailyAttendanceSummary> getSummaryByEmployeeId(
            @PathVariable Long id,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(List.of(), pageable, 0);
    }

    @GetMapping("/employee/code/{empCode}")
    public Page<DailyAttendanceSummary> getSummaryByEmployeeCode(
            @PathVariable String empCode,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        return repository.findByEmployeeCodeAndDateBetween(
                empCode,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                pageable
        );
    }

    @PostMapping
    public DailyAttendanceSummary createDailyAttendanceSummary(
            @RequestBody DailyAttendanceSummary summary) {
        return repository.save(summary);
    }
    
    @PostMapping("/create")
    public DailyAttendanceSummary createDailyAttendanceSummaryAlt(
            @RequestBody DailyAttendanceSummary summary) {
        return repository.save(summary);
    }
}
