package edu.gpnu.controller;

import edu.gpnu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private ITestService testService;

    @GetMapping("/test")
    public String test(){
        return testService.test();
    }
}
