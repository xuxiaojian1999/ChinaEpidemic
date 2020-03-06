//疫情数据
<template>
  <div>
    <div class="row " v-cloak>
      <div class="col" v-if="this.local.country==null" >
        <div  v-if="this.local.city==null">
            <div class="display-4" v-text="this.local.prevince">省份</div> 
        </div> 
        <div  v-else>
            <div class="" v-text="this.local.prevince">省份</div>
            <div class="font-weight-bold lead" v-text="this.local.city">城市</div> 
        </div> 
      </div>
      <div class="col" v-else>
        <div>
            <div class="display-4" v-text="this.local.country">国家</div> 
        </div> 
      </div>
        <div class=" col">
            <div class=""><small>较昨日</small>{{this.incrData.confirm}}</div>
            <div class="lead text-danger" v-text="this.totalData.confirm"></div> 
            <div class="font-weight-bold small">现存确诊</div>
        </div> 
        <div class=" col">
            <div class=""><small>较昨日</small>{{this.incrData.suspect}}</div>
            <div class="lead text-warning"   v-text="this.totalData.suspect"></div> 
            <div class="font-weight-bold small">现存疑似</div>
        </div>
         <div class=" col">
            <div class=""><small>较昨日</small>{{this.incrData.totalConfirm}}</div>
            <div class="lead" style="color:rgb(218, 70, 50)" v-text="this.totalData.totalConfirm"></div> 
            <div class="font-weight-bold small">累计确诊</div>
        </div>
         <div class=" col" >
            <div class=""><small>较昨日</small>{{this.incrData.heal}}</div>
            <div class="lead text-success"  v-text="this.totalData.heal"></div> 
            <div class="font-weight-bold small">累计治愈</div>
        </div>
         <div class=" col">
            <div class=""><small>较昨日</small>{{this.incrData.dead}}</div>
            <div class="lead text-muted"  v-text="this.totalData.dead"></div> 
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
      local:{
        prevince:"省份",
        city:"城市",
        country:null
      },
      //全部数据
      totalData:{
        totalConfirm:'累计确诊',
        //需要经过计算得到
        confirm:'现存确诊',
        suspect:'现存疑似',
        dead:'累计死亡',
        heal:'累计治愈'
      },
      //新增数据
      incrData:{
        totalConfirm:'+1',
        //需要经过计算得到
        confirm:'+1',
        suspect:'+1',
        dead:'+1',
        heal:'+1'
      }
    }
  }
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