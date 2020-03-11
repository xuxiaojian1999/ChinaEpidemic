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
//导入localS
import localS from '@/main/resources/static/js/localStorage.js'
export default {
    data(){
        return{
            //全部省份城市的数据
            list:[],
            //展示的数据
            showList:{},
            //当前的定位
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
        //设置province，给location子组件提供一个返回定位省份的方法
        //用来查询省份数据
        setProvince(province){
            if(province!='')this.province=province
        },
         // ！！！！！需要读取！！！！！！！！！！！！！
        // 各省数据
        // Key：province_data:省份名称
        // 各城市数据
        //Key：city_data:省份名称:城市名称
        // 每日城市新增数据
        // Key：city_data:日期:省份名称:城市名称
        // 每日省新增数据
        // Key：province_data:日期:省份名称
        //这里的每日最新是指前一天
        //获取各省份数据（包括城市）用于前台展示
        getList(){
            //异步请求
            axios
            .get('/province/getList')
            .then(response => {
                //返回值
                //provinceItem={
            //     local:{
            //         province:item.name,
            //          city:null,
            //          country:null
            //      },                        
            //      todayData:item.today,
            //      totalData:item.total,
            //      lastUpdateTime:item.lastUpdateTime,
            //      city:cityList
            //    }
            //  citylist=[  数组*{
                    //     local:{
                    //         province:item.name,
                    //         city:i.name,
                    //         country:null
                    //     },
                    //     todayData:i.today,
                    //     totalData:i.total,
                    //     lastUpdateTime:i.lastUpdateTime
                    // }]
                this.list = response,//查询后传入localStorage
                localS.setToLocalStorage("allProvinceList",this.list,1)
                return 
             })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        }
    },
    mounted(){
        //初始化list（全部数据）
        //调用localS中的方法
        //在localstorage中进行查询，查看“allProvinceList”是否存在
        //参数：查询的名称 
        var data=localS.accessLocalStorage('allProvinceList')
        if(data!=null){
            //返回结果不为空
            this.list=data
            //初始化showList
            this.setShowList(this.province)
        }else{
            //返回结果为null.
            //从数据库中查询，
            //查询后会触发list的watch然后向localstorage中进行存储
            //并且更新showlist
            this.getList()
        }
        
    },
    watch:{
        province:function(){
            //更新showlist
            this.setShowList(this.province)
        },
        list:function(){
            //list更新时，就是使用getlist从数据库重新获取得到的
            //更新showlist
             this.setShowList(this.province)
            
        }
    },
    components:{detaildata,location}
}
</script>
<style>
</style>