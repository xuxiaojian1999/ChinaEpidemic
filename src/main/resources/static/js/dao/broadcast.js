//实时播报数据
import broadcastList from '@/test/resources/static/test/broadcast-list.js'
export default{
    //获取全部列表信息
    getBroadList(){
        // ！！！！！！！这里异步请求后端，获取broadcastList！！！！！！
        // 需要读取实时播报数据库
        //Key：broadcast_data
        //目前通过.js文件获取
        var newBroadcastList=[]
      broadcastList.data.items.forEach(element => {
        newBroadcastList.push({releaseTime:element.ptime,disgest:element.digest,title:element.title,source:element.source})
      });
      return newBroadcastList
      },
}