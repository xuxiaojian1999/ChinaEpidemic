<template >
  <div >
      <div class="border border-2 border-primary rounded mx-auto h-50 w-50 login">
        <div class="formBody">
          <h2 class="text-center">登陆</h2>
            <form>
                <div class="form-group">
                <label for="account " class="text-uppercase">account :</label>
                <!--account校验 -->
                    <transition 
                        enter-active-class="animated shake"
                        leave-active-class=""
                        :duration="{enter:1000,leave:0}"
                        mode="out-in">
                            <div v-if="accountFlag" v-text="accountTip" class="text-danger float-right"></div>
                    </transition>
                <input type="text" class="form-control" v-model="user.account"  placeholder="Enter account">
                </div>
                <div class="form-group">
                <label for="pwd" class="text-uppercase">password:</label>
                 <!--account校验 -->
                    <transition 
                        enter-active-class="animated shake"
                        leave-active-class=""
                        :duration="{enter:1000,leave:0}"
                        mode="out-in">
                            <div v-if="passwordFlag" v-text="passwordTip" class="text-danger float-right"></div>
                    </transition>
                <input type="password" class="form-control" v-model="user.password"  placeholder="Enter password">
                </div>
                <button type="submit" class="btn btn-primary" @click.prevent="check">Submit</button>
                 <transition 
                    enter-active-class="animated shake"
                    leave-active-class="animated shake"
                    :duration="{enter:1000,leave:1000}"
                    mode="out-in">
                        <div v-if="checkFlag" class="text-danger float-right">wrong account or password!</div>
                </transition>
            </form>
        </div>
      </div>
  </div>
</template>
// 需要定义一个供该组件使用的loginb函数
<script>
export default {
    data(){
        return{
            user:{
                account:'',
                password:'',
                name:'',
                checkCode:'',
                identity:''
            },
            //是否登陆成功,默认为成功
            loginFlag:false,
            //提示账号密码是否错误
            checkFlag:false,
            //账号提示
            accountTip:'',
            accountFlag:false,
            //密码提示
            passwordTip:'',
            passwordFlag:false,

        }
    },
    methods:{
        //检验
            check(){
                var reg=/^[0-9]*$/
                var pattern=new RegExp(reg)
                if(this.user.account===''){
                    this.accountTip='Account number cannot be empty!'
                    this.accountFlag=true
                }else if(pattern.test(this.user.account)){
                    this.accountFlag=false
                }else if(!pattern.test(this.user.account)){
                    this.accountTip="Please enter the correct account number!"
                    this.accountFlag=true
                }

                if(this.user.password==''){
                    this.passwordTip='Password cannot be empty!'
                    this.passwordFlag=true
                }else{
                    this.passwordFlag=false
                }
                // 当检验都通过是进入login
                if(!this.passwordFlag&&!this.accountFlag)this.login()
            },
            // !!!!!需要读取!!!!!!
            // 用户信息数据
            // Key:users
            login(){
                 //生成一个四位数的随机数
                 this.user.checkCode=Math.floor(Math.random()*(9999-1000))+1000
                //异步请求
                axios.post('/admin/login', {
                    //账号，密码，随机码
                        account: this.user.account,
                        password:this.user.password,
                        checkCode:this.user.checkCode
                    })
                .then(//请求成功处理
                    response => {
                        //登陆成功返回一个user对象（name,identity,checkCode,account）
                        //返回名称和从后端传递过来的随机数
                        //Identity 1:系统管理员 2：工作人员 
                        this.user=response
                        //当name不为空，就是登陆成功
                         //给父组件传回一个user对象
                        if(this.user.name!=null&&this.user!='')this.$emit('loginb',this.user)
                    })
                .catch(function (error) { // 请求失败处理
                        console.log(error)
                    })
            }
        
    }
}
</script>
<style scoped>
/* 边框 */
.login{
    margin: 10px 0;
}
/* 表单主题 */
.formBody{
    margin: 15px 10px;
}
</style>>