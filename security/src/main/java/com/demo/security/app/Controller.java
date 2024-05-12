package com.demo.security.app;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class Controller {


    public static final String HELLO_USER = "Hello User";
    public static final String HELLO_ADMIN = "Hello Admin";

    @GetMapping("/user-login")
    public ResponseEntity<String> users(){
        log.info("    USER LOGIN      ");
        return ResponseEntity.ok().body(HELLO_USER);
    }

    @GetMapping("/admin-login")

    public ResponseEntity<String> admin(){
        log.info("    ADMIN LOGIN      ");
        return ResponseEntity.ok().body(HELLO_ADMIN);
    }
}
