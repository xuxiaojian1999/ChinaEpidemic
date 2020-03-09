<template>
  <div>
    <!-- 个人信息，身份，注销  props:['user']-->
    <!-- 在admin加一个判断，如果身份不是系统管理员，就显示请输入正确的url -->
    <div class="column w-100 clearfix bg-info text-white rounded">
      <span class="lead">{{this.user.name}}</span>
       <span class="badge badge-danger" v-if="user.identity==1">系统管理员</span> 
       <span class="badge badge-success" v-else>工作人员</span> 
       <button type="button" class="btn btn-sm btn-warning float-right" @click="logout">注销</button>
    </div>
    <!-- 导航栏 -->
    <div class="row" > 
        <router-link to="/provinceList" tag="span" class="col myNav"> <div class=" bg-info text-center border border-dark rounded">疫情数据</div></router-link>
        <router-link to="/broadcastList" tag="span" class="col myNav"> <div class=" bg-info text-center border border-dark rounded">实时播报</div></router-link>
        <router-link v-if="user.identity==1" to="/admin" tag="span" class="col myNav"><div class="bg-info text-center border border-dark rounded"> 管理账号</div></router-link>
    </div>
    <transition 
            enter-active-class="animated bounceInDown"
            leave-active-class="animated bounceOut"
            :duration="{enter:500,leave:1000}"
            mode="out-in">
            <!-- 将用户信息传递给子组件 -->
      <router-view :user='user'></router-view>
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
var router=new VueRouter({
    routes:[
      {path:'/admin',component:admin},
        {path:'/provinceList',component:provincelist},
        {path:'/broadcastList',component:broadcastlist},
        {path:'/',redirect:'/provinceList'},
        {path:'*',redirect:'/provinceList'}]
    })
// let router=
export default {
  data(){
    return{

    }
  },
  methods:{

  },
  methods:{
    //注销
    logout(){
      localStorage.removeItem("backEndUser")
      this.$emit('setloginflag',false)
    }
  },
  mounted(){
   
  },
  router,
  components:{provincelist,broadcastlist,admin},
  props:['user']
}

</script>
<style scoped>
.myNav{
  margin: 5px 20px;
   cursor:pointer 
}
.column{
  padding: 5px 8px;
   margin: 5px 0;
}
</style>