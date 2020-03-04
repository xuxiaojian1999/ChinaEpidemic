import Vue from 'vue/dist/vue.js'
import totalList from '@/resources/static/test/total-list.js'
import "./requireDataList.css"
import broadcastList from './broadcast-list.js.js'
import ChinaMap from '@/resources/static/test/map.vue'

//中国的全部数据
var chinaList=totalList.data.areaTree[0]
//各个省份的数据
var provinceList=chinaList.children
console.log(provinceList)
//将各个省份的数据转换为更加直观的对象数组
var provinceObj={
    //模板
    // "广东省":{
    //     confirm:0,
    //     suspect:0,
    //     heal:0,
    //     deal:0,
    //     lastUpdateTime:new Date()
    // }
}
provinceList.forEach((item,index) => {
    //循环获取每个省份的数据
    provinceObj[item.name]={
        //用0代替item中的null
        confirm:item.total.confirm?item.total.confirm:0,
        suspect:item.total.suspect?item.total.suspect:0,
        heal:item.total.heal?item.total.heal:0,
        dead:item.total.dead?item.total.dead:0,
        lastUpdateTime:item.lastUpdateTime
    }
});

console.log(provinceObj)


console.log("-------------")
// console.log(broadcastList)


var vm=new Vue({
    el:'#app',
    data:{
        provinceObj:provinceObj
    },
    methods:{

    },
    components:{
        chinamap:ChinaMap
    }
})