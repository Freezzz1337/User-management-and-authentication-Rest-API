package com.freezzz.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Test method for authenticated user with 'USER' role
    @GetMapping("/test")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    // Test method for authenticated user with 'ADMIN' role
    @GetMapping("/admin/test")
    public ResponseEntity<String> adminTest(){
        return ResponseEntity.ok("Hello, admin!");
    }
}
