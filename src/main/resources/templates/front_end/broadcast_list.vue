// 事实播报
<template>
  <div>
  
      <h1 >疫情追踪</h1>
    <div class="broadcastContent">
      <div class="line"></div>
      <div class="broadcastList" >
          <div class="broadcastListItem" v-for="(i,index) in broadcastList" :key="index">
            <div class="time font-weight-light">
                {{i.releaseTime}}
            </div>
            <dl class="content">
                <dt class="title">{{i.title}}</dt>
                <dd class="disgest">{{i.disgest}}</dd>
            </dl>
            <div class="source text-right"><span>来源</span>：{{i.source}}</div>
          </div>
          <div>
            <p class="lazyDown text-center">加载更多····</p>
          </div>
      </div>
    </div>
  </div>
</template>
// 懒加载的实现
// 请求后端服务器后返回数据，添加到data.broadcastList中
<script>
import Vue from 'vue/dist/vue.js'
//实时播报数据
import broadcastList from '@/test/resources/static/test/broadcast-list.js'
export default {
    name:"broadcast",
    data(){
      return{
        broadcastList:[
          {releaseTime:new Date(),disgest:"详情详情详情详情详情详情详情详aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssss情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssaaaaa详情详情详情详情详情详情详情详情详情详情详情详情详情详情",title:"标题",source:"来源"},
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
h1{
  /* display: none; */
  width: 100%;
  height: 50px;
  font-size: 50px;
  padding: 0 10px;
  line-height: 50px;
  text-shadow: black 4px 4px 8px;
}
/* 播报总体 */
.broadcastContent{
  display: flex;
  height:100%;
  width: 100%;
}
/* 播报列表 */
/* 竖线 */
.broadcastContent .line{
  width:2px;
  background-color:rgb(194, 194, 194);
  margin:0 31px;
  padding: 0 1px;
  
}
/* ！！！！ 调整播报 */
.broadcastList{
  flex: 1;
}
.broadcastList .broadcastListItem{
  border-radius: 5px;
  background-color: #aebfd4;
  margin: 10px 2px;
  padding: 8px 10px;
  width: 100%;
  position: relative;
}
/* 三角形 */
.broadcastList .broadcastListItem::before{
  content: "";
  width: 0;
  height:0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 10px solid transparent;
  border-right: 10px solid #aebfd4;
  position: absolute;
  top: 0px;
  left: -17px;
}
.broadcastList .broadcastListItem::after{
  content: " ";
  width: 12px;
  height: 12px;
  border: 3px solid red;
  border-radius: 9px;
  position: absolute;
  top: 5px;
  left: -40px;
}
/* 列表内容 */
/* 时间 */
.time{
  font-size: 5px;
}
/* 内容 */
/* .content{
} */
/*标题 */
.title{
  font-size: 30px;
   width: 100%;
}
/* 细节 */
.disgest{
  font-size: 10px;
  width: 100%;
} 
/* 来源 */
.source{
   font-weight:bold;
  font-size: 3px;
  color: rgb(36, 138, 75);
}
.source span{
  font-weight: 100;
  font-size: 1px;
}
/* 懒加载 */
.lazyDown{
  background-color: #aebfd4;
  border-radius: 5px;
   cursor:pointer ;
}
</style>