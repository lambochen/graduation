## 推荐评估

#### 一、分页获取

    POST /graduation/evalutor/evalutor
    
参数

    pageNo [int] : 当前页数，默认为1
    pageSize [int] : 每页条数，默认为10
    
返回

  



#### 二、根据类型分页获取

    POST /graduation/evalutor/evalutor/type/{typeId}
    
参数

    typeId [int]: 推荐类型
    pageNo [int] : 当前页数，默认为1
    pageSize [int] : 每页条数，默认为10
    
参数说明

    typeId:
        
        1, "基于用户的协同过滤推荐"
        2, "基于物品的协同过滤推荐"
        3, "SlopeOne协同过滤推荐"
    
返回

    
  