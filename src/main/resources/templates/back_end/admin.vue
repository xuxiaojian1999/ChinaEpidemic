<template>
  <div>
  <div v-if="user.identity==1"> 
      <div class="row clearfix">
         <form class="form-inline float-right col-md-6 offset-md-6">
            <input type="text" class="form-control w-75" placeholder="enter account or name or phone" v-model="searchElement">
            <button type="submit" class="btn btn-primary" @click.prevent="setUserList">search</button>
            <button type="button" class="btn btn-success float-right" data-toggle="modal" data-target="#userItem" @click="modifyModal('add')">add</button>
          </form>
    <div class="col-md-12 column">
			<table class="table table-hover table-bordered  rounded">
				<thead>
					<tr>
						<th>
							account
						</th>
						<th>
							name
						</th>
						<th>
							password
						</th>
						<th>
							phone
						</th>
             <th >
              modifier
            </th>
            <th >
              founder
            </th>
            <th>
							operation
						</th>
           
					</tr>
				</thead>
				<tbody>
          <!-- 循环展示userList -->
            <tr  v-for="item in userList" :key="item.id" v-cloak>
            <td>
              {{item.account}}
						</td>
						<td>
              {{item.name}}
						</td>
						<td>
              {{item.password}}
						</td>
						<td>
              {{item.phone}}
						</td>
            <td >
              {{item.modifier}}
            </td>
            <td>
              {{item.founder}}
            </td>
            <td>
              <div class="btn-group">
                <button type="button" class="btn btn-warning rounded btn-sm border" data-toggle="modal" data-target="#userItem" @click="modifyModal('modify',item.id)">modify</button>
                <button type="button" class="btn btn-danger rounded btn-sm border " @click="deleteUser(item.id)">delete</button>
            </div>
						</td>
        </tr>
				</tbody>
			</table>
       <!-- 模态框 -->
  <div class="modal fade" id="userItem">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">{{modelType}} user</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <!-- 模态框主体 -->
        <div class="modal-body">
          <!-- 一个表单 -->
            <div class="row clearfix">
              <div class="col-md-12 column">
                <form role="form">
                  <div class="form-group">
                    <label for="exampleInputEmail1">account:</label><input class="form-control" v-model="userItem.account"/>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">name:</label><input  class="form-control" v-model="userItem.name"/>
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputPassword1">password:</label><input class="form-control" v-model="userItem.password"/>
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputPassword1">phone:</label><input  class="form-control" v-model="userItem.phone"/>
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
      <div v-else class="row clearfix text-danger ">
        <div class="col-md-12 column">
          <h2>
            Wrong
          </h2>
          <h4>
            You do not have permission to browse the interface!
          </h4>
          <pre class="text-warning">Please enter the correct address!</pre>
        </div>
      </div>
  </div>
</template>

<script>
//导入adminDao层
import dao from '@/main/resources/static/js/dao/admin.js'
export default {
  data(){
    return{
      userList:[],
      allUserList:[],
      modelType:'',
      userItem:{
        id:'',
        name:'',
        password:'',
        account:'',
        phone:''
      },
      //搜索条件
      searchElement:''
    }
  },
  methods:{
     //更新userlist
     setUserList(){
      var element=this.searchElement
       this.searchElement=''
       if(element==undefined||element==''){
         this.userList=this.allUserList
       }else{
         this.userList=this.allUserList.filter(item=>{
           //实现模糊查询
           var reg =  new RegExp(element);
           if(reg.test(item.name)||reg.test(item.account)||reg.test(item.phone))return item
         })
         
       }
     },
    //删除user
    //在数据库中删除成功，后删除userList中的数据
    deleteUser(id){
      if(dao.deleteUserFromdb(id,this.user.name)){
        this.userList.some((item,index)=>{
          if(item.id==id){
            this.userList.splice(index,1)
          }
        })
      }
    },
    
    //修改user
    modifyUser(userItem){
      if(dao.modifyUserFromdb(userItem,this.user.name)){
        this.deleteUser(userItem.id)
        this.userList.unshift(userItem)
      }
    }, 
    //新增user
    addUser(userItem){
      if(dao.addUserFromdb(userItem,this.user.name)){
        this.allUserList.unshift(userItem)
      }
    },
    //修改模态框类型
    modifyModal(type,id){
      this.modelType=type
      if(id!=undefined){
        this.userList.some((item,index)=>{
          if(item.id==id){
            this.userItem=item
          }
        })
      }else{
        this.userItem={
        id:'',
        name:'',
        password:'',
        account:'',
        phone:''
      }
      }
    },
    //保存模态框
    saveModal(){
      var id=this.userItem.id
      //检验
        var reg=/^[0-9]*$/
        var reg1=/^1[3456789]\d{9}$/
        var pattern=new RegExp(reg)
        var pattern1=new RegExp(reg1)
        if(this.userItem.name==''||this.userItem.account==''||this.userItem.password==''||this.userItem.phone==''||
        this.userItem.name==undefined||this.userItem.account==undefined||this.userItem.password==undefined||this.userItem.phone==undefined||
        pattern.test(account)||pattern1.test(phone)){
          alert('please fill in the correct format')
        }else{
          if(id!=null){
            // id,account,password,name,phone
            this.modifyUser(this.userItem)
          }else if(id==''||id==undefined){
            this.addUser(this.userItem)
          }
        }
    }
  },
  mounted(){
    this.allUserList=dao.getAllUserList()
    this.setUserList()
  },
  props:['user'],
  components:{}

}
</script>
<style scoped>
.table{
  margin: 10px 0;
}
</style>