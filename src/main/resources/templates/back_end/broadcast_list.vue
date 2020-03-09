<template>
  <div>
  <div> 
      <div class="row clearfix">
         <form class="form-inline float-right col-md-6 offset-md-6 ">
            <input type="text" class="form-control w-75" placeholder="enter title or digest or releaseTime" v-model="searchElement">
            <button type="submit" class="btn btn-primary" @click.prevent="setBroadcastList">search</button>
            <button type="button" class="btn btn-success " data-toggle="modal" data-target="#broadcastItem" @click="modifyModal('add')">add</button>
          </form>
    <div class="col-md-12 column">
			<table class="table table-hover table-bordered  rounded">
				<thead>
					<tr>
						<th>
							title
						</th>
						<th>
							digest
						</th>
						<th>
							source
						</th>
						<th>
							releaseTime
						</th>
            <th>
							operation
						</th>
					</tr>
				</thead>
				<tbody>
          <!-- 循环展示broadcastList -->
            <tr  v-for="item in broadcastList" :key="item.id" v-cloak>
            <td>
              {{item.title}}
						</td>
						<td>
              {{item.digest}}
						</td>
						<td>
              {{item.source}}
						</td>
						<td>
              {{item.releaseTime}}
						</td>
            <td>
              <div class="btn-group">
                <button type="button" class="btn btn-warning rounded btn-sm border" data-toggle="modal" data-target="#broadcastItem" @click="modifyModal('modify',item.id)">modify</button>
                <button type="button" class="btn btn-danger rounded btn-sm border " @click="deleteBroadcast(item.id)">delete</button>
            </div>
						</td>
        </tr>
				</tbody>
			</table>
       <!-- 模态框 -->
  <div class="modal fade" id="broadcastItem">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">{{modelType}} broadcast</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <!-- 模态框主体 -->
        <div class="modal-body">
          <!-- 一个表单 -->
            <div class="row clearfix">
              <div class="col-md-12 column">
                <form role="form">
                  <div class="form-group">
                    <label for="exampleInputEmail1">title:</label><input class="form-control" v-model="broadcastItem.title"/>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputsource1">digest:</label><textarea  class="form-control" v-model="broadcastItem.digest"></textarea>
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputsource1">source:</label><input class="form-control" v-model="broadcastItem.source"/>
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputsource1">releaseTime:</label><input  class="form-control" v-model="broadcastItem.releaseTime"/>
                  </div>
                </form>
              </div>
            </div>
        </div>
        <!-- 模态框底部 -->
        <div class="modal-footer">
          <button type="button" class="btn btn-success" data-dismiss="modal" @click="saveModal">save</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">clone</button>
        </div>
      </div>
    </div>
  </div>
		</div>
	</div>
  </div>
  </div>
</template>

<script>
//导入adminDao层
import broadcastDao from '@/main/resources/static/js/dao/broadcast.js'
export default {
  data(){
    return{
      //展示的列表
      broadcastList:[],
      //全部列表
      allBroadcastList:[],
      modelType:'',
      broadcastItem:{
        id:'',
        digest:'',
        source:'',
        title:'',
        releaseTime:''
      },
      //搜索条件
      searchElement:''
    }
  },
  methods:{
     //更新list
     setBroadcastList(){
      var element=this.searchElement
       this.searchElement=''
       if(element==undefined||element==''){
         this.broadcastList=this.allBroadcastList
       }else{
         this.broadcastList=this.allBroadcastList.filter(item=>{
           //实现模糊查询
           var reg =  new RegExp(element);
           if(reg.test(item.digest)||reg.test(item.title)||reg.test(item.releaseTime))return item
         })
         
       }
     },
    //删除broadcast
    //在数据库中删除成功，后删除broadcastList中的数据
    deleteBroadcast(id){
      if(broadcastDao.deleteBroadcastFromdb(id)){
        this.broadcastList.some((item,index)=>{
          if(item.id==id){
            this.broadcastList.splice(index,1)
          }
        })
      }
    },
    
    //修改broadcast
    modifyBroadcast(broadcastItem){
      if(broadcastDao.modifyBroadcastFromdb(broadcastItem)){
        this.deleteBroadcast(broadcastItem.id)
        this.broadcastList.unshift(broadcastItem)
      }
    }, 
    //新增broadcast
    addBroadcast(broadcastItem){
      if(broadcastDao.addBroadcastFromdb(broadcastItem)){
        this.allbroadcastList.unshift(broadcastItem)
      }
    },
    //修改模态框类型
    modifyModal(type,id){
      this.modelType=type
      if(id!=undefined){
        this.broadcastList.some((item,index)=>{
          if(item.id==id){
            this.broadcastItem=item
          }
        })
      }else{
        this.broadcastItem={
        id:'',
        digest:'',
        source:'',
        title:'',
        releaseTime:''
      }
      }
    },
    //保存模态框
    saveModal(){
      var id=this.broadcastItem.id
      //检验
        var reg=/^[0-9]*$/
        var reg1=/^1[3456789]\d{9}$/
        var pattern=new RegExp(reg)
        var pattern1=new RegExp(reg1)
        if(this.broadcastItem.digest==''||this.broadcastItem.title==''||this.broadcastItem.source==''||this.broadcastItem.releaseTime==''||
        this.broadcastItem.digest==undefined||this.broadcastItem.title==undefined||this.broadcastItem.source==undefined||this.broadcastItem.releaseTime==undefined||
        pattern.test(title)||pattern1.test(releaseTime)){
          alert('please fill in the correct format')
        }else{
          if(id!=null){
            // id,title,source,digest,releaseTime
            this.modifyBroadcast(this.broadcastItem)
          }else if(id==''||id==undefined){
            this.addBroadcast(this.broadcastItem)
          }
        }
    }
  },
  mounted(){
    //dao中是getBroadcastList获取全部数据
    this.allBroadcastList=broadcastDao.getBroadcastList()
    this.setBroadcastList()
  },
  components:{}
}

</script>
<style scoped>
.table{
  margin: 10px 0;
}
</style>