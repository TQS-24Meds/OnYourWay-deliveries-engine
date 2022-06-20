package com.meds.deliveries.views;

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
import com.meds.deliveries.request.LoginRequest;
import com.meds.deliveries.service.AdminService;
import com.meds.deliveries.service.PersonService;
import com.meds.deliveries.service.RiderService;

@Controller
public class ProfController {


    @Autowired
    PersonService personsv;
    @Autowired
    RiderService ridersv;


    @Autowired 
    ObjectFactory<HttpSession> httpSessionFactory;
    
    @GetMapping("/rider/{rider_id}")
    public ModelAndView profile(@PathVariable(value="rider_id") int rider_id, Model model) throws NumberFormatException, ResourceNotFoundException { 
        ModelAndView modelAndView = new ModelAndView();
        Rider rider = ridersv.getRiderById(rider_id).orElseThrow(()->  new ResourceNotFoundException("Rider not found for this id:" + rider_id));

        model.addAttribute("rider_id", rider.getId());
        model.addAttribute("email", rider.getEmail());
        model.addAttribute("name", rider.getName());
        model.addAttribute("username",rider.getUsername());
        model.addAttribute("phone",rider.getPhone() );
        model.addAttribute("status",rider.getStatus() );
        model.addAttribute("rating",rider.getAverage_rating() );
        model.addAttribute("address",rider.getAddress() );
        
        
        modelAndView.setViewName("profile");
        return modelAndView;
    }
    
}