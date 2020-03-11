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
import localS from '@/main/resources/static/js/localStorage.js'
//导入axios
import axios from 'axios'
//设置根域名
axios.defaults.baseURL='/ChinaEpidemic'
//导入vue-axios
import VueAxios from 'vue-axios'
Vue.use(VueAxios,axios)
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
        //给login组件调用的方法
        //登陆信息进行保存
        loginBack(user){
            //登陆成功user不为null
            if(user!=null){
                //登陆成功
                this.user.account=user.account
                this.user.name=user.name
                this.user.checkCode=user.checkCode
                this.user.identity=user.identity
                //对登陆标志进行修改
                this.setLoginFlag(true)
                //在back_end.vue中登陆成功后添加到localStorage中
                //存储到localStorage中，时间为一天
                localS.setToLocalStorage('backEndUser',user,1)
            }else{
                this.setLoginFlag(false)
            }
        },
        // !!!!!!!!需要读取！！！！！！
        // 用户信息数据
        // Key:users
        //对checkCode和identity进行校验，实现免登陆
        checkFromdb(account,identity,checkCode){
            //对比数据库中的checkCode和identity是否一致
            //异步请求
            axios.post('/user/check', {
                //账号，身份，随机码
                    account: account,
                    identity:identity,
                    checkCode:checkCode
                })
            .then(//请求成功处理
                response => {
                    //登陆成功返回一个user对象（name,identity,checkCode,account）
                    //返回名称和从后端传递过来的随机数
                    //Identity 1:系统管理员 2：工作人员 
                    this.user=response
                    //当user.name不为空时，校验成功，使登陆
                    //设置loginFlag
                    if(this.user.name!=null&&this.user.name!='')this.setLoginFlag(true)
                })
            .catch(function (error) { // 请求失败处理
                    console.log(error)
                })
        },
        //设置loginFlag
        //传递给back_end子组件注销时调用
        setLoginFlag(flag){
            this.loginFlage=flag
        }
    },
    beforeMount(){
        //在back_end.vue中登陆成功后添加到localStorage中
        // 判断localstorage中的backEndUser是否为空
        var user=localS.getFromLocalStorage('backEndUser')
        //需要对从localStorage中取出来的user进行校验
        if(user!=null){
            //在数据库中校验身份和校验码
            this.checkFromdb(user.account,user.identity,user.checkCode)
        }
    },
    components:{login,backend}
})