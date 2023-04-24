var express = require('express');
var router = express.Router();
const moment = require('moment');
const AccountModel = require('../../models/AccountsModel');
let checkTokenMiddlewares = require('../../middlewares/checkTokenMiddlewares')
//记账本的列表
router.get('/account', checkTokenMiddlewares,function (req, res, next) {
  // let accounts = db.get('accounts').value();
  // console.log(accounts)
  AccountModel.find().sort({ time: -1 }).catch(err => {
    console.log(err)
    res.json({
        code:'1001',
        msg:'读取失败～～',
        data:null
    })
  }).then(data => {
    res.json({
        code:'0000',
        msg:'读取成功',
        data:data
    })
  })

})

//新增记录
router.post('/account',checkTokenMiddlewares ,(req, res) => {
  // console.log(req)
  //生成id
  // let id = shortid.generate();
  console.log(req.body)
  AccountModel.create({
    ...req.body,
    time: moment(req.body.time).toDate()
  }).catch(err => {
    console.log(err);
    res.json({
        code:'1002',
        msg:'创建失败～～',
        data:null
    })
    return;
  }).then(data => {
    console.log(data);
    res.json({
        code:'0000',
        msg:'创建成功',
        data:data
    })
  });
})

//删除数据
router.delete('/account/:id',checkTokenMiddlewares ,(req, res) => {
  //获取params 的id参数
  let id = req.params.id;
  // db.get('accounts').remove({id:id}).write();
  AccountModel.deleteOne({ _id: id }).catch(err => {
    console.log(err)
    res.json({
        code:'1003',
        msg:'删除账单失败～～',
        data:null
    })
    return;
  }).then(() => {
    res.json({
        code:'0000',
        msg:'删除成功',
        data:{}
    })
  })
});

//读取单个详细记录
router.get('/account/:id',checkTokenMiddlewares ,(req,res) => {
    let {id} = req.params;
    AccountModel.findById(id).catch((err) => {
        console.log(err);
        res.json({
            code:'1004',
            msg:'读取失败～～',
            data:null
        })
    }).then(data => {
        res.json({
            code:'0000',
            msg:'读取成功',
            data:data
        })
    })
});

//更新单个账单信息
router.patch('/account/:id',checkTokenMiddlewares ,(req,res) => {
    let {id} = req.params;
    AccountModel.updateOne({_id:id},req.body).catch((err) => {
        console.log(err);
        res.json({
            code:'1005',
            msg:'更新失败～～',
            data:null
        })
    })
    AccountModel.findById(id).catch((err) => {
        console.log(err);
        res.json({
            code:'1004',
            msg:'读取失败～～',
            data:null
        })
    }).then(data => {
        res.json({
            code:'0000',
            msg:'更新成功',
            data:data
        })
    })
})
module.exports = router;