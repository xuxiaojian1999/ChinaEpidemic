import Vue from 'vue/dist/vue.js'
import VueRouter from 'vue-router'
// 实时播报组件
import broadcastlist from '@/main/resources/templates/front_end/broadcast_list.vue'
//中国疫情数据组件
import chinadata from '@/main/resources/templates/front_end/china_data.vue'
//单个省份数据组件
import provincedata from '@/main/resources/templates/front_end/province_data.vue'
//导入样式
import 'bootstrap/dist/css/bootstrap.css'
import 'jquery/dist/jquery.js'
import 'bootstrap/dist/js/bootstrap.js'
//引入自定义css
import '@/main/resources/static/css/front_end.css'
//导入animate.css
import 'animate.css'
// 启用路由
Vue.use(VueRouter)
//定义路由对象
var router=new VueRouter({
    routes:[
        {path:'/broadcast',component:broadcastlist},
        {path:'/chinaData',component:chinadata},
        {path:'/provinceData',component:provincedata},
        {path:'/',redirect:'/chinaData'},
        {path:'*',redirect:'/'}
    ]
})
//导入axios
import axios from 'axios'
//设置根域名
axios.defaults.baseURL='/ChinaEpidemic'
//导入vue-axios
import VueAxios from 'vue-axios'
Vue.use(VueAxios,axios)
var vm=new Vue({
    el:"#chinaData",
    data:{
        
    },
    methods:{},
    components:{
        broadcastlist,chinadata,provincedata
    },
    router
    
})