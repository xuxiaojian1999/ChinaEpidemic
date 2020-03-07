import Vue from 'vue/dist/vue.js'
//登陆组件
import login from '@/main/resources/templates/back_end/login.vue'
//账号管理组件
import admin from '@/main/resources/templates/back_end/admin.vue'
//更新疫情数据组件
import provincelist from '@/main/resources/templates/back_end/province_list.vue'
//更新实时播报组件
import broadcastlist from '@/main/resources/templates/back_end/broadcast_list.vue'
//导入样式
import 'bootstrap/dist/css/bootstrap.css'
import 'jquery/dist/jquery.js'
import 'bootstrap/dist/js/bootstrap.js'
// 启用路由
Vue.use(VueRouter)
//定义路由对象
var router=new VueRouter({
    routes:[
        {path:'/login',component:login},
        {path:'/admin',component:admin},
        {path:'/provinceList',component:provincelist},
        {path:'/broadcastList',component:broadcastlist},
        {path:'/',redirect:'/login'}
    ]
})
var vm =new Vue({
    el:'root',
    data:{

    },
    methods:{

    },
    components:{login,admin,provincelist,broadcastlist},
    router:router
})