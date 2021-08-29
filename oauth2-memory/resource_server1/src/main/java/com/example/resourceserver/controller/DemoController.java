package com.example.resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@PreAuthorize("#oauth2.hasScope('write')")
    @GetMapping("/demo")
    public String demo() {
        return "RESOURCE 1";
    }
}
