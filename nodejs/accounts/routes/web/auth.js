var express = require('express');
var router = express.Router();
//导入 用户的模型
const UserModel = require('../../models/UserModel');
const md5 = require('md5');
const { model } = require('mongoose');

router.get('/reg',(req,res)=>{
    res.render('auth/reg');
})

router.get('/login',(req,res)=>{
    res.render('auth/login');
});

router.post('/reg',(req,res)=>{
    UserModel.create({...req.body,password:md5(req.body.password)}).catch(err=>{
        res.status(500).send("注册失败，请重试~~");
    }).then(()=>{
        res.render('success',{msg:'注册成功',url:'/login'});
    })
});

router.post('/login',(req,res)=>{
    let {username,password} = req.body;
    UserModel.findOne({username:username,password:md5(password)}).catch(err=>{
        res.status(500).send("登录失败，请重试~~");
    }).then(data=>{
        if(!data) res.send("账号或密码错误");
        req.session.username=data.username;
        req.session._id=data._id;
        res.render('success',{msg:'登录成功',url:'/account'});
    })
});

router.post('/logout',(req,res)=>{
    req.session.destroy(()=>{
        res.render('success',{msg:'退出成功',url:'/login'});
    })
});
module.exports=router;