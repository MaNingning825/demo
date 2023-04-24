const mongoose=require('mongoose')
let UserSchema = mongoose.Schema({
    username:String,
    password:String
});
let UserModel =mongoose.model('users',UserSchema);
module.exports=UserModel;