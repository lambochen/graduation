
# 数据库设计说明

### 数据库基本信息

1、数据库版本

    Server version: 5.7.24 MySQL Community Server
    
### 数据库实例基本信息

1、实例名称

    graduation
    
2、存储引擎

    InnoDB
    
3、字符集

    utf8mb4_unicode_ci
    
    | collation_connection | utf8mb4_unicode_ci |
    | collation_database   | utf8mb4_unicode_ci |
    | collation_server     | utf8mb4_unicode_ci |
    
    | character_set_client     | utf8mb4                                      |
    | character_set_connection | utf8mb4                                      |
    | character_set_database   | utf8mb4                                      |
    | character_set_filesystem | binary                                       |
    | character_set_results    | utf8mb4                                      |
    | character_set_server     | utf8mb4                                      |
    | character_set_system     | utf8                                         |
    
### 表字段说明

**1、用户基本信息表（user）**

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   bigint      |   Long        |   ID      | PK，auto_increment   |   |
|   gmt_create  |   timestamp   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |
|   gmt_modified    |   timestamp   |   Date    |   最近修改时间  |   default:current_timestamp, not null |   |
|   deleted     |   varchar(1)  |   Integer     |   是否删除（0：未删除  1：已删除）     |  default:"0", not null   | 所有数据采用逻辑删除   |
|   nick_name    |   varchar(16) |   String  |   用户昵称    |   null    |   |
|   real_name   |   varchar(16) |   String  |   真实姓名    |   null    |   |
|   telephone   |   varchar(11) |   String  |   电话号码    |   UK， not null    | 采用电话号码进行登录  |
|   password    |   varchar(64) |   String  |   密码      |   null    |   MD5值    |
|   gender      |   varchar(1)  |   Integer |   性别（0：保密 1：男 2：女）    |   default:"0", not null   |   后台采用枚举类替换   |
|   birthday    |   date    |   Date    |   出生日期    |   null    |   |
|   country     |   varchar(24) |   String  |   国家  |   null    |       |
|   province    |   varchar(24) |   String  |   省份  |   null    |   |
|   city    |   varchar(24) |   String  |   城市  |   null    |   |
|   position    |   varchar(128) |   String  |   详细地址  |   null    |   |
|   latitude    |   varchar(32) |   String  |   经度  |   null    |   |
|   longitude    |   varchar(32) |   String  |   纬度  |   null    |   |
|   avatar_url  |   varchar(256)    |   String  |   用户头像URL |   null    |   |
|   type    |   varchar(1)  |   Integer |   用户类型（0：普通用户 1：商家 2：管理员 3：超级管理员） | default:"0", not null  |  |
|   description |   varchar(256)    |   String  |   描述  |   null    |   |

其它说明：

    PK: id
    UK: uk_telephone(telephone)
    index:  idx_position(country,province,city)
    engine: InnoDB
    auto_increment: 1
    charset: utf8mb4
    comment: 用户基本信息表
    
**2、用户标签表（user_tag）**
    
**3、商品一级目录表(goods_catalog_one)**

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   int      |   Long        |   ID      | PK，auto_increment   |   |
|   gmt_create  |   timestamp   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |
|   gmt_modified    |   timestamp   |   Date    |   最近修改时间  |   default:current_timestamp, not null |   |
|   deleted     |   varchar(1)  |   Integer     |   是否删除（0：未删除  1：已删除）     |  default:"0", not null   | 逻辑删除   |
|   name    |   varchar(16) |   String  |   目录名称    |   not null    |   |
|   description |   varchar(256)    |   String  |   描述  |   null    |   |

其它说明：

    PK: id
    UK: uk_name(name)
    index:  idx_name(name)
    engine: InnoDB
    auto_increment: 1
    charset: utf8mb4
    comment: 商品一级目录表

**4、商品二级目录表(goods_catalog_two)**

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   int      |   Long        |   ID      | PK，auto_increment   |   |
|   gmt_create  |   timestamp   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |
|   gmt_modified    |   timestamp   |   Date    |   最近修改时间  |   default:current_timestamp, not null |   |
|   deleted     |   varchar(1)  |   Integer     |   是否删除（0：未删除  1：已删除）     |  default:"0", not null   | 逻辑删除   |
|   name    |   varchar(16) |   String  |   目录名称    |   not null    |   |
|   description |   varchar(256)    |   String  |   描述  |   null    |   |
|   catalog_one_id  |   int  |   Long   |   外键依赖一级目录    |   FK(goods_catalog_one), not null |   |

其它说明：

    PK: id
    UK: uk_name(name)
    FK: fk_catalog_one(catalog_one_id)
    index:  idx_name(name)
    engine: InnoDB
    auto_increment: 1
    charset: utf8mb4
    comment: 商品二级目录表


