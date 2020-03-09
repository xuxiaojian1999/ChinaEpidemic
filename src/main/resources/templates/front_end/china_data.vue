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
import totalList from '@/test/resources/static/test/total-list.js'
//导入Dao
import dao from '@/main/resources/static/js/dao/province.js'
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
        }
        
    },
    components:{
        detaildata,chinamap,provincelist
    },
    beforeMount(){
        //初始化省份疫情数据
        this.provinceList=dao.getProvinceList()
        //初始化中国疫情数据
        this.chinaData=dao.getChinaData()
        //计算现存确诊人数
        this.counterConfirm()
        //设置confirmlis
        this.setConfirmList()
    },
    mounted(){
       
    }
}

</script>
<style>
.chinaMap{
    padding-left: 15%;
}
</style>