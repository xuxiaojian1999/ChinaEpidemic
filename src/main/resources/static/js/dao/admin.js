 export default{
   //获取用户信息
    //！！！！！！！需要读取！！！
    // 用户信息数据
    // Key:users 
    // 获取全部用户数据
    getAllUserList(){
        return [
          {id:1,account:'1',name:'小明',password:'123',phone:'123456789'},
          {id:2,account:'12',name:'小明',password:'123',phone:'123456789'},
          {id:3,account:'123',name:'小明',password:'123',phone:'123456789'},
          {id:4,account:'1234',name:'小明',password:'123',phone:'123456789'},
          {id:5,account:'12345',name:'小红',password:'123',phone:'123456789'}
        ]
      },
    //!!!!!!!!!!!需要读取！！！
    // 用户信息数据
    // Key:users 
    //删除数据库的user
    deleteUserFromdb(id){
            //这里直接返回true
            return true
        },
    //！！！！！！！需要读取！！！！
    //用户信息数据
    // Key:users 
    //修改数据库中的user
    modifyUserFromdb(userItem){

        //这里直接返回true
        return true
      },
    //!!!!!!需要读取！！！！！！！
    //用户信息数据
    // Key:users 
    //在数据库中新增user
    addUserFromdb(userItem){
        //这里直接返回true
        return true
      }
}  