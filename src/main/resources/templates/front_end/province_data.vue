// 单个省份的详细数据
<template>
  <div>
      <location @setProvince="setProvince" style="width:100%"></location>
      <detaildata :local="showList.local" :totalData="showList.totalData" :todayData="showList.todayData"></detaildata>
        <div v-for="i in showList.city" :key="i.local.city">
             <detaildata :local="i.local" :totalData="i.totalData" :todayData="i.todayData"></detaildata>
        </div>
  </div>
</template>
<script>
//导入疫情数据模板组件
import detaildata from '@/main/resources/templates/front_end/detail_data.vue'
//定位组件
import location from '@/main/resources/templates/front_end/location.vue'
//导入Dao
import dao from '@/main/resources/static/js/dao/province.js'
//导入localS
import localS from '@/main/resources/static/js/localStorage.js'
export default {
    data(){
        return{
            list:[],
            showList:{},
            province:'',
            city:''
        }
    },
    methods:{
        // 根据路由传递过来的参数，获取单个省份的数据
        setShowList(local){
            this.showList={}
            if(local=='')local='湖北'
            // console.log(this.list)
            this.list.some(item=>{
                if(item.local.province==local){
                    var cityList=[]
                    //循环城市数据
                    item.city.forEach(element => {
                        var cityItem={
                            local:element.local,
                            todayData:element.todayData,
                            totalData:element.totalData
                        }
                        cityList.push(cityItem)
                    });
                    //获取省份数据
                    var provinceList={
                        local:item.local,                        
                        todayData:item.todayData,
                        totalData:item.totalData,
                        city:cityList
                    }
                    this.showList=provinceList
                    return true
                    }
            })
        },
        //设置province，给location组件提供一个返回定位省份的方法
        //用来查询省份数据
        setProvince(province){
            if(province!='')this.province=province
        }
    },
    mounted(){
        //初始化list（全部数据）
        //调用localS中的方法
        //在localstorage中进行查询，查看“allProvinceList”是否存在
        //参数：查询的名称 查询方法
        this.list=localS.accessLocalStorage('allProvinceList',dao.getList)
        //初始化showList
        this.setShowList(this.province)
    },
    watch:{
        province:function(){
            this.setShowList(this.province)
        }
    },
    components:{detaildata,location}
}

</script>
<style>
</style>