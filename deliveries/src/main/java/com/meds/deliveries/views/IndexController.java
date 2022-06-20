package com.meds.deliveries.views;

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

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2

public class IndexController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    AdminService adminsv;

    @GetMapping("/")
    public ModelAndView index(Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String adminid = (String.valueOf(session.getAttribute("admin_id")));
      log.info(adminid);

      Admin admin = adminsv.getAdminById(Integer.parseInt(adminid)).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:" + adminid));
  

      model.addAttribute("email", admin.getEmail());
      model.addAttribute("name", admin.getName());      
  
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("index");
      return modelAndView;
    }
    
}
