
package com.dinstone.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Controller
@RequestMapping(value = "/error")
public class DefaultHandlerController {

    @RequestMapping("/noHandleMethod")
    public ModelAndView noHandleMethod(ModelAndView view, HttpServletRequest request)
            throws NoSuchRequestHandlingMethodException {
        view.setViewName("index");
        throw new NoSuchRequestHandlingMethodException(request);
    }

}
