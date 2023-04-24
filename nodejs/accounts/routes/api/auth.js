var express = require('express');
var router = express.Router();
const jwt = require('jsonwebtoken')
//导入 用户的模型
const UserModel = require('../../models/UserModel');
const md5 = require('md5');
let {secrt} = require('../../config/config')
const { model } = require('mongoose');

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
        res.json({
            code:'2001',
            msg:'数据库读取失败～～',
            data:null
        })
    }).then(data=>{
        if(!data) return res.json({
            code: '2002',
            msg: '用户名或密码错误~~~',
            data: null
          });
        // req.session.username=data.username;
        // req.session._id=data._id;
        let token = jwt.sign({
            username:data.username,
            _id:data._id
        },secrt,{
            expiresIn:60*60*24*3
        })
        res.json({
            code:'0000',
            msg:'登录成功',
            data:token
        })
    })
});

router.post('/logout',(req,res)=>{
    req.session.destroy(()=>{
        res.render('success',{msg:'退出成功',url:'/login'});
    })
});
module.exports=router;