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
import com.meds.deliveries.model.Rider;
import com.meds.deliveries.service.AdminService;
import com.meds.deliveries.service.RiderService;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class RiderPageController {
    @Autowired ObjectFactory<HttpSession> httpSessionFactory;
    @Autowired AdminService adminsv;
    @Autowired RiderService ridersv;


    @GetMapping("/riders")
    public ModelAndView riders(Model model , @RequestParam(name="keyword", required = false, defaultValue = "") String keyword) throws NumberFormatException, ResourceNotFoundException {  
      ModelAndView modelAndView = new ModelAndView();
  
      HttpSession session = httpSessionFactory.getObject();
      
  
      log.info(model);

      System.out.println("LISTA keyword:" + keyword);

      modelAndView.setViewName("riders");

      if (keyword != null) {
        System.out.println("olha aqui" + keyword);

        List<Rider> listaFiltrada = ridersv.findKeyword(keyword);
        System.out.println("LISTA FILTRADA:" + listaFiltrada);
        modelAndView.addObject("listRiders", listaFiltrada);

      } else {
        List<Rider> riderList = ridersv.getAllRiders();
        System.out.println("entrei");

        System.out.println("LISTA toda:" + riderList);
        modelAndView.addObject("listRiders", riderList);

      }
  
      return modelAndView;
    }
  
}
