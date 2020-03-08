<template>
  <div>
    <div class="row" > 
        <!-- <router-link to="/provinceList" tag="span" class="col"> <div class="myNavText">疫情数据</div></router-link>
        <router-link to="/broadcastList" tag="span" class="col"> <div class="myNavText">实时播报</div></router-link>
        <router-link to="/admin" tag="span" class="col"><div class=" myNavText"> 管理账号</div></router-link> -->
    </div>
  </div>
</template>

<script>
//疫情数据组件
import provincelist from '@/main/resources/templates/back_end/province_list.vue'
// 更新播报组件
import broadcastlist from '@/main/resources/templates/back_end/broadcast_list.vue'
//账号管理组件
import admin from '@/main/resources/templates/back_end/admin.vue'
// 导入路由
// import VueRouter from 'vue-router'
//定义路由对象
// var router=new VueRouter({
//     routes:[
//         {path:'/admin',component:admin},
//         {path:'/provinceList',component:provincelist},
//         {path:'/broadcastList',component:broadcastlist},
//         {path:'/',redirect:'/provinceList'}
//     ]
// })
export default {
  data(){
    return{

    }
  },
  methods:{

  },
  mounted(){
      
  },
  // router:{router},
  components:{provincelist,broadcastlist,admin}
}

</script>
<style scoped>
.row{
  height: 100px;
  width: 100px;
  background-color: black;
}
</style>