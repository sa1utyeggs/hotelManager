package com.hotel.controller;

import com.hotel.enums.HttpResponseEnum;
import com.hotel.pojo.commom.ResponseResult;
import com.hotel.pojo.dto.UserDto;
import com.hotel.pojo.vo.UserLoginVo;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login/login")
    public String login(@RequestBody UserLoginVo vo, HttpServletRequest request) {
        ResponseResult<UserDto> result = userService.login(vo);
        HttpSession session = request.getSession();
        if (result.getCode().equals(HttpResponseEnum.SUCCESS.getCode())) {
            session.setAttribute("user", result.getData());
            return "redirect:/room/page";
        }
        return "login";
    }
}
