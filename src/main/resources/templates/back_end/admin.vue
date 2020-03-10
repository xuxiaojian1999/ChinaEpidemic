<template>
  <div>
  <div v-if="user.identity==1"> 
      <div class="row clearfix">
         <form class="form-inline float-right col-md-6 offset-md-6">
            <input type="text" class="form-control w-75" placeholder="enter account or name or phone" v-model="searchElement">
            <button type="submit" class="btn btn-primary" @click.prevent="setUserList">search</button>
            <button type="button" class="btn btn-success float-right" data-toggle="modal" data-target="#userItem" @click="openModal('add')">add</button>
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
            <th>
              identity
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
            <td>
              {{item.identity}}
            </td>
            <td >
              {{item.modifier}}
            </td>
            <td>
              {{item.founder}}
            </td>
            <td>
              <div class="btn-group">
                <button type="button" class="btn btn-warning rounded btn-sm border" data-toggle="modal" data-target="#userItem" @click="openModal('modify',item.id)">modify</button>
                <button type="button" class="btn btn-danger rounded btn-sm border " @click="deleteUserFromdb(item.id)">delete</button>
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
                  <div class="form-group">
                    <label for="exampleInputPassword1">identity:</label><input  class="form-control" v-model="userItem.identity"/>
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
        phone:'',
        identity:''
      },
      //搜索条件
      searchElement:'',
      //数据库删除标志
      deleteSign:false,
      //数据库修改标志
      modifySign:false,
      //数据库增加标志
      addSign:false
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
    //删除allUserList中的user数据
    //参数，要删除的用户id
    deleteUser(id){
        this.allUserList.some((item,index)=>{
          if(item.id==id){
            this.allUserList.splice(index,1)
          }
        })
        //重新设置userlist
        this.setUserList()
    },
    //修改allUserList中的user
    modifyUser(userItem){
        this.deleteUser(userItem.id)
        this.allUserList.unshift(userItem)
        //重新设置userlist
        this.setUserList()
    }, 
    //在alluserList中新增user
    addUser(userItem){
        this.allUserList.unshift(userItem)
        //重新设置userlist
        this.setUserList()
    },
    //打开模态框
    openModal(type,id){
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
        this.userItem.name==undefined||this.userItem.account==undefined||this.userItem.password==undefined||
        this.userItem.phone==undefined||
        pattern.test(account)||pattern1.test(phone)){
          alert('please fill in the correct format')
        }else{
          if(id!=null){
           //在数据中修改
            this.modifyUserFromdb()
          }else if(id==''||id==undefined){
            //在数据库中新增
            this.addUserFromdb()
          }
        }
    },
    //从数据库中获取数据
    //！！！！！！！需要读取！！！
    // 用户信息数据
    // Key:users 
    // 获取全部用户数据
    getAllUserList(){
      //异步请求
      axios.get('admin/getList')
      //返回值为全部user列表
      //user(id,account,name,password,phone:,modifie,founder,identity)
      //checkCode不需要展示
      .then(response => (this.allUserList= response))
      .catch(function (error) { // 请求失败处理
        console.log(error);
      });
    },
    //!!!!!!!!!!!需要读取！！！
    // 用户信息数据
    // Key:users 
    //删除数据库的user
    //直接有按钮调用
    deleteUserFromdb(id){
            //异步请求
      axios.get('admin/deleteUser',
      {
        //id要修改的用户的id，operator操作人员的名称
        id:id,
        operator:user.name
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
    //用户信息数据
    // Key:users 
    //修改数据库中的user
    modifyUserFromdb(){
                //异步请求
                axios.post('/admin/modifyUser', {
                    // id,account,password,name,phone,identity,operator(操作人员)
                    id:userItem.id,
                    account: this.userItem.account,
                    password:this.userItem.password,
                    name:this.userItem.name,
                    phone:this.userItem.phone,
                    identity:this.userItem.identity,
                    operator:this.user.name
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
    // Key:users 
    //在数据库中新增user
    addUserFromdb(){
                    //异步请求
                axios.post('/admin/addUser', {
                    //account,password,name,phone,identity,operator(操作人员)
                    account: this.userItem.account,
                    password:this.userItem.password,
                    name:this.userItem.name,
                    phone:this.userItem.phone,
                    identity:this.userItem.identity,
                    operator:this.user.name
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
  beforeMount(){
    //初始化alluserList
    this.getAllUserList()
  },
  watch:{
    'allUserList':function(){
      //当alluserlist改变时，就是从数据库中获取到了全部user
      //初始化userlist
      this.setUserList()
    },
    'deleteSign':function(){
      if(this.deleteSign)this.deleteUser(this.userItem.id)
    },
    'modifySign':function(){
      if(this.modifySign)this.modifyUser(this.userItem)
    },
    'addSign':function(){
      if(this.addSign)this.addUser(this.userItem)
    }
  },//user是登陆到这个页面的用户
  props:['user'],
  components:{}

}
</script>
<style scoped>
.table{
  margin: 10px 0;
}
</style>