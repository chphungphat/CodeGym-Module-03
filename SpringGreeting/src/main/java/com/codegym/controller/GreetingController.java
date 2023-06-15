package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GreetingController {
//    @GetMapping("/greeting")
    @RequestMapping(value = {"/", "/greeting"})
    public String greeting(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

//    @RequestMapping(value = {"/", "/greeting"})
//    public void greeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("name", "CPP");
//        request.setAttribute("name0", "CPP");
//        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
////        return "index";
//    }
}
