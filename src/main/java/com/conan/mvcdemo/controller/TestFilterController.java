package com.conan.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class TestFilterController {


    @RequestMapping(value = "/login")
    public String login() {
        return "login/login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String dologin(@RequestParam("account") String account, @RequestParam("pwd") String pwd) {

        System.out.println("account   " + account);
        System.out.println("pwd   " + pwd);

        return "login/loginsuccess";
    }
}
