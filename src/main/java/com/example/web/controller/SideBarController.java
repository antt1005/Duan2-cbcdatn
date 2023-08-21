package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("trangchu")
public class SideBarController {

    @GetMapping
    String getSideBar() {
        return "sidebar/trangchu";
    }
}
