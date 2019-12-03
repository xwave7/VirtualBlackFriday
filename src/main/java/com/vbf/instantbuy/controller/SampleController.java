package com.vbf.instantbuy.controller;

import com.vbf.instantbuy.result.CodeMsg;
import com.vbf.instantbuy.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by BRODY on 2019/12/2.
 */

@Controller
@RequestMapping("/demo")
public class SampleController {

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello, instantBuy");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Brody");
        return "hello";
    }
}
