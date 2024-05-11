package com.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/user-login")
    public ResponseEntity<String> users(){
        return ResponseEntity.ok().body("Hello User");
    }

    @GetMapping("/admin-login")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok().body("Hello Admin");
    }
}
