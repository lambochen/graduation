
## 推荐数据

#### 一、获取首页数据

    GET /graduation/recommend/home
    
参数

    无
    
返回



#### 二、根据推荐类型获取

    GET /graduation/recommend/recommend/{recommendType}
    
参数

    recommendType [int] : 推荐类型 【必填】
    pageNo [int] : 页码，默认为1
    pageSize [int] : 每页条数，默认为10
    
参数说明

    recommendType:
        
        USER_BASED_RECOMMEND(1, "基于用户的协同过滤推荐"),
        ITEM_BASED_RECOMMEND(2, "基于物品的协同过滤推荐"),
        SLOPE_ONE_RECOMMEND(3, "SlopeOne协同过滤推荐"),
        POPULAR_RECOMMEND(4, "热门推荐"),
        SEASON_RECOMMEND(5, "时令推荐"),
        USER_TAG_BASED_RECOMMEND(6, "基于用户标签的推荐");  
        
    pageNo,pageSize:
        
        当推荐类型为 1，2，3，6时，不需要填写 
    
返回-推荐类型为热门推荐
返回-推荐类型为时令推荐
返回-其它类型



    

    