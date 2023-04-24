package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Controller
@RequestMapping("/sysUserLogin")
public class SysUserLoginController {
    private String prefix="sysUserLogin/";
    @RequestMapping("/main")
    public String main(){
        return prefix+"main";
    }
    @RequestMapping("/add")
    public String add(){
        return prefix+"add";
    }
    @RequestMapping("/edit")
    public String edit(){
        return prefix+"edit";
    }
}
