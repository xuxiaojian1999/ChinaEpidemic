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