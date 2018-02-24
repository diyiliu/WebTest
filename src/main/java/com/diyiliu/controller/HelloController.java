package com.diyiliu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: HelloController
 * Author: DIYILIU
 * Update: 2018-02-22 13:38
 */

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hi")
    public String sayHello(HttpServletRequest request, @RequestBody String body) {
        System.out.println("parameter: " + request.getParameterMap().keySet());
        System.out.println("requestBody: " + body);

        return "hello";
    }

}
