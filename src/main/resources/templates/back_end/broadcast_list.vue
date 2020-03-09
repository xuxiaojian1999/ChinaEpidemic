<template>
  <div> 

  </div>
</template>

<script>
//导入adminDao层
import dao from '@/main/resources/static/js/dao/broadcast.js'
export default {
  // data(){
  //   return{
  //     userList:[],
  //     allUserList:[],
  //     modelType:'',
  //     userItem:{
  //       id:'',
  //       name:'',
  //       password:'',
  //       account:'',
  //       phone:''
  //     },
  //     //搜索条件
  //     searchElement:''
  //   }
  // },
  // methods:{
  //    //更新userlist
  //    setUserList(){
  //     var element=this.searchElement
  //      this.searchElement=''
  //      if(element==undefined||element==''){
  //        this.userList=this.allUserList
  //      }else{
  //        this.userList=this.allUserList.filter(item=>{
  //          //实现模糊查询
  //          var reg =  new RegExp(element);
  //          if(reg.test(item.name)||reg.test(item.account)||reg.test(item.phone))return item
  //        })
         
  //      }
  //    },
  //   //删除user
  //   //在数据库中删除成功，后删除userList中的数据
  //   deleteUser(id){
  //     if(dao.deleteUserFromdb(id)){
  //       this.userList.some((item,index)=>{
  //         if(item.id==id){
  //           this.userList.splice(index,1)
  //         }
  //       })
  //     }
  //   },
    
  //   //修改user
  //   modifyUser(userItem){
  //     if(dao.modifyUserFromdb(userItem)){
  //       this.deleteUser(userItem.id)
  //       this.userList.unshift(userItem)
  //     }
  //   }, 
  //   //新增user
  //   addUser(userItem){
  //     if(dao.addUserFromdb(userItem)){
  //       this.allUserList.unshift(userItem)
  //     }
  //   },
  //   //修改模态框类型
  //   modifyModal(type,id){
  //     this.modelType=type
  //     if(id!=undefined){
  //       this.userList.some((item,index)=>{
  //         if(item.id==id){
  //           this.userItem=item
  //         }
  //       })
  //     }else{
  //       this.userItem={
  //       id:'',
  //       name:'',
  //       password:'',
  //       account:'',
  //       phone:''
  //     }
  //     }
  //   },
  //   //保存模态框
  //   saveModal(){
  //     var id=this.userItem.id
  //     //检验
  //       var reg=/^[0-9]*$/
  //       var reg1=/^1[3456789]\d{9}$/
  //       var pattern=new RegExp(reg)
  //       var pattern1=new RegExp(reg1)
  //       if(this.userItem.name==''||this.userItem.account==''||this.userItem.password==''||this.userItem.phone==''||
  //       this.userItem.name==undefined||this.userItem.account==undefined||this.userItem.password==undefined||this.userItem.phone==undefined||
  //       pattern.test(account)||pattern1.test(phone)){
  //         alert('please fill in the correct format')
  //       }else{
  //         if(id!=null){
  //           // id,account,password,name,phone
  //           this.modifyUser(this.userItem)
  //         }else if(id==''||id==undefined){
  //           this.addUser(this.userItem)
  //         }
  //       }
  //   }
  // },
  // mounted(){
  //   this.allUserList=dao.getAllUserList()
  //   this.setUserList()
  // },
  // components:{}
}

</script>
<style scoped>
.table{
  margin: 10px 0;
}
</style>