//全国疫情
//需要传入很多数据
<template>
  <div>
      <detaildata :local="chinaData.local" :totalData="chinaData.totalData" :todayData="chinaData.todayData"></detaildata>
      <chinamap class="chinaMap" ></chinamap>
      <provincelist :provinceList="provinceList"></provincelist>
  </div>
</template>

<script>
import detaildata from '@/main/resources/templates/front_desk/detail_data.vue'
import chinamap from '@/main/resources/templates/front_desk/china_map.vue'
import provincelist from '@/main/resources/templates/front_desk/province_list.vue'
//导入localS
import localS from '@/main/resources/static/js/localStorage.js'
export default {
    data(){
        return{
        //中国疫情数据（传入detail_data中的数据）
        chinaData:{},
        //中国各省份的数据
        //传入provincelist子组件的数据
        provinceList:[]
        }
    },
    methods:{
        //计算现存确诊人数
        counterConfirm(){
            this.provinceList.forEach(item =>{
                item['partConfirm']= item.totalData.confirm-item.totalData.dead-item.totalData.heal
            })
        },
        //获取中国各省份的数据（不包括城市，只是省份）
        getProvinceList(){
            //异步请求
            this.$axios
            .get('/province/getList?key=provinceList')
            .then(response => {
                //返回值：
                //{
                //     local:{
                //         province:item.name,
                //          city:null,
                //          country:null
                //      },                        
                //      todayData:item.today,
                //      totalData:item.total,
                //      lastUpdateTime:item.lastUpdateTime
                // }
                var result=response.data;
                if(result!=null&&result!=''){
                    //当返回值不为空
                     //将provinceList,存储到localstorage中
                    this.provinceList = result
                    // localS.setToLocalStorage("provinceList",this.provinceList,1)
                   
                }
               
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        },
        //不在mysql中存储
        //获取中国当日的一个总数据
        getChinaData(){
            //异步请求
            this.$axios
            .get('/province/getChinaData')
            .then(response => {
            //返回值：
                //{
                // local:{ 
                //     province:null,
                //     city:null,
                //     country:china.name},
                //totalData:
                // {
                //     //现存确诊需要经过计算得到
                //     confirm:'累计确诊',
                //     suspect:'现存疑似',
                //     dead:'累计死亡',
                //     heal:'累计治愈'
                // },
                //todayData:
                // {
                //     //现存确诊需要经过计算得到
                //     confirm:'+1',
                //     suspect:'+1',
                //     dead:'+1',
                //     heal:'+1'
                // }
            //}
                var result=response.data;
                if(result!=null&&result!=''){
                    //当返回值不为空
                     this.chinaData = result
                }
               
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        }
    },
    components:{
        detaildata,chinamap,provincelist
    },
    created(){
    },
    beforeMount(){
        //初始化省份疫情数据
         this.getProvinceList()
        // this.setConfirmList()
        //初始化中国疫情数据
        this.getChinaData() 
    },
    mounted(){
        
    },
    watch:{
        provinceList:function(){
            //当provinceList改变时
            //计算现存确诊人数
            this.counterConfirm()
             
        }
    }
}
</script>
<style>
.chinaMap{
    padding-left: 15%;
}
</style>