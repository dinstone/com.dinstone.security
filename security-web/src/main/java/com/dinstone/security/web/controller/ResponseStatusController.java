
package com.dinstone.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error")
public class ResponseStatusController {

    @RequestMapping("/unauth")
    public ModelAndView unauth(ModelAndView view) {
        view.setViewName("index");
        throw new UnauthorizedException();
    }

}