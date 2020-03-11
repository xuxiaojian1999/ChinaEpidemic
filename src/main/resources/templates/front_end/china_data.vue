//全国疫情
//需要传入很多数据
<template>
  <div>
      <detaildata :local="chinaData.local" :totalData="chinaData.totalData" :todayData="chinaData.todayData"></detaildata>
      <chinamap class="chinaMap" :dataList="comfirmList"></chinamap>
      <provincelist :provinceList="provinceList"></provincelist>
  </div>
</template>

<script>
import detaildata from '@/main/resources/templates/front_end/detail_data.vue'
import chinamap from '@/main/resources/templates/front_end/china_map.vue'
import provincelist from '@/main/resources/templates/front_end/province_list.vue'
//导入localS
import localS from '@/main/resources/static/js/localStorage.js'
export default {
    data(){
        return{
        //中国疫情数据（传入detail_data中的数据）
        chinaData:{},
        //传入china_map中的值
        //数据
        comfirmList:[
            {name:"南海诸岛",value:0 },
            {name: '北京', value:0 },
            {name: '天津', value:0 },
            {name: '上海', value:0},
            {name: '重庆', value:0},
            {name: '河北', value: 0},
            {name: '河南', value: 0},
            {name: '云南', value: 0},
            {name: '辽宁', value: 0},
            {name: '黑龙江', value: 0},
            {name: '湖南', value: 0},
            {name: '安徽', value: 0},
            {name: '山东', value: 0},
            {name: '新疆', value: 0},
            {name: '江苏', value: 0},
            {name: '浙江', value: 0},
            {name: '江西', value: 0},
            {name: '湖北', value: 0},
            {name: '广西', value: 0},
            {name: '甘肃', value: 0},
            {name: '山西', value: 0},
            {name: '内蒙古', value: 0},
            {name: '陕西', value: 0},
            {name: '吉林', value: 0},
            {name: '福建', value: 0},
            {name: '贵州', value: 0},
            {name: '广东', value: 0},
            {name: '青海', value: 0},
            {name: '西藏', value: 0},
            {name: '四川', value: 0},
            {name: '宁夏', value: 0},
            {name: '海南', value: 0},
            {name: '台湾', value: 0},
            {name: '香港', value: 0},
            {name: '澳门', value: 0}
        ],
        //中国各省份的数据
        //传入provincelist子组件的数据
        provinceList:[]
        }
    },
    methods:{
        //计算现存确诊人数
        counterConfirm(){
            this.provinceList.forEach(item =>{
                item['partConfirm']= item.confirm-item.heal-item.dead
            })
        },
        //设置comfirmList的value
        setConfirmList(){
            this.comfirmList.forEach(item =>{
                this.provinceList.some(i =>{
                    if(i.province==item.name){
                        item.value=i.partConfirm
                        return true
                    }
                })
            })
        },
        // !!!!!!!!!!!需要读取!!!!!!!!
        // 各省数据
        //Key：province_data:省份名称
        //获取中国各省份的数据（不包括城市）
        getProvinceList(){
            //异步请求
            axios
            .get('/province/getProvinceList')
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
                if(response!=null){
                    //当返回值不为空
                     //将provinceList,存储到localstorage中
                    localS.setToLocalStorage("provinceList",this.provinceList,1)
                    this.provinceList = response
                }
               
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        },
        // ！！！！！！！！需要读取！！！！！！！！
        // 每日中国新增数据 
        // Key:china_data:日期
        // 中国数据
        // Key:china_data
        //获取中国当日的一个总数据
        getChinaData(){
            //异步请求
            axios
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
                //     totalConfirm:'+1',
                //     //现存确诊需要经过计算得到
                //     confirm:'+1',
                //     suspect:'+1',
                //     dead:'+1',
                //     heal:'+1'
                // }
            //}
                if(response!=null){
                    //当返回值不为空
                     this.chinaData = response
                    //将chinaData,存储到localstorage中
                     localS.setToLocalStorage("chinaData",this.chinaData,1)
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
    beforeMount(){
        //初始化省份疫情数据
        //调用localS中的方法
        //在localstorage中进行查询，查看“provinceList”是否存在
        //参数：查询的名称
        var data=localS.getFromLocalStorage("provinceList")
        if(data!=null){
            //返回结果不为空
            this.provinceList=data
        }else{
            //返回结果为null
            this.getProvinceList()
            //调用该方法后，会重新给this.provinceList赋值，在该方法中会存入到localStorage中
            //然后触发watch
            //计算现存确诊人数，设置confirmlist
        }
        //初始化中国疫情数据
        //调用localS中的方法
        //在localstorage中进行查询，查看“chinaData”是否存在
        //参数：查询的名称 
        data=localS.getFromLocalStorage('chinaData')
        if(data!=null){
            //返回结果不为空
             this.chinaData=data
        }else{
            //返回结果为null
            //调用getChinaData方法，在该方法中会存入到localStorage中
            this.getChinaData()
        }
    },
    watch:{
        provinceList:function(){
            //当provinceList改变时
            //计算现存确诊人数
            this.counterConfirm()
            //设置confirmlist,传入到china_map中的数据
            this.setConfirmList()
        }
    }
}
</script>
<style>
.chinaMap{
    padding-left: 15%;
}
</style>