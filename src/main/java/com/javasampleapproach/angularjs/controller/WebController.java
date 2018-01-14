package com.javasampleapproach.angularjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javasampleapproach.angularjs.vo.*;

@Controller
public class WebController {
	@RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }
	
	
	
}