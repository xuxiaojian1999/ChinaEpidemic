//中国疫情地图
//需要传入dataList
<template>
   <div class="echarts">
        <div :style="{height:'400px',width:'100%'}" ref="chinaMap"></div>
    </div>
</template>

<script>
import echarts from 'echarts'
import '@/main/resources/static/js/china.js'
export default
{
    name: "chinaMap",
    data(){
        return {
          //我也不知道干嘛的
            chart:null,
            provinceList:[
            {name:"南海诸岛",value:this.random() },
            {name: '北京', value:this.random()},
            {name: '天津', value:this.random() },
            {name: '上海', value:this.random()},
            {name: '重庆', value:this.random()},
            {name: '河北', value: this.random()},
            {name: '河南', value: this.random()},
            {name: '云南', value: this.random()},
            {name: '辽宁', value: this.random()},
            {name: '黑龙江', value: this.random()},
            {name: '湖南', value: this.random()},
            {name: '安徽', value: this.random()},
            {name: '山东', value: this.random()},
            {name: '新疆', value: this.random()},
            {name: '江苏', value: this.random()},
            {name: '浙江', value: this.random()},
            {name: '江西', value: this.random()},
            {name: '湖北', value: this.random()},
            {name: '广西', value: this.random()},
            {name: '甘肃', value: this.random()},
            {name: '山西', value: this.random()},
            {name: '内蒙古', value: this.random()},
            {name: '陕西', value: this.random()},
            {name: '吉林', value: this.random()},
            {name: '福建', value: this.random()},
            {name: '贵州', value: this.random()},
            {name: '广东', value: this.random()},
            {name: '青海', value: this.random()},
            {name: '西藏', value: this.random()},
            {name: '四川', value: this.random()},
            {name: '宁夏', value: this.random()},
            {name: '海南', value: this.random()},
            {name: '台湾', value: this.random()},
            {name: '香港', value: this.random()},
            {name: '澳门', value:this.random()}
        ]
        }
    },
    created(){
    },
    mounted(){
        //调用方法
        this.chinaConfigure()
    },
    beforeDestory(){
        if(!this.chart){
            return;
        }
        this.chart.dispose()
        this.chart=null
    },
    methods:{
        //随机数
        random() {
            var lower=0
            var upper=10000
            return Math.floor(Math.random() * (upper - lower+1)) + lower;
        },
        //初始化confirmList
        // confirmListInit(name){
        //     this.provinceList.forEach(item=>{
        //         if(name==item.local.province)return item.totalData.confirm
        //     })
        // },
        //中国疫情地图
        chinaConfigure(){
            //echarts对象
            var chinaMap=echarts.init(this.$refs.chinaMap)
            Window.onresize=chinaMap.resize
        //echarts配置
        var option = {
            tooltip: {
                    formatter:function(params,ticket, callback){
                        return "<span >"+params.name+'：'+params.value+'</span>'
                    }//数据格式化
                },
            visualMap: {
                min: 0,
                max: 10000,
                left: 'left',
                top: 'bottom',
                text: ['高','低'],//取值范围的文字
                inRange: {
                    color: ['white', 'red']//取值范围的颜色
                },
                show:true//图注
            },
            geo: {
                map: 'china',
                roam: false,//不开启缩放和平移
                zoom:1.23,//视角缩放比例
                label: {
                    normal: {
                        show: true,
                        fontSize:'10',
                        color: 'rgba(0,0,0,0.7)'
                    }
                },
                itemStyle: {
                    normal:{
                        borderColor: 'rgba(0, 0, 0, 0.2)'
                    },
                    emphasis:{
                        areaColor: '#F3B329',//鼠标选择区域颜色
                        shadowOffsetX: 0,
                        shadowOffsetY: 0,
                        shadowBlur: 20,
                        borderWidth: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            },
            series : [
                {
                    name: '信息量',
                    type: 'map',
                    geoIndex: 0,
                    data:this.provinceList,
                    width:'20px',
                }
            ]
        };
         chinaMap.setOption(option);
         //点击各个省份后触发的事件
        // chinaMap.on('click', function (params) {
        //     alert(params.name);
        // });
        }
        
    }

}
</script>

<style>
.echarts{
    width:100%;
    border:rgb(175, 175, 175) solid 1px;
    border-radius:16px;
    margin: 5px 0;
    padding: 5px 50px;
    overflow: hidden;
}
</style>