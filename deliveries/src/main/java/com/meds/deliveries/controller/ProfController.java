package com.meds.deliveries.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Admin;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.service.AdminService;
import com.meds.deliveries.service.PersonService;
import com.meds.deliveries.service.RiderService;

@Controller
public class ProfController {

    @Autowired
    AdminService admnsv;
    @Autowired
    PersonService personsv;
    @Autowired
    AdminService adminsv;


    @Autowired 
    ObjectFactory<HttpSession> httpSessionFactory;
    
    @GetMapping("/profile")
    public ModelAndView profile(Model model) throws NumberFormatException, ResourceNotFoundException {

        HttpSession session = httpSessionFactory.getObject();
        String adminid = (String.valueOf(session.getAttribute("admin_id")));
        Admin admin = adminsv.getAdminById(Integer.parseInt(adminid)).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:" + adminid));
        

        model.addAttribute("admin_id", admin.getId());
        model.addAttribute("stores",admin.getStores() );
        model.addAttribute("email", admin.getEmail());
        model.addAttribute("name", admin.getName());
        model.addAttribute("username",admin.getUsername());
        model.addAttribute("phone",admin.getPhone() );
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
}



}