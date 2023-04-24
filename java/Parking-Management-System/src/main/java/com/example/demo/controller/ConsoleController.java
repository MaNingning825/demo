package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IncomeLogService;
import com.example.demo.service.SitInfoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/console")
public class ConsoleController {
    @Autowired
    private UserService userService;
    @Autowired
    private SitInfoService sitInfoService;
    @Autowired
    private IncomeLogService incomeLogService;
    @RequestMapping("/console")
    public String console(){
        return "console/console";
    }

    @ResponseBody
    @RequestMapping("/countOfUser")
    public int countOfUser(){
        return userService.countOfUser();
    }

    @ResponseBody
    @RequestMapping("/countOfSit")
    public int countOfSit(){
        return sitInfoService.countOfSit();
    }
    @ResponseBody
    @RequestMapping("/countOfAvailableSit")
    public int countOfAvailableSit(){
        return sitInfoService.countOfAvailableSit();
    }
    @ResponseBody
    @RequestMapping("/sumOfIncome")
    public Long sumOfIncome(){
        return incomeLogService.sumOfIncome();
    }
}
