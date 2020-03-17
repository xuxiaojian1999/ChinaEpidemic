//疫情数据模板
<template>
  <div>
    <div class="row " v-cloak v-if="local!=undefined&&totalData!=null&&todayData!=null">
      <!-- 对local进行一个判断 -->
      <!-- 有国家的优先显示国家 -->
      <!-- 无国家的，只有省份的优先显示省份，有城市的显示省份和城市 -->
      <div class="col" v-if="local.country==undefined||local.country==null||local.country==''">
        <div  v-if="local.city==null">
            <div class="display-4" v-text="local.province">省份</div> 
        </div> 
        <div  v-else>
            <div class="" v-text="local.province">省份</div>
            <div class="font-weight-bold lead" v-text="local.city">城市</div> 
        </div> 
      </div>
      
      <div class="col" v-else-if="local.country!=null">
        <div>
            <div class="display-4" v-text="local.country">国家</div> 
        </div> 
      </div>
        <div class=" col">
            <div class=""><small>较昨日</small>
                <div class="text-danger">
                   <span v-if="todayData.confirm-todayData.heal-todayData.dead>=0">
                    +
                  </span>
                  {{todayData.confirm-todayData.heal-todayData.dead}}
                </div>
              </div>
            <div class="lead text-danger" >{{totalData.confirm-totalData.heal-totalData.dead}}</div> 
            <div class="font-weight-bold small">现存确诊</div>
        </div> 
        <div class=" col">
            <div class=""><small>较昨日</small>
              <div class="text-warning">
                 <span v-if="todayData.suspect>=0">
                    +
                </span>
                {{todayData.suspect?todayData.suspect:0}}
              </div>
            </div>
            <div class="lead text-warning"   v-text="this.totalData.suspect"></div> 
            <div class="font-weight-bold small">现存疑似</div>
        </div>
         <div class=" col">
            <div class=""><small>较昨日</small>
              <div class="" style="color:rgb(218, 70, 50)">
                 <span v-if="todayData.confirm>=0">
                    +
                </span>
                {{todayData.confirm}}
              </div>  
            </div>
            <div class="lead" style="color:rgb(218, 70, 50)" v-text="totalData.confirm"></div> 
            <div class="font-weight-bold small">累计确诊</div>
        </div>
         <div class=" col" >
            <div class=""><small>较昨日</small>
              <div class="text-success">
                 <span v-if="todayData.heal>=0">
                    +
                </span>
                {{todayData.heal}}
              </div>  
            </div>
            <div class="lead text-success"  v-text="totalData.heal"></div> 
            <div class="font-weight-bold small">累计治愈</div>
        </div>
         <div class=" col">
            <div ><small>较昨日</small>
              <div class="text-muted">
                <span v-if="todayData.dead>=0">
                    +
                </span>
                {{todayData.dead}}
              </div>  
            </div>
            <div class="lead text-muted"  v-text="totalData.dead"></div> 
            <div class="font-weight-bold small">累计死亡</div>
        </div>
  </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      //省份或城市
      // local:{
      //   prevince:"省份",
      //   city:"城市",
      //   country:null
      // },
      // //全部数据
      // totalData:{
      //   totalConfirm:'累计确诊',
      //   //需要经过计算得到
      //   confirm:'现存确诊',
      //   suspect:'现存疑似',
      //   dead:'累计死亡',
      //   heal:'累计治愈'
      // },
      // //新增数据
      // incrData:{
      //   totalConfirm:'+1',
      //   //需要经过计算得到
      //   confirm:'+1',
      //   suspect:'+1',
      //   dead:'+1',
      //   heal:'+1'
      // }
    }
  },
  props:['local','totalData','todayData']
}

</script>
<style scoped>
*{
  text-align: center;
}
.row{
  border: rgb(175, 175, 175) solid 1px;
  border-right: 0px;
  border-radius: 16px;
}
.col{
  border-right: rgb(175, 175, 175) solid 1px;
  border-radius:5px;
  margin: 2px 3px;
}
</style>