package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.IncomeLog;
import com.example.demo.entity.User;
import com.example.demo.mapper.IncomeLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Controller
@RequestMapping("/income")
public class IncomeLogController {
    @Autowired
    private IncomeLogMapper incomeLogMapper;
    private String prefix="income/";
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
    @ResponseBody
    @RequestMapping("/data")
    public Object getList(@RequestParam(defaultValue = "1")int page,
                          @RequestParam(defaultValue = "5")int limit) {
        QueryWrapper<IncomeLog> wrapper = new QueryWrapper<>();
        Page<IncomeLog> page1 = new Page<>(page,limit);
        Page<IncomeLog> userPage = incomeLogMapper.selectPage(page1, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","ok");
        map.put("count",userPage.getTotal());
        map.put("data",userPage.getRecords());
        return map;
    }
}
