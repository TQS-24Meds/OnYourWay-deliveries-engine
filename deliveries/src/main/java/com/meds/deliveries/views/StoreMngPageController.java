package com.meds.deliveries.views;

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
import com.meds.deliveries.service.AdminService;
@Controller
public class StoreMngPageController {

  
        @Autowired ObjectFactory<HttpSession> httpSessionFactory;
        @Autowired
        AdminService adminsv;
    
    
        @GetMapping("/stats")
        public ModelAndView viewstats(Model model) throws ResourceNotFoundException {
      
          ModelAndView modelAndView = new ModelAndView();
      
          HttpSession session = httpSessionFactory.getObject();
      
          
            //add store list?
      
          modelAndView.setViewName("stats");
      
          return modelAndView;
        }
      
    }
    
    
    

