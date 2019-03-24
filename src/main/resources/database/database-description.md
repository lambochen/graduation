
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

1、用户基本信息表（user）

| 列 | 数据类型 | 对应Java类型 | 列描述 | 约束/默认值 | 备注 |
| ------- | ------- | ------- | ------- | ------- | ------- |
|   id          |   BIGINT      |   Long        |   用户ID      | PK，auto_increment   |   |
|   gmt_create  |   TIMESTAMP   |   Date        |   记录创建时间  |    default:current_timestamp, not null |  |






