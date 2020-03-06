import Vue from 'vue/dist/vue.js'
import VueRouter from 'vue-router'
// 实时播报组件
import broadcastlist from '@/main/resources/templates/front_end/broadcast_list.vue'
//中国疫情数据组件
import chinadata from '@/main/resources/templates/front_end/china_data.vue'
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
        {path:'/chinaData',component:chinadata},
        {path:'/',redirect:'/chinaData'}
    ]
})
var vm=new Vue({
    el:"#chinaData",
    data:{
        
    },
    methods:{},
    components:{
        broadcastlist,chinadata
    },
    router:router,
    
})