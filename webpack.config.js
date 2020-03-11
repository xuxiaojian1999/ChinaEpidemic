const path=require('path')
const webpack=require("webpack")
const VueLoaderPlugin = require('vue-loader/lib/plugin');
//网页热点配置
const HtmlWebpackPlugin= require('html-webpack-plugin')
//htmlplugin对象

const htmlPlugin=new HtmlWebpackPlugin({
    // 入口html文件路径
    template:path.join(__dirname,'./src/main/resources/static/html/front_end.html'),
    // template:path.join(__dirname,'./src/main/resources/static/html/back_end.html'),
    filename:'index.html'
    })
module.exports={
    //入口,要打包的文件
    //要打包的文件不存在，会报错
    //约定，默认的打包文件入口是src->index.js
    // entry:path.join(__dirname,'./src/main/resources/static/js/back_end.js'),
    entry:path.join(__dirname,'./src/main/resources/static/js/front_end.js'),
    output:{
        //输出文件的相关配置
        //默认打包文件出口是dist->main.js
    path:path.join(__dirname,'./src/main/resources/static/dist'),//打包输出目录
    filename:'main.js',
    //输出文件的名称
    //输出目录必须存在，文件名称可以随便取
    //设置模式
   // mode:'development'//development production
    //当属性值为production时，会自动压缩打包的文件
    },
     resolve:{
        //给src取别名
        alias:{
            '@':path.join(__dirname,'./src')
        }
    } ,
    module:{
        //匹配规则
        rules:[
            //处理css文件的规则
            {test:/\.css$/,use:['style-loader','css-loader']},
            {test:/\.js|jsx$/,use:'babel-loader',exclude:/node_modules/},
            { test: /\.vue$/, use: "vue-loader" }
            // {test:/\.json$/,use:'url-loader'}
        ]
    },
    //插件、
    plugins:[
        // 网页热配置
        htmlPlugin,
        //vue文件处理插件
        new VueLoaderPlugin()
    ]
}