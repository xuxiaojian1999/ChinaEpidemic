export default{
            // !!!!!需要读取!!!!!!
            // 用户信息数据
            // Key:users
            // 这里直接通过if进行判断
            loginFromdb(account,password){
                
                //登陆成功
                 if(account=="123"&&password=='123'){
                     //返回名称
                    return '小明'
                 }else{
                    //登陆失败
                    return false
                 }
                 
            }
}