// 事实播报
<template>
  <div>
      <h1>疫情追踪</h1>
    <div class="broadcastContent">
      <div class="line"></div>
      <div class="broadcastList" v-for="(i,index) in broadcastList" :key="index">
          <div class="broadcastListItem">
            <div class="time">
                {{i.releaseTime}}
            </div>
            <div class="content">
                <div class="title">{{i.title}}</div>
                <div class="disgest">{{i.disgest}}</div>
            </div>
            <div class="source">{{i.source}}</div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue/dist/vue.js'
//实时播报数据
import broadcastList from '@/test/resources/static/test/broadcast-list.js'
export default {
    name:"broadcast",
    data(){
      return{
        broadcastList:[
          {releaseTime:new Date(),disgest:"详情",title:"标题",source:"来源"},
          {releaseTime:new Date(),disgest:"详情",title:"标题",source:"来源"},
          {releaseTime:new Date(),disgest:"详情",title:"标题",source:"来源"},
          {releaseTime:new Date(),disgest:"详情",title:"标题",source:"来源"},
          {releaseTime:new Date(),disgest:"详情",title:"标题",source:"来源"}
        ]
      }
    },
    //除了methods都是方法
    methods:{
        getBroadList(){
           
        }
    },
    beforeMount(){
      // 这里异步请求后端，获取broadcastList
      //目前通过。json文件获取
       console.log(broadcastList)
      
    },
    mounted(){
      //getBroadList()
    }
}

</script>
<style scoped>
.broadcastContent{
  height:800px;
  display:flex;
}
.line{
  width:2px;
  background-color:#ddd;
  margin:0 30px;
}
</style>