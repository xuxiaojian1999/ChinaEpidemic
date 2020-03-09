import Vue from 'vue/dist/vue.js'
//导入样式
import 'bootstrap/dist/css/bootstrap.css'
import 'jquery/dist/jquery.js'
import 'bootstrap/dist/js/bootstrap.js'
//导入animate.css
import 'animate.css'
//导入自定义样式
import '@/main/resources/static/css/back_end.css'
//登陆组件
import login from '@/main/resources/templates/back_end/login.vue'
//后台管理组件
import backend from '@/main/resources/templates/back_end/back_end.vue'
//导入可设置过期时间的localStorage
import '@/main/resources/static/js/localStorage.js'
//导入Login的dao层
import dao from '@/main/resources/static/js/dao/login.js'
// 导入路由
// import VueRouter from 'vue-router'
// //启用路由
// Vue.use(VueRouter)
var vm =new Vue({
    el:'#root',
    data:{
        //用户信息
        //传递给back_end子组件的数据
        user:{
            account:'',
            name:'',
            identity:'',
            checkCode:''
        },
        //判断是否登陆，flase,login组件显示
        loginFlage:false
    },
    methods:{
        //对登陆标志进行判断
        login(flag){
            if(flag){
                //存储到localStorage中，时间为一天
                localStorage.setExpire('backEndUser',this.user,1)
                this.setLoginFlag(true)
            }else{
            }   
        },
        //给login组件调用的方法
        //登陆信息进行保存
        loginBack(account,name,identity,checkCode,flag){
            this.user.account=account
            this.user.name=name
            this.user.checkCode=checkCode
            this.user.identity=identity
            this.login(flag)
        },
        //设置loginFlag
        //传递给back_end子组件调用
        setLoginFlag(flag){
            this.loginFlage=flag
        }
    },
    beforeMount(){
         // 清除账号
        // localStorage.removeItem('backEndAccount')
        // 判断localstorage中的account是否为空
        var user=localStorage.getExpire('backEndUser')
        //需要对从localStorage中取出来的user进行校验
        //主要校验身份和校验码
        if(user!=null){
            if(dao.checkFromdb(user.identity,user.checkCode)){
                this.user.account=user.account
                this.user.name=user.name
                this.user.checkCode=user.checkCode
                this.user.identity=user.identity
                this.setLoginFlag(true)
            }
        }
    },
    components:{login,backend}
})