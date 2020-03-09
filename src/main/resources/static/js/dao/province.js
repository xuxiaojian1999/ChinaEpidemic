//省份数据
import totalList from '@/test/resources/static/test/total-list.js'
export default{
        // ！！！！！需要读取！！！！！！！！！！！！！
        // 各省数据
        // Key：province_data:省份名称
        // 各城市数据
        //Key：city_data:省份名称:城市名称
        // 每日城市新增数据
        // Key：city_data:日期:省份名称:城市名称
        // 每日省新增数据
        // Key：province_data:日期:省份名称
        // 这里通过获取.js文件中的数据模拟
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
                        totalData:i.total
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
        // !!!!!!!!!!!需要读取
        // 各省数据
        //Key：province_data:省份名称
        //目前使用.js文件中的数据代替
        //获取中国各省份的数据
        getProvinceList(){
            var newprovinceList=[]
            var provinceList=totalList.data.areaTree[0].children
            provinceList.forEach(element => {
                var item={
                    province:element.name,
                    totalConfirm:element.total.confirm,
                    suspect:element.total.suspect,
                    heal:element.total.heal,
                    dead:element.total.dead
                }
                newprovinceList.push(item)
            });
            return newprovinceList
        }

}