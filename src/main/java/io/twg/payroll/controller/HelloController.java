package io.twg.payroll.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    private static final String template = "Hello, %s!";

    @RequestMapping("hello")
    public String helloWorld(@RequestParam(value="name", defaultValue="World") String name){
        return String.format(template, name);
    }
}
