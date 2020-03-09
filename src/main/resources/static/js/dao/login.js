export default{
            // !!!!!需要读取!!!!!!
            // 用户信息数据
            // Key:users
            loginFromdb(account,password){
                //登陆成功
                 // 这里直接通过if进行判断
                 //生成一个四位数的随机数
                 var checkCode=Math.floor(Math.random()*(9999-1000))+1000
                 if(account=="123"&&password=='123'){
                     //返回名称和从后端传递过来的随机数
                     //Identity 1:系统管理员 2：工作人员 
                    return {name:'小明',checkCode:checkCode,identity:1}
                 }else{
                    //登陆失败
                    return false
                 }
                 
            },
            // !!!!!!!!需要读取！！！！！！
            // 用户信息数据
            // Key:users
            //对checkCode和identity进行校验，实现免登陆
            checkFromdb(identity,checkCode){
               //对比数据库中的checkCode是否一致
               //这里对比不了，直接返回true
               var check=false

               check=true
               return check
            }
}