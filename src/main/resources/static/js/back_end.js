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
// 导入路由
// import VueRouter from 'vue-router'
// //启用路由
// Vue.use(VueRouter)
var vm =new Vue({
    el:'#root',
    data:{
        account:'',
        name:'',
        //判断是否登陆，login组件是否显示
        loginFlage:false
    },
    methods:{
        //对登陆标志进行判断
        login(flag){
            console.log(flag)
            if(flag){
                //存储到localStorage中，时间为一天
                localStorage.setExpire('backEndAccount',{account:this.account,name:this.name},1)
                this.loginFlage=true
            }else{
            }   
        },
        //给login组件调用的方法
        //登陆信息进行保存
        loginBack(account,name,flag){
            this.account=account
            this.name=name
            this.login(flag)
        }
    },
    beforeMount(){
         // 清除账号
        // localStorage.removeItem('backEndAccount')
        // 判断localstorage中的account是否为空
        var account=localStorage.getExpire('backEndAccount')
        if(account!=null){
            this.account=account.account
            this.name=account.name
            this.loginFlage=true
        }
    },
    components:{login,backend}
})