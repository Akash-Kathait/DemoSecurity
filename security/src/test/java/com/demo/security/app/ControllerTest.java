package com.demo.security.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {


Controller testController;

    @BeforeEach
    void setup() {
        testController = new Controller();
    }

    @Test
    @DisplayName("user login test")
    void users() {
        assertEquals(testController.users(), ResponseEntity.ok(testController.HELLO_USER),"user-login should be returned");
    }

    @Test
    @DisplayName("admin login test")
    void admin() {
        assertEquals(testController.admin(), ResponseEntity.ok(testController.HELLO_ADMIN),
        "admin login should be returned");
    }
}