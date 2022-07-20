package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final AdminServiceImpl adminService;

    public LoginController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                              BindingResult result,
                              Model model,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
       try {
           session.removeAttribute("message");
           if(result.hasErrors()){
               model.addAttribute("adminDto", adminDto);
               result.toString();
               return "register";
           }

           String username = adminDto.getUsername();
           Admin admin = adminService.findByUsername(username);
           if(admin != null) {
               model.addAttribute("adminDto", adminDto);
               System.out.println("admin est pas null ");
               session.setAttribute("message","Votre email a été enregistr");
               return "register";
           }
           if(adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
               adminService.save(adminDto);
               System.out.println("succès");
               model.addAttribute("adminDto", adminDto);
               redirectAttributes.addFlashAttribute("message", "Enregistrement réussi");
           } else {
               model.addAttribute("adminDto", adminDto);
               redirectAttributes.addFlashAttribute("message", "Mot de passe pas le meme");
               session.setAttribute("message","Mot de passe pas le meme");
               System.out.println("Mot de passe pas le meme");
               return "register";
           }
       } catch (Exception e) {
     redirectAttributes.addFlashAttribute("message","Ne peut pas s'enregistrer parce que qu'il y a une errour de server !!!");
       }
        return "error";
    }

}
