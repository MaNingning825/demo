package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/main")
    public String main(){
        return "user/main";
    }
    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }
    @ResponseBody
    @PostMapping("/save")
    public Object save(@RequestBody User user)
    {
        userMapper.insert(user);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg","保存成功");
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Long carId, ModelMap mmap){
        User user=userMapper.selectById(carId);
        mmap.put("user",user);
        return "user/edit";
    }
    @ResponseBody
    @RequestMapping("/data")
    public Object getList(@RequestParam(defaultValue = "1")int pageNo,
                               @RequestParam(defaultValue = "5")int limit) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(pageNo,limit);
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","ok");
        map.put("count",userPage.getTotal());
        map.put("data",userPage.getRecords());
        return map;
    }
    @ResponseBody
    @PutMapping("/update")
    public Object update(@RequestBody User user)
    {
        userMapper.updateById(user);
        Map<String,Object> map=new HashMap<>();
        map.put("success",true);
        map.put("msg","更新成功");
        return map;
    }
    @ResponseBody
    @DeleteMapping( "/batchRemove")
    public Object batchRemove(@RequestParam(value = "ids",required = false)List<Integer> ids)
    {
        userMapper.deleteBatchIds(ids);
        Map<String,Object> map=new HashMap<>();
        map.put("success",true);
        map.put("msg","批量删除成功");
        return map;
    }

    @ResponseBody
    @DeleteMapping("/remove/{carId}")
    public Object remove(@PathVariable("carId") Long carId)
    {
        userMapper.deleteById(carId);
        Map<String,Object> map=new HashMap<>();
        map.put("success",true);
        map.put("msg","删除成功");
        return map;
    }
}
