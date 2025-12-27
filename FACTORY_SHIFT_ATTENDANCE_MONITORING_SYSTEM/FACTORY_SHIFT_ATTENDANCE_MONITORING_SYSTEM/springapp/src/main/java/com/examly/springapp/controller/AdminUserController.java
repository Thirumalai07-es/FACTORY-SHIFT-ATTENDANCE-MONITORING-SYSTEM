package com.examly.springapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.AdminUser;
import com.examly.springapp.service.AdminUserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    
    private final AdminUserService adminUserService;
    
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AdminUser> getAdminById(@PathVariable Long id) {
        AdminUser admin = adminUserService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<AdminUser> addAdmin(@RequestBody AdminUser admin) {
        AdminUser savedAdmin = adminUserService.addAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AdminUser> updateAdmin(@PathVariable Long id, @RequestBody AdminUser admin) {
        admin.setId(id);
        AdminUser updatedAdmin = adminUserService.updateAdmin(admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<AdminUser>> getAllAdmins() {
        List<AdminUser> admins = adminUserService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
