//localStorage
 //设置过期时间 expire单位:天
 Storage.prototype.setExpire=(key, value, expire) =>{
        let obj={
            data:value,
            time:Date.now(),
            expire:expire*1000*60*60*24
        };
        localStorage.setItem(key,JSON.stringify(obj));
    }
    //获取key
    Storage.prototype.getExpire= key =>{
        let val =localStorage.getItem(key);
        if(!val){
        // 不存在返回null
            return val;
        }
        val =JSON.parse(val);
        if(Date.now()-val.time>val.expire){
            localStorage.removeItem(key);
            return null;
        }
        return val.data;
    }
    export default{
        //用来对传入的字符串进行在localStorage中进行查询
        //该方法仅适用于无参数的查询数据
        //如果存在localStorage中，就直接取出并返回
        //如果不存在localStorage中，就调用传入的方法进行查询，再返回
        //参数：字符串（需要查询的） 方法（用来查询的方法,目前该方法不能有参数）
        //如果第二个参数直接传入一个方法（就是后面带了括号的），就会直接将方法的返回结果传递过来
        accessLocalStorage(str,queryFun){
            var local=localStorage
            var item=local.getExpire(str)
            if(item!=undefined&&item!=''&&item!=null){
                //当查询结果不为空
                return item
            }else{
                //当查询结果为空
                //先调用查询方法进行查询
                item=queryFun()
                //存入localstorage 0.08天=2小时
                local.setExpire(str,item,0.08)
                //返回结果
                return item
            }
        }
    }