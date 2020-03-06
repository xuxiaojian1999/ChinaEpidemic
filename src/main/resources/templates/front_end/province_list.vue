//各个省份列表
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
      <tr v-for="i in previnceList" :key="i.region">
        <td>{{i.region}}</td>
        <td>{{i.totalConfirm-i.heal-i.dead}}</td>
        <td>{{i.totalConfirm}}</td>
        <td>{{i.suspect}}</td>
        <td>{{i.heal}}</td>
        <td>{{i.dead}}</td>
        <td><a :href="i.region">详情</a></td>
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
  props:['previnceList'],
  mounted(){
    console.log(this.previnceList)
  }
}
</script>
<style scoped>
.myTabel{
  width: 100%;
}
</style>