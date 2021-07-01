package com.lantin.spring.controller.inner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2021/06/28/14:22 周一
 *
 * @author Lantin
 */
@Controller
@RequestMapping("inner")
@Slf4j
public class InnerController {


    @ResponseBody
    @RequestMapping("test")
    public Object testInnerScan() {

        return "hello spring mvc";
    }
}
