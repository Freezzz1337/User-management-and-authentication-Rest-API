package com.freezzz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class testContoller {
    @GetMapping("/test")
    public String test(){
        return "Hello world!";
    }
}
