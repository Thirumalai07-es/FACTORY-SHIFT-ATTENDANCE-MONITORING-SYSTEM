package com.examly.springapp.service;

import org.springframework.stereotype.Service;
import com.examly.springapp.model.AdminUser;
import com.examly.springapp.repository.AdminUserRepository;

import java.util.List;

@Service
public class AdminUserService {
    
    private final AdminUserRepository adminUserRepository;
    
    public AdminUserService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }
    
    public AdminUser addAdmin(AdminUser admin) {
        return adminUserRepository.save(admin);
    }
    
    public AdminUser getAdminById(Long id) {
        return adminUserRepository.findById(id).orElse(null);
    }
    
    public AdminUser updateAdmin(AdminUser admin) {
        return adminUserRepository.save(admin);
    }
    
    public List<AdminUser> getAllAdmins() {
        return adminUserRepository.findAll();
    }
}
