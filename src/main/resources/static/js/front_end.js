import Vue from 'vue/dist/vue.js'
import VueRouter from 'vue-router'
import broadcastlist from '@/main/resources/templates/front_end/broadcast_list.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'jquery/dist/jquery.js'
import 'bootstrap/dist/js/bootstrap.js'
//引入自定义css
import '@/main/resources/static/css/front_end.css'
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
var vm=new Vue({
    el:"#chinaData",
    data:{},
    methods:{},
    components:{
        broadcastlist
    },
    router:router,
    
})