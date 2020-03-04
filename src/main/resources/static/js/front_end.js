import Vue from 'vue/dist/vue.js'
import VueRouter from 'vue-router'
import broadcastlist from '@/main/resources/templates/front_end/broadcast_list.vue'
import 'bootstrap/dist/css/bootstrap.css'
// 启用路由
Vue.use(VueRouter)
//定义路由对象
var router=new VueRouter({
    routes:[
        {path:'/broadcast',component:broadcastlist},
        {path:'/chinaData',component:broadcastlist},
        {path:'/',redirect:'/chinaData'}
    ]
})
/*
1.搭建路由
2.调好事实播报组件
*/
var vm=new Vue({
    el:"#chinaData",
    data:{},
    methods:{},
    components:{
        broadcastlist
    },
    router:router,
    
})