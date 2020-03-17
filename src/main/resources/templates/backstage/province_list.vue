<template>
  <div>
    
			<!-- 当updateSign1为true时，为正在更新数据状态 -->
				<div v-if="updateSign1" class="alert alert-secondary alert-dismissible" >
					<button type="button" class="close" @click="updateSign1=!updateSign1">&times;</button>
					<strong>Be careful!</strong> Updating database！
				</div>
			<!-- 当updateSign2为true时，为正在更新成功状态 -->	
				<div v-if="updateSign2" class="alert alert-success alert-dismissible" >
					<button type="button" class="close" @click="updateSign2=!updateSign2">&times;</button>
					<strong>Success!</strong> Database update succeeded! Please refresh the page to view the latest data!
				</div>
			<!-- 当updateSign3为true时，为正在更新失败状态 -->	
				<div v-if="updateSign3" class="alert  alert-danger alert-dismissible" >
					<button type="button" class="close" @click="updateSign3=!updateSign3">&times;</button>
					<strong>Fail!</strong> Database update failed! Please try again!
				</div>
				<!-- 当updateSign4为true时，为正在更新状态 -->	
				<div v-if="updateSign4" class="alert  alert-warning alert-dismissible" >
					<button type="button" class="close" @click="updateSign4=!updateSign4">&times;</button>
					<div v-html="updateMsg4"></div>
				</div>
    <div class="row btn-group dropright w-100">
		<!-- 系统管理员可以点击 -->
		<button v-if="user.identity==1" type="button" class="col-md-2 btn btn-outline-danger" @click="updateProvince"><abbr title="Every day, click before 12 p.m!">update</abbr></button>
		<!-- 工作人员不可点击 -->
		<button v-else type="button" class="col-md-2 btn btn-outline-danger" disabled>update</button>
	  <button type="button" class="col-md-10 btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		当前查看的是 <span class="lead">{{province}}省</span>的疫情数据
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
	<!-- 展示已经更新在前台的各省份数据 -->
			<div class="row clearfix">
				<!-- 省份数据 -->
		<div class="col-md-12 column">
			<table class="table table-bordered table-hover table-striped table-condensed">
				<thead>
					<tr>
						<th>
							state
						</th>
						<th>
							province
						</th>
						<th>
							confirm
						</th>
						<th>
							suspect
						</th>
						<th>
							heal
						</th>
						<th>
							dead
						</th>
						<th>
							lastUpdateTime
						</th>
						
					</tr>
				</thead>
				<tbody  v-if="showList.local!=undefined">
					<tr v-if="showList.totalData!=null"> 
						<td>
							shown
						</td>
						<td  v-if="showList.local.province!=null&&showList.local.province!=undefined">
							{{showList.local.province}}
						</td>
						<td>
							{{showList.totalData.confirm}}
						</td>
						<td>
							{{showList.totalData.suspect}}
						</td>
						<td>
							{{showList.totalData.heal}}
						</td>
						<td>
							{{showList.totalData.dead}}
						</td>
						<td>
							{{showList.lastUpdateTime}}
						</td>
					</tr>
					<tr v-if="showList.todayData!=null">
						<td>
							not shown
						</td>
						<td v-if="showList.local.province!=null&&showList.local.province!=undefined">
							{{showList.local.province}}
						</td>
						<td>
							{{showList.totalData.confirm+showList.todayData.confirm}}
						</td>
						<td>
							{{showList.totalData.suspect+showList.todayData.suspect}}
						</td>
						<td>
							{{showList.totalData.heal+showList.todayData.heal}}
						</td>
						<td>
							{{showList.totalData.dead+showList.todayData.dead}}
						</td>
						<td>
							{{showList.todayData.lastUpdateTime}}
						</td>
					</tr>
				</tbody>
			</table>
		</div> 
		<!-- 城市数据 -->
		<div class="col-md-12 column">
			<table class="table table-bordered table-hover table-striped table-condensed">
				<thead>
					<tr>
						<th>
							state
						</th>
						<th>
							city
						</th>
						<th>
							confirm
						</th>
						<th>
							suspect
						</th>
						<th>
							heal
						</th>
						<th>
							dead
						</th>
						<th>
							lastUpdateTime
						</th>
						<th>
							operation
						</th>
					</tr>
				</thead>
				<tbody v-for="item in showList.city" :key="item.local.city" >
					<tr >
						<td>
							shown
						</td>
						<td>
							{{item.local.city}}
						</td>
						<td >
							{{item.totalData.confirm}}
						</td>
						<td>
							{{item.totalData.suspect}}
						</td>
						<td>
							{{item.totalData.heal}}
						</td>
						<td>
							{{item.totalData.dead}}
						</td>
						<td>
							{{item.lastUpdateTime}}
						</td>
						<td></td>
					</tr>
					<tr v-if="item.todayData!=null">
						<td>
							not shown
						</td>
						<td >
							{{item.local.city}}
						</td>
						<td >
							{{item.todayData.confirm+item.totalData.confirm}}
						</td>
						<td >
							{{item.todayData.suspect+item.totalData.suspect}}
						</td>
						<td > 
							{{item.todayData.heal+item.totalData.heal}}
						</td>
						<td >
							{{item.todayData.dead+item.totalData.dead}}
						</td>
						<td v-if="item.todayData!=null">
							{{item.todayData.lastUpdateTime}}
						</td>
						<td>
							<button type="button" class="btn btn-warning rounded btn-sm border" 
							data-toggle="modal" data-target="#userItem" 
							@click="openModal(item)">modify</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div> 
	       <!-- 模态框 -->
  <div class="modal fade" id="userItem">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">modify new data for {{cityData.city}} </h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <!-- 模态框主体 -->
        <div class="modal-body">
          <!-- 一个表单 -->
            <div class="row clearfix">
              <div class="col-md-12 column">
                <form role="form">
                  <div class="form-group">
                    <label for="exampleInputEmail1">confirm:</label><input class="form-control" v-model="cityData.confirm"/>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">suspect:</label><input  class="form-control" v-model="cityData.suspect"/>
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputPassword1">heal:</label><input class="form-control" v-model="cityData.heal"/>
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputPassword1">dead:</label><input  class="form-control" v-model="cityData.dead"/>
                  </div>
                </form>
              </div>
            </div>
        </div>
        <!-- 模态框底部 -->
        <div class="modal-footer">
			<abbr title="Do not fill in all data">Fill in the new data here</abbr>
          <button type="button" class="btn btn-success" data-dismiss="modal" @click="saveModal">save</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">clone</button>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
	data(){
		return{
			province:'湖北',
			//展示的数据
			showList:[],
			//全部的数据
			provinceList:[],
			//模态框中的数据
			cityData:{
				id:null,
				city:'',
				confirm:0,
				suspect:0,
				heal:0,
				dead:0
			},
			updateSign1:false,
			updateSign2:false,
			updateSign3:false,
			updateSign4:false,
			updateMsg4:''
		}
	},
	methods:{
		//设置展示的数据showlist
		//查询出相应的省份数据
		setShowList(){
			var list=[]
			//filter返回的是一个数组
			//所以需要先用一个数组来装取，然后再取出里面的第一个数组
			list=this.provinceList.filter(item =>{
				//循环找出所需的省份数据
				if(item.local.province==this.province) return item 
			})
			// 设置showList的todayData
			this.showList=list[0]
			var newConfirm=0
			var newSuspect=0
			var newDead=0
			var newHeal=0
			
			this.showList.city.forEach(element => {
				newConfirm=newConfirm+element.todayData.confirm
				newSuspect=newSuspect+element.todayData.suspect
				newDead=newDead+element.todayData.dead
				newHeal=newHeal+element.todayData.heal
			});
			var newTodayData={
				confirm:newConfirm,
				suspect:newSuspect,
				heal:newDead,
				dead:newHeal
			}
			this.showList.todayData=newTodayData
			// console.log(this.showList)
			// console.log("showList.local")
			// console.log(this.showList.local)
			// console.log("showList.city")
			// console.log(this.showList.city)
		},
		//修改province
		changeProvince(local){
			this.province=local
		},
		//打开触发模态框
		openModal(item){
			this.cityData={
				id:item.todayData.id,
				city:item.local.city,
				confirm:item.todayData.confirm,
				suspect:item.todayData.suspect,
				heal:item.todayData.heal,
				dead:item.todayData.dead
			}
		},
		//保存模态框
		saveModal(){
			var patrn = /^[0-9]*$/
			if(patrn.test(this.cityData.confirm)&&patrn.test(this.cityData.suspect)
			&&patrn.test(this.cityData.heal)&&patrn.test(this.cityData.dead)){
				//当输入值为数字时调用modifyTodayFromdb()
				this.modifyTodayFromdb(this.cityData)
			}else{
				alert("Please fill in the whole number!")
			}
		},
		//当前时间生成器
        getNewTime(){
            var date=new Date()
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var h = date.getHours();
            var m = date.getMinutes();
            var s = date.getSeconds();
            // 在 numbers<10 的数字前加上 0
            m = checkTime(m);
            s = checkTime(s);
            month=checkTime(month)
            strDate=checkTime(strDate)
            function checkTime(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }
            return year+'-'+month+'-'+strDate+' '+h+':'+m+':'+s
        },
        //这里的每日是指当天
        // 这里通过获取.js文件中的数据模拟
        //获取各省份数据（包括城市），用于后台更新，
        //todayData中的数据为疫情最新修改数据
        getNewList(){
			//异步请求
            this.$axios
            .get('/province/getNewList')
            .then(response => {
			//返回值：
			//这里是后台，所以需要更新时间
					//省数据
                //[{
            //     local:{
            //         province:item.name,
            //          city:null,
            //          country:null
            //      },                        
            //      todayData:item.today,
            //      totalData:item.total,
            //      lastUpdateTime:item.lastUpdateTime,
            //      city:cityList
			//    }]
			//		城市数据
            //  citylist=[  数组*{
                    //     local:{
                    //         province:item.name,
                    //         city:i.name,
                    //         country:null
                    //     },
					//     todayData:
					//		{
							// 	id:item.id,
							// 	city:item.local.city,
							// 	confirm:item.todayData.confirm,
							// 	suspect:item.todayData.suspect,
							// 	heal:item.todayData.heal,
							// 	dead:item.todayData.dead,
							// 	lastUpdateTime:item.lastUpdateTime
							// }
					//     totalData:
					//		{
							// 	id:item.id,
							// 	city:item.local.city,
							// 	confirm:item.todayData.confirm,
							// 	suspect:item.todayData.suspect,
							// 	heal:item.todayData.heal,
							// 	dead:item.todayData.dead,
							// 	lastUpdateTime:item.lastUpdateTime
							// }
                    //     lastUpdateTime:i.lastUpdateTime
					// }]
					var result=response.data;
         			if(result!=null&&result!=''){
						//  console.log(result)
                        //返回值不为空
                        this.provinceList = result
                        //watch中监听provinceList，会自动更新showlist
						//传入localStorage
						this.setShowList()
                    }
             })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
		},
        //修改每日城市新增数据
        //直接修改mysql
        modifyTodayFromdb(item){
				//异步请求
                this.$axios.post('/province/modifyCity', {
                    // id,city,confirm,suspect,heal,dead,lastUpdateTime(由前端生成),modifier(操作人员)
                    id:this.cityData.id,
					city:this.cityData.city,
					confirm:this.cityData.confirm,
					suspect:this.cityData.suspect,
					heal:this.cityData.heal,
					dead:this.cityData.dead,
					modifier:this.user.name,
					lastUpdateTime:this.getNewTime()
                    })
                .then(//请求成功处理
                    response => {
					  //返回值，成功返回true，布尔值
					  var result=response.data;
         			if(result!=null&&result!=''){
						 this.updateSign4=true
                      if(result){
                        //当修改成功后
						this.updateMsg4="<strong>Success!</strong>"+this.cityData.city+"Data updated successfully!Refresh the page to show the latest data!"
					  }else{
						  //修改失败后
						this.updateMsg4="<strong>Fail!</strong>"+this.cityData.city+"Data update failed!"
					  }
					  }
                    })
                .catch(function (error) { // 请求失败处理
                        console.log(error)
                })
		},
		//更新新的每日城市，每日省份，默认值全为0
		//每日省份数据仅用于参考，无法修改
		updateProvince(){
			//进入该处理方法，先把updatedign标志改为1，为正在更新数据状态
			this.updateSign1=true
			this.updateSign2=false
			this.updateSign3=false
			//异步请求
            this.$axios
            .get('/province/updateProvince')
            .then(response => {
				//返回值，布尔值
				//成功返回true
				var result=response.data;
         		if(result!=null){
                    if(result){
						//数据库更新成功，将updatesign改为2，为更新成功状态
						this.updateSign2=true
                    }else{
						//数据库更新失败，将updatesign改为3，为更新失败状态
						this.updateSign3=true
					}
				}
             })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
		}
	},
	beforeMount(){
		
	},
	mounted(){
		//初始化provinceList
		//需要在挂载前将数据渲染上去，不然会报空异常
		//获取全部数据
		this.getNewList()
	},
	watch:{
		'province':function(){
			//当province改变时，重新设置showlist
			this.setShowList()
		// console.log(this.showList)
		// console.log(this.provinceList)
		}
	},
	props:['user']
}

</script>
<style scoped>
.row{
	margin: 5px 0;
}
</style>