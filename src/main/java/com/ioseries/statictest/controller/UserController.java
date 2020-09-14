package com.ioseries.statictest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @GetMapping("/items") Iterable<String> all() { return new ArrayList<String>(); }
}