**5、商品基本信息表(goods)**

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   bigint      |   Long        |   ID      | PK，auto_increment   |   |
|   gmt_create  |   timestamp   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |
|   gmt_modified    |   timestamp   |   Date    |   最近修改时间  |   default:current_timestamp, not null |   |
|   deleted     |   varchar(1)  |   Integer     |   是否删除（0：未删除  1：已删除）     |  default:"0", not null   | 逻辑删除   |
|   name    |   varchar(16) |   String  |   商品名称    |   not null    |   |
|   description |   varchar(256)    |   String  |   描述  |   null    |   |
|   catalog_one_id  |   int  |   Long   |   外键依赖一级目录    |   FK(goods_catalog_one), not null |   |
|   catalog_two_id  |   int  |   Long   |   外键依赖二级目录    |   FK(goods_catalog_two), not null |   |
|   goods_info  |   text    |   String  |   商品信息(富文本)    |   not null    |   |
|   price   |   decimal     |   Double  |   价格      |   null    |   |
|   remarks     |   varchar(512)    |   String      |   备注      |   null    |   |
|   business_id    |   bigint |   Long  |   商户ID  |   FK(business_info), not null    |   |


其它说明：

    PK: id
    FK: fk_catalog_one(catalog_one_id)
    FK: fk_catalog_two(catalog_two_id)
    FK: fk_business(business_id)
    index:  idx_name(name)
    engine: InnoDB
    auto_increment: 1
    charset: utf8mb4
    comment: 商品基本信息表
    
**6、商品订单表(goods_order)**

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   bigint      |   Long        |   ID      | PK，auto_increment   |   |
|   gmt_create  |   timestamp   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |
|   gmt_modified    |   timestamp   |   Date    |   最近修改时间  |   default:current_timestamp, not null |   |
|   deleted     |   varchar(1)  |   Integer     |   是否删除（0：未删除  1：已删除）     |  default:"0", not null   | 逻辑删除   |
|   name    |   varchar(16) |   String  |   订单名称    |   null    |   |
|   description |   varchar(256)    |   String  |   描述  |   null    |   |
|   goods_id  |   bigint  |   Long   |   外键依赖商品基本信息表    |   FK(goods), not null |   |
|   user_id  |   bigint  |   Long   |   外键依赖用户基本信息表    |   FK(user), not null |   |
|   price   |   deci mal     |   Double  |   价格      |   null    |   |
|   remarks     |   varchar(512)    |   String      |   备注      |   null    |   |
|   post_country     |   varchar(24) |   String  |   国家  |   null    |       |
|   post_province    |   varchar(24) |   String  |   省份  |   null    |   |
|   post_city    |   varchar(24) |   String  |   城市  |   null    |   |
|   post_position    |   varchar(128) |   String  |   详细地址  |   null    |   |

其它说明：

    PK: id
    FK: fk_goods(goods_id)
    FK: fk_user(user_id)
    engine: InnoDB
    auto_increment: 1
    charset: utf8mb4
    comment: 商品订单表

**7、商品评论表(goods_comment)**

**8、店铺信息表(business_info)**

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   bigint      |   Long        |   ID      | PK，auto_increment   |   |
|   gmt_create  |   timestamp   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |
|   gmt_modified    |   timestamp   |   Date    |   最近修改时间  |   default:current_timestamp, not null |   |
|   deleted     |   varchar(1)  |   Integer     |   是否删除（0：未删除  1：已删除）     |  default:"0", not null   | 逻辑删除   |
|   name    |   varchar(16) |   String  |   名称    |   null    |   |
|   description |   varchar(256)    |   String  |   描述  |   null    |   |
|   opening_time  |   timestamp  |   Date   |   开店时间    |   default:current_timestamp, not null |   |
|   country     |   varchar(24) |   String  |   国家  |   null    |       |
|   province    |   varchar(24) |   String  |   省份  |   null    |   |
|   city    |   varchar(24) |   String  |   城市  |   null    |   |
|   position    |   varchar(128) |   String  |   详细地址  |   null    |   |
|   latitude    |   varchar(32) |   String  |   经度  |   null    |   |
|   longitude    |   varchar(32) |   String  |   纬度  |   null    |   |
|   avatar_url  |   varchar(256)    |   String  |   店铺头像URL |   null    |   |
|   telephone    |   varchar(11) |   String  |   电话号码  |   null    |   |
|   user_id    |   bigint |   Long  |   用户ID  |   FK(user), not null    |   |


其它说明：

    PK: id
    FK: fk_user(user_id)
    engine: InnoDB
    auto_increment: 1
    charset: utf8mb4
    comment: 商家基本信息表







