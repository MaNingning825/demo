var express = require('express');
var router = express.Router();
const AccountModel = require('../../models/AccountsModel');
//导入 lowdb
// const low = require('lowdb')
// const FileSync = require('lowdb/adapters/FileSync')
// const adapter = new FileSync(__dirname + '/../data/db.json');
// const db = low(adapter)
const moment = require('moment')
// const shortid = require('shortid');

const checkLoginMiddleware = require('../../middlewares/checkLoginMiddlewares');

/* GET home page. */// 记账本列表
router.get('/', function (req, res, next) {
  // res.render('index', { title: 'Express' });
  res.redirect('/account')
});

//记账本的列表
router.get('/account',checkLoginMiddleware, function (req, res, next) {
  // let accounts = db.get('accounts').value();
  // console.log(accounts)
  AccountModel.find().sort({ time: -1 }).catch(err => {
    console.log(err)
    res.status(500).send("读取失败~");
  }).then(data => {
    res.render('list', { accounts: data,moment:moment });
  })

})

//添加记录
router.get('/account/create',checkLoginMiddleware ,function (req, res, next) {
  res.render('create');
})

//新增记录
router.post('/account',checkLoginMiddleware ,(req, res) => {
  // console.log(req)
  //生成id
  // let id = shortid.generate();
  console.log(req.body)
  AccountModel.create({
    ...req.body,
    time: moment(req.body.time).toDate()
  }).catch(err => {
    console.log(err);
    res.status(500).send("插入失败");
    return;
  }).then(data => {
    console.log(data);
    res.render('success', { msg: "添加成功哦～～～", url: "/account" })
  });
  //获取请求体数据
  // console.log(req.body)
  // db.get('accounts').unshift({id:id,...req.body}).write()

})

//删除数据
router.get('/account/:id',checkLoginMiddleware ,(req, res) => {
  //获取params 的id参数
  let id = req.params.id;
  // db.get('accounts').remove({id:id}).write();
  AccountModel.deleteOne({ _id: id }).catch(err => {
    console.log(err)
    res.status(500).send('删除失败～');
    return;
  }).then(() => {
    res.render('success', { msg: "删除成功～～～", url: "/account" })
  })
});
module.exports = router;
