package com.meds.deliveries.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Admin;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.service.AdminService;
import com.meds.deliveries.service.RiderService;


@Controller
public class IndexController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    AdminService adminsv;

    @GetMapping("/index")
    public ModelAndView index(Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String adminid = (String.valueOf(session.getAttribute("id_admin")));
      Admin admin = adminsv.getAdminById(Integer.parseInt(adminid)).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:" + adminid));
  

      model.addAttribute("stores",admin.getStores() );
      model.addAttribute("email", admin.getEmail());
      model.addAttribute("name", admin.getName());      
  
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("index");
      return modelAndView;
    }
    
}
