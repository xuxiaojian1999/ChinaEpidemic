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
        //返回值
        //如果存在localStorage中，就直接取出并返回
        //如果不存在localStorage中，就返回null
        //参数：字符串（需要查询的） 
        getFromLocalStorage(str){
            var local=localStorage
            var item=local.getExpire(str)
            if(item!=undefined&&item!=''&&item!=null){
                //当查询结果不为空
                return item
            }else{
                //当查询结果为空
                return null
            }
        },
        //将数据存入localSorage中
        //无返回值
        //参数：字符串（名称） 对象（存入的数据） 整型（时间，单位：天）
        setToLocalStorage(key,value,time){
            localStorage.setExpire(key,value,time)
        }
    }