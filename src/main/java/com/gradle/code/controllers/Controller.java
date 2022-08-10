package com.gradle.code.controllers;

import com.gradle.code.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    @ResponseBody
    public String helloName(@RequestParam(name = "name") String name){
        return "Hello" + name;
    }
}
