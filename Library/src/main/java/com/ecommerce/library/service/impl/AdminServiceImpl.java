package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.repository.AdminRepository;
import com.ecommerce.library.repository.RoleRepository;
import com.ecommerce.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminServiceImpl implements AdminService  {

    private BCryptPasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    private final RoleRepository roleRepository;

    public AdminServiceImpl(RoleRepository roleRepository, AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
    @Override

    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        /*         admin.setPassword(adminDto.getPassword(passwordEncoder.encode(adminDto.getPassword()));*/

        admin.setRoles(Collections.singletonList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);

    }
}
