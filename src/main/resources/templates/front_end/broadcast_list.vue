// 事实播报
//需要传入broadcastList
<template>
  <div>
  
      <h1 >实时播报</h1>
    <div class="broadcastContent">
      <div class="line"></div>
      <div class="broadcastList" >
          <div class="broadcastListItem" v-for="(i,index) in showList" :key="index">
            <div class="time font-weight-light">
                {{i.releaseTime}}
            </div>
            <dl class="content">
                <dt class="title">{{i.title}}</dt>
                <dd class="digest">{{i.digest}}</dd>
            </dl>
            <div class="source text-right"><span>来源</span>：{{i.source}}</div>
          </div>
          <div>
            <p class="lazyDown text-center" @click="pushToShowList" v-text="lazyTip"></p>
          </div>
      </div>
    </div>
  </div>
</template>
// 懒加载的实现
// 请求后端服务器后返回数据，添加到data.broadcastList中
<script>

//导入broadcastDao
import dao from '@/main/resources/static/js/dao/broadcast.js'
//导入localS
import localS from '@/main/resources/static/js/localStorage.js'
export default {
    name:"broadcast",
    data(){
      return{
        broadcastList:[],
        showList:[],
        //broadcastList加入到showList中的一个索引
        index:0,
        lazyTip:'加载更多····'
      }
    },
    //除了methods都是方法
    methods:{

        //设置展示的列表信息
        pushToShowList(){
          for(var i=0;i<10;i++){
            if(this.broadcastList[this.index]==null){
              this.lazyTip="已经是全部了！"
              break
            }
              this.showList.push(this.broadcastList[this.index])
              delete this.broadcastList[this.index]
              this.index++
          }
        }
    },
    beforeMount(){
      //调用localS中的方法
      //在localstorage中进行查询，查看“broadcastList”是否存在
      //参数：查询的名称 查询方法
       this.broadcastList=localS.accessLocalStorage("broadcastList",dao.getBroadcastList)
       this.pushToShowList()
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
  background-color: #edfff2;
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
  border-right: 10px solid #edfff2;
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
  height: 13px;
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
.digest{
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
/* 伪懒加载 */
.lazyDown{
  background-color: #edfff2;
  border-radius: 5px;
   cursor:pointer ;
}
</style>