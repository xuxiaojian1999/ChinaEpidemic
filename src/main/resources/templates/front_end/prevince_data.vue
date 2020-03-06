// 单个省份的详细数据
<template>
  <div>
      <detaildata :local="showList.local" :totalData="showList.totalData" :todayData="showList.todayData"></detaildata>
        <div v-for="i in showList.city" :key="i.local.city">
             <detaildata :local="i.local" :totalData="i.totalData" :todayData="i.todayData"></detaildata>
        </div>
  </div>
</template>

<script>
import totalList from '@/test/resources/static/test/total-list.js'
//导入疫情数据模板组件
import detaildata from '@/main/resources/templates/front_end/detail_data.vue'
export default {
    data(){
        return{
            list:[],
            showList:{}
        }
    },
    methods:{
        // ！！！！！需要读取！！！！！！！！！！！！！
        // 根据路由传递过来的参数，获取单个省份的数据
        // 各省数据
        // Key：province_data:省份名称
        // 各城市数据
        //Key：city_data:省份名称:城市名称
        // 每日城市新增数据
        // Key：city_data:日期:省份名称:城市名称
        // 每日省新增数据
        // Key：province_data:日期:省份名称
        // 这里通过获取.js文件中的数据模拟
        getList(){
            //各省详细数据的展示
            this.list=totalList.data.areaTree[0].children;
        },
        setShowList(local){
            this.list.filter(item=>{
                if(item.name==local){
                    var cityList=[]
                    item.children.forEach(element => {
                        var cityItem={
                            local:{
                                prevince:item.name,
                                city:element.name,
                                country:''
                            },
                            todayData:element.today,
                            totalData:element.total
               
                        }
                        cityList.push(cityItem)
                    });
                    var previnceList={
                        local:{
                            prevince:item.name,
                            city:null,
                            country:null
                        },                        
                        todayData:item.today,
                        totalData:item.total,
                        city:cityList
                    }
                    this.showList=previnceList
                    return previnceList
                    }
            })
        }
    },
    mounted(){
        this.getList()
        this.setShowList('湖北')
    },
    components:{detaildata}
}

</script>
<style>
</style>