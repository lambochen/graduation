
--  创建数据库
create database graduation;

-- 使用数据库
use graduation;

-- 创建测试表
create table if not exists `test`(
    `id` int not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1删除',
    `name` varchar(32) not null comment '名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8mb4 COMMENT = '测试表';





