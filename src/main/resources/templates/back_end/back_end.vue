<template>
  <div>
    <div class="row" > 
        <router-link to="/provinceList" tag="span" class="col myNav"> <div class=" bg-info text-center border border-dark rounded">疫情数据</div></router-link>
        <router-link to="/broadcastList" tag="span" class="col myNav"> <div class=" bg-info text-center border border-dark rounded">实时播报</div></router-link>
        <router-link to="/admin" tag="span" class="col myNav"><div class="bg-info text-center border border-dark rounded"> 管理账号</div></router-link>
    </div>
    <transition 
            enter-active-class="animated bounceInDown"
            leave-active-class="animated bounceOut"
            :duration="{enter:500,leave:1000}"
            mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
//疫情数据组件
import provincelist from '@/main/resources/templates/back_end/province_list.vue'
// 更新播报组件
import broadcastlist from '@/main/resources/templates/back_end/broadcast_list.vue'
//账号管理组件
import admin from '@/main/resources/templates/back_end/admin.vue'

import Vue from 'vue/dist/vue.js'
// 导入路由
import VueRouter from 'vue-router/dist/vue-router.js'
// 启用路由
Vue.use(VueRouter)
// 定义路由对象
let router=new VueRouter({
    routes:[
        {path:'/admin',component:admin},
        {path:'/provinceList',component:provincelist},
        {path:'/broadcastList',component:broadcastlist},
        {path:'/',redirect:'/provinceList'}
    ]
})
export default {
  data(){
    return{

    }
  },
  methods:{

  },
  mounted(){
  },
  router,
  components:{provincelist,broadcastlist,admin}
}

</script>
<style scoped>
.myNav{
  margin: 5px 20px;
   cursor:pointer 
}
</style>