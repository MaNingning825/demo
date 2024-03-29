/**
 * 
 * @param {*} success 数据库连接成功的回调
 * @param {*} error 数据库连接失败的回调
 */
module.exports = function(success, error) {
    if (typeof error !== 'function') {
        error = () => {
            console.log('连接失败~~~');
        }
    }
    const mongoose = require('mongoose');
    const { DBHOST, DBPORT, DBNAME } = require('../config/config')
    // console.log(DBHOST)
    mongoose.connect(`mongodb://${DBHOST}:${DBPORT}/${DBNAME}`);
    mongoose.connection.once('open', () => {
        console.log('连接成功')
        success();
    });

    mongoose.connection.on('error', () => {
        error();
    });

    mongoose.connection.on('close', () => {
        console.log("连接关闭");
    });
}
