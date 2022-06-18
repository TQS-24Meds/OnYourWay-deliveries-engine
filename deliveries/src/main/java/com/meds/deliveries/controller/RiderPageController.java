package com.meds.deliveries.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Admin;
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.service.AdminService;
import com.meds.deliveries.service.RiderService;

@Controller
public class RiderPageController {
    @Autowired ObjectFactory<HttpSession> httpSessionFactory;
    @Autowired AdminService adminsv;
    @Autowired RiderService ridersv;


    @GetMapping("/riders")
    public ModelAndView riders(Model model) throws NumberFormatException, ResourceNotFoundException {
  
      ModelAndView modelAndView = new ModelAndView();
  
      HttpSession session = httpSessionFactory.getObject();
      String adminid = (String.valueOf(session.getAttribute("admin_id")));
      Admin admin = adminsv.getAdminById(Integer.parseInt(adminid)).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:" + adminid));
  
      model.addAttribute("name", admin.getName());
      
      List<Rider> riderList = ridersv.getAllRiders();
  
  
      modelAndView.addObject("riderlist", riderList);
      modelAndView.setViewName("riders");
  
      return modelAndView;
    }
  
}
