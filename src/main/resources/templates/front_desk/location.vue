<template>
  <div>
      <div class="btn-group dropright float-right w-100">
	  <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		{{tip}}{{province}}
	  </button>
	  <div class="dropdown-menu">
		<!-- Dropdown menu links -->
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('广东')">广东</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('湖北')">湖北</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('北京')">北京</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('天津')">天津</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('上海')">上海</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('重庆')">重庆</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('河北')">河北</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('河南')">河南</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('云南')">云南</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('辽宁')">辽宁</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('黑龙江')">黑龙江</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('湖南')">湖南</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('安徽')">安徽</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('山东')">山东</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('新疆')">新疆</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('江苏')">江苏</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('浙江')">浙江</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('江西')">江西</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('吉林')">湖北</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('广西')">广西</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('甘肃')">甘肃</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('山西')">山西</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('内蒙古')">内蒙古</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('陕西')">陕西</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('福建')">福建</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('贵州')">贵州</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('青海')">青海</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('西藏')">西藏</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('四川')">四川</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('宁夏')">宁夏</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('海南')">海南</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('台湾')">台湾</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('香港')">香港</a>
		<a class="dropdown-item" href="#" @click.prevent="changeProvince('澳门')">澳门</a>
        </div>
	</div>
  </div>
</template>

<script>
//装从百度api获得的位置
var local=''
export default {
    data(){
        return{
            province:'湖北',
            city:'',
            tip:'当前定位：'
        }
    },
    methods:{
        //获取当前定位
        getLocation(){
            var geolocation=new BMap.Geolocation();
            geolocation.getCurrentPosition(function(r){
                if(this.getStatus()==BMAP_STATUS_SUCCESS){
                    var lat=r.address.lat//当前经度
                    var lng=r.address.lng//当前纬度
                    var province=r.address.province //返回当前省份
                    var city=r.address.city//返回当前城市
                    local=province.replace("省",'')
                }
            });
        },
        //参数n为休眠时间，单位为毫秒:
            sleep(n) {
                var start = new Date().getTime();
                //  console.log('休眠前：' + start);
                while (true) {
                if (new Date().getTime() - start > n) {
                    break;
                    }
                }
                // console.log('休眠后：' + new Date().getTime());
                },
        //改变定位
        changeProvince(province){
            this.province=province
        }
    },
    beforeMount(){
        
    },
    mounted(){
        if(this.$route.query.province==undefined){
            this.getLocation()
            this.sleep(500)
            if(local!=''){
                this.province=local
            }else{
                    this.tip="定位失败，默认定位："
            }
       }else{
           this.province=this.$route.query.province
       }
    },
    watch:{
        province:function(){
           this.$emit('setProvince',this.province)
            this.tip="当前定位："
        }
    }
}

</script>
<style scoped>
.dropright{
    margin: 5px 0;
}
</style>