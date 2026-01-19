package com.example.car.tp1.ctrl;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "<h1>Hello world!</h1>";
    }
}