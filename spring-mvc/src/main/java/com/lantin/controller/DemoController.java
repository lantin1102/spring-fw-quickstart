package com.lantin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2021/05/18/22:11 周二
 *
 * @author Lantin
 */
@Controller
@RequestMapping
public class DemoController {


    @RequestMapping("demo")
    public String demo(Model model) {

        model.addAttribute("msg", "hello SpringMVC");

        return "demo";
    }


}
