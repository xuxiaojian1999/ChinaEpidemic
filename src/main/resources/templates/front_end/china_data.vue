//全国疫情
//需要传入很多数据
<template>
  <div>
      <detaildata :local="local" :totalData="totalData" :todayData="todayData"></detaildata>
      <chinamap class="chinaMap" :dataList="comfirmList"></chinamap>
      <provincclist :provinceList="provinceList"></provincclist>
  </div>
</template>

<script>
import detaildata from '@/main/resources/templates/front_end/detail_data.vue'
import chinamap from '@/main/resources/templates/front_end/china_map.vue'
import provincclist from '@/main/resources/templates/front_end/province_list.vue'
import totalList from '@/test/resources/static/test/total-list.js'

export default {
    data(){
        return{
        //中国疫情数据（传入detail_data中的数据）
        // ！！！！！！！！需要读取！！！！！！！！
        // 每日中国新增数据 
        // Key:china_data:日期
        // 中国数据
        // Key:china_data
        local:{
            province:null,
            city:null,
            country:'中国'
        },
        //全部数据
        totalData:{
            //现存确诊需要经过计算得到
            confirm:'累计确诊',
            suspect:'现存疑似',
            dead:'累计死亡',
            heal:'累计治愈'
        },
        //新增数据
        todayData:{
            totalConfirm:'+1',
            //需要经过计算得到
            confirm:'+1',
            suspect:'+1',
            dead:'+1',
            heal:'+1'
        },
        //传入china_map中的值
        //!!!!!!!!!!!!!!需要读取
        // 各省数据
        // Key：province_data:省份名称
        //数据
        comfirmList:[
            {name:"南海诸岛",value: this.randomValue()},
            {name: '北京', value: this.randomValue()},
            {name: '天津', value: this.randomValue()},
            {name: '上海', value: this.randomValue()},
            {name: '重庆', value: this.randomValue()},
            {name: '河北', value: this.randomValue()},
            {name: '河南', value: this.randomValue()},
            {name: '云南', value: this.randomValue()},
            {name: '辽宁', value: this.randomValue()},
            {name: '黑龙江', value: this.randomValue()},
            {name: '湖南', value: this.randomValue()},
            {name: '安徽', value: this.randomValue()},
            {name: '山东', value: this.randomValue()},
            {name: '新疆', value: this.randomValue()},
            {name: '江苏', value: this.randomValue()},
            {name: '浙江', value: this.randomValue()},
            {name: '江西', value: this.randomValue()},
            {name: '湖北', value: this.randomValue()},
            {name: '广西', value: this.randomValue()},
            {name: '甘肃', value: this.randomValue()},
            {name: '山西', value: this.randomValue()},
            {name: '内蒙古', value: this.randomValue()},
            {name: '陕西', value: this.randomValue()},
            {name: '吉林', value: this.randomValue()},
            {name: '福建', value: this.randomValue()},
            {name: '贵州', value: this.randomValue()},
            {name: '广东', value: this.randomValue()},
            {name: '青海', value: this.randomValue()},
            {name: '西藏', value: this.randomValue()},
            {name: '四川', value: this.randomValue()},
            {name: '宁夏', value: this.randomValue()},
            {name: '海南', value: this.randomValue()},
            {name: '台湾', value: this.randomValue()},
            {name: '香港', value: this.randomValue()},
            {name: '澳门', value: this.randomValue()}
        ],
        //中国各省份的数据
        provinceList:[]
        }
    },
    methods:{
        //随机数0-1000
        randomValue() {
            return Math.round(Math.random()*100000);
        },
        //获取中国各省份的数据
        // !!!!!!!!!!!需要读取
        // 各省数据
        //Key：province_data:省份名称
        //目前使用.js文件中的数据代替
        getProvinceList(){
            var provinceList=totalList.data.areaTree[0].children
            provinceList.forEach(element => {
                var item={
                    province:element.name,
                totalConfirm:element.total.confirm,
                suspect:element.total.suspect,
                heal:element.total.heal,
                dead:element.total.dead}
                this.provinceList.push(item)
            });
        }
    },
    components:{
        detaildata,chinamap,provincclist
    },
    beforeMount(){
        this.getProvinceList()
    }
}

</script>
<style>
.chinaMap{
    padding-left: 15%;
}
</style>