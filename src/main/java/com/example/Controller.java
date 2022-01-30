package com.example;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/process")
    public String process(Model model, @RequestParam("infix") String infix){
        String result = Service.infixToPostfix(infix);
        model.addAttribute("result", result);
        return "index";
    }
}
