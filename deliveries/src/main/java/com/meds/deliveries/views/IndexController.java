package com.meds.deliveries.views;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meds.deliveries.exception.ResourceNotFoundException;
import com.meds.deliveries.model.Admin;
import com.meds.deliveries.request.LoginRequest;
import com.meds.deliveries.security.auth.AuthTokenResponse;
import com.meds.deliveries.service.AdminService;


@Controller
public class IndexController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    AdminService adminsv;

    @Autowired
    RestTemplate restTemplate;

    @ModelAttribute("loginRequest")
    public LoginRequest loginRequestAttribute() {
      return new LoginRequest();
    }

    @GetMapping("/")
    public RedirectView index(HttpServletRequest request) {
      if (request.getSession().getAttribute("email") == null) {
        return new RedirectView("login");  
      } 
      return new RedirectView("index");
    }

    @GetMapping("/login")
    public ModelAndView viewLogin(Model model) {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("login");
      return modelAndView;
    }

    @PostMapping("/")
    public RedirectView loginChecking(@ModelAttribute LoginRequest loginRequest, Model model) {
      HttpSession session = httpSessionFactory.getObject();
      
      String email = loginRequest.getEmail();

      Admin admin = adminsv.getAdminByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this email:" + email));

      ResponseEntity<AuthTokenResponse> response = restTemplate.postForEntity("/api/auth/login", loginRequest, AuthTokenResponse.class);
      AuthTokenResponse authTokenResponse = response.getBody();

      if (authTokenResponse != null) {
        session.setAttribute("token", authTokenResponse.getToken());

        // pass required values
        session.setAttribute("email", email);
        session.setAttribute("admin_name", admin.getName());
        session.setAttribute("admin_phone", admin.getPhone());
        return new RedirectView("index");
      }
      
      return new RedirectView("login");    
    }
    
}
