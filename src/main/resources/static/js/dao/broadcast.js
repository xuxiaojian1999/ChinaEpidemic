//实时播报数据
import broadcastList from '@/test/resources/static/test/broadcast-list.js'
export default{
    //获取全部列表信息
    getBroadcastList(){
      console.log("getBroadcastList")
        // ！！！！！！！这里异步请求后端，获取broadcastList！！！！！！
        // 需要读取实时播报数据库
        //Key：broadcast_data
        //目前通过.js文件获取
        var newBroadcastList=[]
        //模拟id
        var id=0
      broadcastList.data.items.forEach(element => {
        newBroadcastList.push({id:id,releaseTime:element.ptime,
          digest:element.digest,title:element.title,source:element.source,
          founder:'founder',modifier:'modifier'})
          id++
      });
      return newBroadcastList
      },
      //!!!!!!!!!需要读取！！！！！
      // 需要读取实时播报数据库
      //Key：broadcast_data
      deleteBroadcastFromdb(id,operator){
        //这里直接返回true
        return true
      },
       //！！！！！！！需要读取！！！！
      //需要读取实时播报数据库
      //Key：broadcast_data
      //修改数据库中的broadcast
    modifyBroadcastFromdb(broadcastItem,operator){

      //这里直接返回true
      return true
    },
    //!!!!!!需要读取！！！！！！！
    //用户信息数据
    //Key：broadcast_data
    //修改数据库中的broadcast
    addBroadcastFromdb(broadcastItem,operator){
      //这里直接返回true
      return true
    }

}