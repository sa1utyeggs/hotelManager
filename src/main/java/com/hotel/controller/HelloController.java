package com.hotel.controller;

import com.hotel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

/**
 * @author 86183
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        helloService.test();
        return "hello";
    }
}
