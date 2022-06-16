package com.hotel.controller;

import com.hotel.constant.MessageConst;
import com.hotel.pojo.entity.Message;
import com.hotel.pojo.vo.UserLoginVo;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 86183
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login/page")
    public String hello() {
        return "login";
    }

    @GetMapping("/login/login")
    public String login(@RequestBody UserLoginVo vo, HttpServletRequest request) {
        Message message = userService.login(vo);
        HttpSession session = request.getSession();
        if (message.getCode() == MessageConst.SUCCESS){
            session.setAttribute("user",message.getObject());
            return "redirect:/room/page";
        }
        return "login";
    }
}
