package com.example.demo.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequestMapping("hc")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/hi/ko")
    public ModelAndView sayHi() {
        var model = new HashMap<String,Object>();
        model.put("nom", "NGUYEN");
        model.put("prenom", "Phuong-Lan");

        return new ModelAndView("HelloMAV", model);
    }
}
