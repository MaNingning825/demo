const db = require('../db/db');
const mongoose = require('mongoose');
const BookModel = require('../models/BookModel');
const MovieMOdel = require('../models/MovieModel')
const AccountModel = require('../models/AccountsModel');
const moment = require('moment')
db(() => {
    // BookModel.create({
    //     name: '水浒传',
    //     author: "施耐庵",
    //     price: 19.9
    // }).catch(err => {
    //     console.log(err);
    //     return;
    // }).then(res => {
    //     console.log(res);
    //     // mongoose.disconnect();
    //     // return;
    // });
    // MovieMOdel.create({
    //     title: '新三国演义',
    //     director: '高飞飞'
    // }).catch(err => {
    //     console.log(err);
    //     return;
    // }).then(res => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })
    a={
        title: '1000',
        time: '2023-04-08',
        type: '1',
        account: '10000',
        remarks: '测试'
      }
    AccountModel.create({
        ...a,
        time: moment(a.time).toDate()
      }).catch(err => {
        console.log(err);
        // res.status(500).send("插入失败");
        return;
      }).then(data => {
        console.log(data);
        mongoose.disconnect();
        // res.render('success', { msg: "添加成功哦～～～", url: "/accounts" })
      });
}, () => {
    console.log("连接失败")
})