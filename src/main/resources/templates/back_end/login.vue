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
                <input type="text" class="form-control" v-model="account"  placeholder="Enter account">
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
                <input type="password" class="form-control" v-model="password"  placeholder="Enter password">
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
            account:'',
            password:'',
            name:'',
            //提示账号密码是否错误
            checkFlag:false,
            //账号提示
            accountTip:'',
            accountFlag:false,
            //密码提示
            passwordTip:'',
            passwordFlag:false
        }
    },
    methods:{
        //检验
            check(){
                var reg=/^[0-9]*$/
                var pattern=new RegExp(reg)
                if(this.account===''){
                    this.accountTip='Account number cannot be empty!'
                    this.accountFlag=true
                }else if(pattern.test(this.account)){
                    this.accountFlag=false
                }else if(!pattern.test(this.account)){
                    this.accountTip="Please enter the correct account number!"
                    this.accountFlag=true
                }

                if(this.password==''){
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
            // 这里直接通过if进行判断
        login(){
            var flag=false
            console.log('进入login.vue的login')
            //登陆成功
             if(this.account=="123"&&this.password=='123'){
                this.account='123'
                this.password=''
                this.name='小明'
                //传回给父组件的判断标志
                flag=true
                this.$emit('loginb',this.account,this.name,flag)
             }else{
                 //登陆失败
                flag=false
                this.checkFlag=true
                this.$emit('loginb','','',flag)
             }
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