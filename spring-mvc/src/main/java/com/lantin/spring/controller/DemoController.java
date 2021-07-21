package com.lantin.spring.controller;


import com.lantin.core.basic.CommonResponse;
import com.lantin.spring.model.User;
import com.lantin.spring.config.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2021/05/18/22:11 周二
 *
 * @author Lantin
 */
@Controller
@RequestMapping
@Slf4j
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("demo")
    public String demo(Model model) {

        model.addAttribute("msg", "hello SpringMVC");

        return "demo";
    }

    @RequestMapping("back")
    public String back() {
        return "back";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "do-service")
    public CommonResponse<?> demoService(HttpServletResponse resp) {
        //这个对响应的中文无效，会被MVC内部的MessageConverter转换器的默认编码ISO 8859-1覆盖覆盖
        // resp.setContentType("text/html;charset=utf-8");
        int a = 1 / 0;
        String s = demoService.demoService();
        return CommonResponse.success();
    }

    /**
     * 响应json类型数据
     *
     * @return user对象
     */
    @ResponseBody
    @RequestMapping("return-json")
    public User returnJson() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setSex("男");

        return user;
    }
}