//各个省份列表
//需要传递previnceList
<template>
  <div>
    <table class="table table-bordered  table-hover myTable">
    <thead>
      <tr>
        <th class="table-info">地区</th>
        <th class="table-danger">现存确诊</th>
        <th class="" style="background-color:rgb(218, 70, 50)">累计确诊</th>
        <th class="table-warning">现存疑似</th>
        <th class="table-success">治愈</th>
        <th class="table-secondary">死亡</th>
        <th class="table-primary">疫情</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="i in provinceList" :key="i.province">
        <td>{{i.province}}</td>
        <td>{{i.confirm}}</td>
        <td>{{i.totalConfirm}}</td>
        <td>{{i.suspect}}</td>
        <td>{{i.heal}}</td>
        <td>{{i.dead}}</td>
        <td><a :href="'#/provinceData?province='+i.province">详情</a></td>
      </tr>
    </tbody>
</table>
  </div>
</template>

<script>

export default {
  data(){
    return{

    }
  },
  methods:{

  },
  props:['provinceList']
}
</script>
<style scoped>
.myTabel{
  width: 100%;
}
</style>