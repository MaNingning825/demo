const mongoose = require("mongoose");

//3连接mongodb数据库
//数据库不存在，会自动创建该数据库
mongoose.connect('mongodb://127.0.0.1:27017/admin');
//4设置回调
mongoose.connection.once("open", () => {
    //5创建文档的结构对象
    //设置集合中文档的属性以及属性值的类型
    let BookSchema = new mongoose.Schema({
        name: {
            type: String,
            required: true,//表明该属性必须不为空
            unique: true//设置为独一无二的
        },
        author: {
            type: String,
            default: '匿名'
        },
        style: {
            type: String,
            enum: ['言情', '城市', '志怪', '恐怖']
        },
        price: Number,
        is_hot: Boolean,
        tags: Array,
        pub_time: Date,
        test: mongoose.Schema.Types.Mixed//文档ID
    });

    //6创建模型对象 对文档操作的封装对象
    let BookModel = mongoose.model('books', BookSchema);

    //7新增
    // BookModel.create({
    //     name: '西游记',
    //     author: '吴承恩',
    //     style:'志怪',
    //     price: 19.9,
    //     is_hot: true,
    //     tags: ['鬼怪', '励志', '社会'],
    //     pub_time: new Date(),
    //     test: new Date()
    // }).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // });

    //删除一条
    // BookModel.deleteOne({_id:'6444ebc9246a69e0fd6fc5c2'}).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //批量删除
    // BookModel.deleteMany({is_hot:false}).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //更新一条
    // BookModel.updateOne({_id:'6444ecce3524c9d6cd0f49ea'},{name:'红楼梦',author:'曹雪芹',price:9.9}).then((res) =>{
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //批量更新
    // BookModel.updateMany({author:'余光中'},{is_hot:false}).then((res) =>{
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //读取单条数据
    // BookModel.findOne({name:'西游记'}).then(
    //     (res) =>{
    //         console.log(res);
    //         mongoose.disconnect();
    //     }
    // )

    //根据id更新
    // BookModel.findById('644527ba151bf463215eb967').then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //  批量读取
    // BookModel.find({author:{$regex:'吴'}}).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //  模糊查询 正则表达式
    // BookModel.find({author:/吴/}).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //价格小与10的书
    // BookModel.find({price:{$lt:10}}).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //吴承恩或者曹雪芹的书
    // BookModel.find({$or: [{author:'吴承恩'},{author:'曹雪芹'}]}).then((res) =>{
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //价格大于10小于等于20的书
    // BookModel.find({$and: [{price:{$gt:10}},{price:{$lte:20}}]}).then((res)=>
    // {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //正则表达式
    // BookModel.find({author: new RegExp('芹')}).then((res) => {
    //     console.log(res);
    //     mongoose.disconnect();
    // })

    //字段筛选 0:不要的字段 1:要的字段
    // BookModel.find().select({ _id: 0, name: 1,author: 1 }).then(res => {
    //     console.log(res);
    //     mongoose.disconnect();
    // }).catch(err => {
    //     console.log(err);
    //     mongoose.disconnect();
    // })

    // 数据排序
    //sort 1:升序 -1:倒序
    // BookModel.find().sort({price:1}).then(res=>{
    //     console.log(res)
    //     mongoose.disconnect();
    // }).catch(err=>{
    //     console.log("出错啦～～～");
    //     console.log(err);
    //     return;
    // });

    //数据的截取
    BookModel.find().select({name:1,author:1,price:1}).sort({price:1})
    .skip(2)//跳过
    .limit(4)//限定
    .then(res =>{
        console.log(res);
        mongoose.disconnect();
    })
    
});

mongoose.connection.on("error", function () {
    console.log("数据库连接出错");
})
mongoose.connection.on("close", function () {
    console.log("数据库已断开连接");
})
// mongoose.disconnect();