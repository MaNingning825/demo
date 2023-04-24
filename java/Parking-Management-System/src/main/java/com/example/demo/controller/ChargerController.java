package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Charger;
import com.example.demo.entity.User;
import com.example.demo.mapper.ChargerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * <p>
 * 停车收费标准表 前端控制器
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Controller
@RequestMapping("/charger")
public class ChargerController {
    @Autowired
    private ChargerMapper chargerMapper;
    private String prefix="charger/";
    @RequestMapping("/main")
    public String main(){
        return prefix+"main";
    }
    @RequestMapping("/add")
    public String add(){
        return prefix+"add";
    }
    @ResponseBody
    @RequestMapping("/save")
    public Object save(@RequestBody Charger charger){
        chargerMapper.insert(charger);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg","保存成功");
        return map;
    }
    @RequestMapping("/edit")
    public String edit(Long id, ModelMap map){
        Charger charger=chargerMapper.selectById(id);
        map.put("charger",map);
        return prefix+"edit";
    }
    @ResponseBody
    @RequestMapping("/data")
    public Object getList(@RequestParam(defaultValue = "1")int page,
                          @RequestParam(defaultValue = "5")int limit) {
        QueryWrapper<Charger> wrapper = new QueryWrapper<>();
        Page<Charger> page1 = new Page<>(page,limit);
        Page<Charger> ChargerPage = chargerMapper.selectPage(page1, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","ok");
        map.put("count",ChargerPage.getTotal());
        map.put("data",ChargerPage.getRecords());
        return map;
    }
    @ResponseBody
    @PutMapping("/update")
    public Object update(@RequestBody Charger Charger)
    {
        chargerMapper.updateById(Charger);
        Map<String,Object> map=new HashMap<>();
        map.put("success",true);
        map.put("msg","更新成功");
        return map;
    }
    @ResponseBody
    @DeleteMapping( "/batchRemove")
    public Object batchRemove(@RequestParam(value = "ids",required = false) List<Integer> ids)
    {
        System.out.println(ids);
        chargerMapper.deleteBatchIds(ids);
        Map<String,Object> map=new HashMap<>();
        map.put("success",true);
        map.put("msg","批量删除成功");
        return map;
    }

    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Object remove(@PathVariable("id") Long id)
    {
        chargerMapper.deleteById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("success",true);
        map.put("msg","删除成功");
        return map;
    }
}
