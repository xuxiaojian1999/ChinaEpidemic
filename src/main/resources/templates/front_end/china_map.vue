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
        }
    },
      //全国现存确诊数据
    props:['dataList'],
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
                    data:this.dataList,
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