<template>
  <div>
    <div class="row btn-group dropright w-100">
	  <button type="button" class="col-md-12 btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
				<tbody>
					<tr>
						<td>
							shown
						</td>
						<td>
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
				<tbody v-for="item in showList.city" :key="item.local.city">
					<tr >
						<td>
							shown
						</td>
						<td>
							{{item.local.city}}
						</td>
						<td>
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
					<tr >
						<td>
							not shown
						</td>
						<td>
							{{item.local.city}}
						</td>
						<td>
							{{item.todayData.confirm+item.totalData.confirm}}
						</td>
						<td>
							{{item.todayData.suspect+item.totalData.suspect}}
						</td>
						<td>
							{{item.todayData.heal+item.totalData.heal}}
						</td>
						<td>
							{{item.todayData.dead+item.totalData.dead}}
						</td>
						<td>
							{{item.todayData.lastUpdateTime}}
						</td>
						<td>
							<button type="button" class="btn btn-warning rounded btn-sm border" 
							data-toggle="modal" data-target="#userItem" 
							@click="modifyModal(item)">modify</button>
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
			cityData:{
				id:null,
				city:'',
				confirm:0,
				suspect:0,
				heal:0,
				dead:0
			}
		}
	},
	methods:{
		//设置展示的数据showlist
		setShowList(){
			var province=[]
			province=this.provinceList.filter(item =>{
				if(item.local.province==this.province) return item 
			})
			this.showList=province[0]
		},
		//修改province
		changeProvince(local){
			this.province=local
		},
		//修改触发模态框
		modifyModal(item){
			this.cityData={
				id:item.id,
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
				if(dao.modifyTodayFromdb(this.cityData)){
					location.reload()
				}
			}else{
				alert("Please fill in the whole number!")
			}
		}
	},
	beforeMount(){
		//初始化provinceList
		//需要在挂载前将数据渲染上去，不然会报空异常
		//获取全部数据
		this.provinceList=dao.getNewList()
		//给展示的数据初始化
		this.setShowList()
	}
	,
	mounted(){
		
	},
	watch:{
		'province':function(){
			this.setShowList()
		}
	}
}

</script>
<style scoped>
.row{
	margin: 5px 0;
}
</style>