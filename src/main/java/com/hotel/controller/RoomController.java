package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86183
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    @GetMapping("/page")
    public String roomPage(){
        return "room";
    }
}
