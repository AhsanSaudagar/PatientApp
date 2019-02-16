package com.perennial.patientapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "patientApp")
public class UserController {

    @RequestMapping(value = "/")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "Login User | Patient App");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView getOutwardSupplyPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        //modelAndView.addObject("title", "Login User | Patient App");
        return modelAndView;
    }
}
