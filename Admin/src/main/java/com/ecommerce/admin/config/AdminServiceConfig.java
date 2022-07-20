package com.ecommerce.admin.config;

import com.ecommerce.library.model.Admin;
import com.ecommerce.library.repository.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class AdminServiceConfig implements UserDetailsService {

    private AdminRepository adminRepository;

    public AdminServiceConfig() {
    }

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if(admin == null) {
            throw new UsernameNotFoundException("Ne trouve pas l'utilisateur");
        }
        return new User(
                admin.getUsername(),
                admin.getPassword(),
                admin.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));

    }


}
