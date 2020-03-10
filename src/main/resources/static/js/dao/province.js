//省份数据
import totalList from '@/test/resources/static/test/total-list.js'
export default{
        //当前时间生成器
        getNewTime(){
            var date=new Date()
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var h = date.getHours();
            var m = date.getMinutes();
            var s = date.getSeconds();
            // 在 numbers<10 的数字前加上 0
            m = checkTime(m);
            s = checkTime(s);
            month=checkTime(month)
            strDate=checkTime(strDate)
            function checkTime(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }
            return year+'-'+month+'-'+strDate+' '+h+':'+m+':'+s
        },


        // ！！！！！需要读取！！！！！！！！！！！！！
        // 各省数据
        // Key：province_data:省份名称
        // 各城市数据
        //Key：city_data:省份名称:城市名称
        // 每日城市新增数据
        // Key：city_data:日期:省份名称:城市名称
        // 每日省新增数据
        // Key：province_data:日期:省份名称
        //这里的每日最新是指前一天
        // 这里通过获取.js文件中的数据模拟
        //获取各省份数据（包括城市）
        getList(){
            //全部省详细数据的展示
            var provinceList=[]
            totalList.data.areaTree[0].children.forEach(item => {
               var provinceItem=[]
               var cityList=[]
                item.children.forEach(i=>{
                    var cityItem={
                        local:{
                            province:item.name,
                            city:i.name,
                            country:null
                        },
                        todayData:i.today,
                        totalData:i.total,
                        lastUpdateTime:i.lastUpdateTime
                    }
                    cityList.push(cityItem)
                })
               provinceItem={
                local:{
                    province:item.name,
                     city:null,
                     country:null
                 },                        
                 todayData:item.today,
                 totalData:item.total,
                 lastUpdateTime:item.lastUpdateTime,
                 city:cityList
               }
               provinceList.push(provinceItem)
            });
            return provinceList
        },
        // ！！！！！需要读取！！！！！！！！！！！！！
        // 各省数据
        // Key：province_data:省份名称
        // 各城市数据
        //Key：city_data:省份名称:城市名称
        // 每日城市新增数据
        // Key：city_data:日期:省份名称:城市名称
        // 每日省新增数据
        // Key：province_data:日期:省份名称
        //这里的每日是指当天
        // 这里通过获取.js文件中的数据模拟
        //获取各省份数据（包括城市），用于后台更新，
        //todayData中的数据为疫情最新修改数据
        getNewList(){
            //这一还是照用已经展示出来的数据
            //全部省详细数据的展示
            var provinceList=[]
            totalList.data.areaTree[0].children.forEach(item => {
               var provinceItem=[]
               var cityList=[]
               var cityId=0
                item.children.forEach(i=>{
                    var cityItem={
                        //这里的id是每日城市新增数据中的id
                        id:++cityId,
                        local:{
                            province:item.name,
                            city:i.name,
                            country:null
                        },
                        todayData:i.today,
                        totalData:i.total,
                        lastUpdateTime:i.lastUpdateTime
                    }
                    cityItem.todayData['lastUpdateTime']=this.getNewTime()
                    cityList.push(cityItem)
                })
               provinceItem={
                local:{
                    province:item.name,
                     city:null,
                     country:null
                 },                        
                 todayData:item.today,
                 totalData:item.total,
                 lastUpdateTime:item.lastUpdateTime,
                 city:cityList
               }
               provinceList.push(provinceItem)
            });
            return provinceList
        },
        // ！！！！！！！！需要读取！！！！！！！！
        // 每日中国新增数据 
        // Key:china_data:日期
        // 中国数据
        // Key:china_data
        //获取中国当日的一个总数据
        getChinaData(){
            var china=totalList.data.areaTree[0]
            var chinaData={
                local:{ 
                    province:null,
                    city:null,
                    country:china.name},
                totalData:china.total,
                // {
                //     //现存确诊需要经过计算得到
                //     confirm:'累计确诊',
                //     suspect:'现存疑似',
                //     dead:'累计死亡',
                //     heal:'累计治愈'
                // },
                todayData:china.today
                // {
                //     totalConfirm:'+1',
                //     //现存确诊需要经过计算得到
                //     confirm:'+1',
                //     suspect:'+1',
                //     dead:'+1',
                //     heal:'+1'
                // }
            }
            return chinaData
        },
        // !!!!!!!!!!!需要读取!!!!!!!!
        // 各省数据
        //Key：province_data:省份名称
        //目前使用.js文件中的数据代替
        //获取中国各省份的数据（不包括城市）
        getProvinceList(){
            var newprovinceList=[]
            var provinceList=totalList.data.areaTree[0].children
            provinceList.forEach(element => {
                var item={
                    province:element.name,
                    // 
                    confirm:element.total.confirm,
                    suspect:element.total.suspect,
                    heal:element.total.heal,
                    dead:element.total.dead
                }
                newprovinceList.push(item)
            });
            return newprovinceList
        },
        //!!!!!!!!需要读取！！！！
        //修改每日城市新增数据
        //直接修改mysql
        modifyTodayFromdb(item){


            return true
        }

}