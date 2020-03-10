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
            <th v-if="user.identity==1">
              modifier
            </th>
            <th  v-if="user.identity==1">
              founder
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
            <td v-if="user.identity==1">
              {{item.modifier}}
            </td>
            <td v-if="user.identity==1">
              {{item.founder}}
            </td>
            <td>
              <div class="btn-group">
                <button type="button" class="btn btn-warning rounded btn-sm border" data-toggle="modal" data-target="#broadcastItem" @click="modifyModal('modify',item.id)">modify</button>
                <button type="button" class="btn btn-danger rounded btn-sm border " @click="deleteBroadcastFromdb(item.id)">delete</button>
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
import func from '../../../../../vue-temp/vue-editor-bridge'
export default {
  data(){
    return{
      //展示的列表
      broadcastList:[],
      //全部列表
      allBroadcastList:[],
      modelType:'',
      //模态框的数据
      broadcastItem:{
        id:'',
        digest:'',
        source:'',
        title:'',
        releaseTime:'',
        modifyier:'',
        founder:''
      },
      //搜索条件
      searchElement:'',
      //数据库删除标志
      deleteSign:false,
      //数据库修改标志
      midifySign:false,
      //数据库增加标志
      addSign:false
    }
  },
  methods:{
     //更新broadcastList
     //展示的列表
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
    //删除allBroadcastList中的broadcast
    deleteBroadcast(id){
      var id=this.broadcastItem.id
        this.allBroadcastList.some((item,index)=>{
          if(item.id==id){
            this.allBroadcastList.splice(index,1)
          }
        })
        //更新broadcastlist
      this.setBroadcastList()
    },
    //修改allbroadlist中的broadcast
    modifyBroadcast(){
      var broadcastItem=this.broadcastItem
        this.deleteBroadcast(broadcastItem.id)
        this.allBroadcastList.unshift(broadcastItem)
        //更新broadcastlist
        this.setBroadcastList()
    }, 
    //在allbroadcastlist中新增broadcast
    addBroadcast(){
        var broadcastItem=this.broadcastItem
        this.allbroadcastList.unshift(broadcastItem)
        //更新broadcastlist
        this.setBroadcastList()
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
            //在数据库中修改
            this.modifyBroadcastFromdb()
          }else if(id==''||id==undefined){
            //在数据库中增加
            this.addBroadcastFromdb()
          }
        }
    },
    //在数据库中获取全部实时播报列表信息
    // ！！！！！！！这里异步请求后端，获取broadcastList！！！！！！
    // 需要读取实时播报数据库
    //Key：broadcast_data
    getBroadcastList(){
        //异步请求
       axios
      .get('/broadcast/getList')
      .then(response => (
        //返回值(id,releaseTime,digest,title,source,founder,modifier)
        this.allBroadcastList = response))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      });
      },
      //!!!!!!!!!需要读取！！！！！
      // 需要读取实时播报数据库
      //Key：broadcast_data
      deleteBroadcastFromdb(id){
        //将获取的id放入data中
        this.broadcastItem.id=id
         //异步请求
        axios.get('/broadcast/deleteBroadcast',
        {
          //id要修改的用户的id，operator操作人员的名称
          id:id,
          operator:this.user.name
        })
        .then(response => (
          //返回是否删除成功,布尔值
          this.deleteSign=response
          ))
        .catch(function (error) { // 请求失败处理
          console.log(error);
        });
      },
      //！！！！！！！需要读取！！！！
      //需要读取实时播报数据库
      //Key：broadcast_data
      //修改数据库中的broadcast
      modifyBroadcastFromdb(){
                //异步请求
                axios.post('/broadcast/modifyBroadcast', {
                    // id,digest,source,title,releaseTime,modifier(操作人员)
                    id:this.broadcastItem.id,
                    digest:this.broadcastItem.digest,
                    source:this.broadcastItem.source,
                    title:this.broadcastItem.title,
                    releaseTime:this.broadcastItem.releaseTime,
                    modifyier:this.user.name
                    })
                .then(//请求成功处理
                    response => (
                        //修改成功返回true 布尔值
                        this.modifySign=response
                    ))
                .catch(function (error) { // 请求失败处理
                        console.log(error)
                    })
    },
    //!!!!!!需要读取！！！！！！！
    //用户信息数据
    //Key：broadcast_data
    //修改数据库中的broadcast
    addBroadcastFromdb(){
                //异步请求
                axios.post('/broadcast/addBroadcast', {
                   //digest,source,title,releaseTime,founder(操作人员)
                    digest:this.broadcastItem.digest,
                    source:this.broadcastItem.source,
                    title:this.broadcastItem.title,
                    releaseTime:this.broadcastItem.releaseTime,
                    founder:this.user.name
                    })
                .then(//请求成功处理
                    response => (
                        //修改成功返回true 布尔值
                        this.addSign=response
                    ))
                .catch(function (error) { // 请求失败处理
                        console.log(error)
                    })
    }

  },
  mounted(){
    //初始化allbroadcastList
    //从数据库中获取全部信息
    this.getBroadcastList()
  },
  watch:{
      'allBroadcastList':function(){
          //设置broadlist
          this.setBroadcastList()
      },
      'deleteSign':function(){
        //删除broadlist中的broadcast
        if(this.deleteSign)this.deleteBroadcast()
      },
      'modifySign':function(){
        //修改broadcastList中的broadcast
        if(this.modifySign){
          //修改人
          this.broadcastItem.modifyier=this.user.name
          //修改allbroadcastlist
          this.modifyBroadcast()
        }
      },
      'addSign':function(){
        //创建人
         this.broadcastItem.founder=this.user.name
        //修改allbroadcastlist
        this.addBroadcast()
      }
  },
  //进入该页面的用户
  props:['user'],
  components:{}
}

</script>
<style scoped>
.table{
  margin: 10px 0;
}
</style>