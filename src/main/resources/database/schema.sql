
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


-- 创建用户基本信息表
create table if not exists `user` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `nick_name` varchar(16) null comment '用户昵称',
    `real_name` varchar(16) null comment '真实姓名',
    `telephone` varchar(11) not null    comment '电话号码',
    `password`  varchar(64) null comment '密码MD5值',
    `gender`    varchar(1)  not null default "0" comment '性别（0：保密 1：男 2：女）',
    `birthday` date null    comment '出生日期',
    `country`  varchar(24)  null comment '国家',
    `province`  varchar(24)  null comment '省份',
    `city`  varchar(24)  null comment '城市',
    `position` varchar(128) null comment '详细地址',
    `latitude` varchar(32) null comment '经度',
    `longitude` varchar(32) null comment '纬度',
    `avatar_url` varchar(256) null comment '用户头像URL',
    `type` varchar(1) not null default "0" comment '用户类型（0：普通用户 1：商家 2：管理员 3：超级管理员）',
    `description` varchar(256) null comment '描述',
    primary key (`id`),
    unique key `uk_telephone`(`telephone`),
    index `idx_position`(`country`,`province`,`city`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '用户基本信息表';


-- 用户-标签表
create table if not exists `user_tag` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    `catalog_one_id` int not null comment '一级目录ID，FK【goods_catalog_one】',
    `catalog_two_id` int comment '二级目录ID，FK【goods_catalog_two】',
    primary key (`id`),
    key `idx_user` (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '用户-标签';


-- 创建商品一级目录表
create table if not exists `goods_catalog_one` (
    `id` int not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `name` varchar(16) null comment '目录名称',
    `description` varchar(256) null comment '描述',
    primary key (`id`),
    unique key `uk_name`(`name`),
    index `idx_name`(`name`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '商品一级目录表';

-- 创建商品二级目录表
create table if not exists `goods_catalog_two` (
    `id` int not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `name` varchar(16) null comment '目录名称',
    `description` varchar(256) null comment '描述',
    `catalog_one_id` int not null comment '一级目录ID，FK(goods_catalog_one)',
    `season` int null default '0' comment '时令，0代表尚未分类',
    primary key (`id`),
    unique key `uk_name`(`name`),
    index `idx_name`(`name`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '商品一级目录表';

-- 商品基本信息表
create table if not exists `goods` (
    `id` int not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `name` varchar(16) null comment '商品名称',
    `description` varchar(256) null comment '描述',
    `catalog_one_id` int not null comment '一级目录ID，FK(goods_catalog_one)',
    `catalog_two_id` int not null comment '二级目录ID，FK(goods_catalog_two)',
    `goods_info`    text  not null comment '商品信息（富文本格式）',
    `price` decimal  null comment '价格',
    `remarks` varchar(512) null comment '备注',
    `business_id` bigint not null comment '商户ID【FK(user)】',
    `cover_img` varchar(256) not null comment '封面图片',
    primary key (`id`),
    index `idx_name`(`name`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '商品基本信息表';

-- 商品订单表
create table if not exists `goods_order` (
    `id` int not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `name` varchar(16) null comment '商品名称',
    `description` varchar(256) null comment '描述',
    `goods_id` int not null comment '商品ID，FK(goods)',
    `user_id` int not null comment '用户ID，FK(user)',
    `price` decimal  null comment '价格',
    `remarks` varchar(512) null comment '备注',
    `post_country`  varchar(24)  null comment '国家',
    `post_province`  varchar(24)  null comment '省份',
    `post_city`  varchar(24)  null comment '城市',
    `post_position` varchar(128) null comment '详细地址',
--     `business_id` bigint not null comment '商户ID【FK:business_info】',
    `number` int not null default '1' comment '购买数量',
    primary key (`id`),
    index `idx_name`(`name`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '商品订单表';


-- 创建商户基本信息表
create table if not exists `business_info` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `name` varchar(16) null comment '商户名称',
    `opening_time` timestamp not null DEFAULT CURRENT_TIMESTAMP comment '开店时间',
    `telephone` varchar(11) not null    comment '电话号码',
    `country`  varchar(24)  null comment '国家',
    `province`  varchar(24)  null comment '省份',
    `city`  varchar(24)  null comment '城市',
    `position` varchar(128) null comment '详细地址',
    `latitude` varchar(32) null comment '经度',
    `longitude` varchar(32) null comment '纬度',
    `avatar_url` varchar(256) null comment '用户头像URL',
    `description` varchar(256) null comment '描述',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    primary key (`id`),
    unique key `uk_telephone`(`telephone`),
    index `idx_position`(`country`,`province`,`city`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '商户基本信息表';

-- 创建商品评论表
create table if not exists `goods_comment` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `img_one` varchar(256) null comment '图片1',
    `img_two` varchar(256) null comment '图片2',
    `img_three` varchar(256) null comment '图片3',
    `content` varchar(256) not null comment '评论内容',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    `goods_id` bigint not null comment '商品ID【FK(goods)】',
    `score` int not null default '0' comment '用户评分:1-5',
    primary key (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '商品评论表';


-- 创建客服消息表
create table if not exists `chat` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `chat_id` varchar(256) not null comment '会话ID【自定义】',
    `content` varchar(2048) not null comment '聊天内容',
    `sender` bigint not null comment '发送者ID【FK(user)】',
    `receiver` bigint not null comment '接收者ID【FK(user)】',
    `read` int not null default '1' comment '是否已读，0已读 1未读',
    primary key (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '客服消息表';


-- 创建购物车表
create table if not exists `shopping_cart` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    `goods_id` bigint not null comment '商品ID【FK(goods)】',
    `count` int not null default '1' comment '数量',
    primary key (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '购物车表';



------------------------------------
-- 以下为推荐系统特需表格
------------------------------------

-- 用户行为表
create table if not exists `user_behavior` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    `goods_id` bigint not null comment '商品ID【FK(goods)】',
    `behavior` int not null default '1' comment '用户行为：1点击 2加入购物车 3购买 4评价-好评 5评价-中评 6评价-差评',
    primary key (`id`),
    key `idx_user` (`user_id`),
    key `idx_goods` (`goods_id`),
    key `idx_user_goods` (`user_id`, `goods_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '用户行为表';

-- 用户-物品偏好表
create table if not exists `user_goods_preference` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    `goods_id` bigint not null comment '商品ID【FK(goods)】',
    `preference` int not null default '0' comment '偏好，越高越喜欢',
    primary key (`id`),
    key `idx_user` (`user_id`),
    key `idx_goods` (`goods_id`),
    key `idx_user_goods` (`user_id`, `goods_id`),
    unique key `uk_user_goods` (`user_id`, `goods_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '用户-物品偏好表';


-- 物品推荐排行榜表
create table if not exists `recommend_ranking_goods` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `goods_id` bigint not null comment '商品ID【FK(goods)】',
    `ranking` int not null default '0' comment '推荐次数',
    primary key (`id`),
    key `idx_goods` (`goods_id`),
    unique key `uk_goods` (`goods_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '物品推荐排行榜表';

-- 用户-物品推荐队列表
create table if not exists `recommend_queue_goods` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `user_id` bigint not null comment '用户ID【FK(user)】',
    `goods_id` bigint not null comment '商品ID【FK(goods)】',
    `recommend_type` int not null default '0' comment '推荐类型',
    primary key (`id`),
    key `idx_user` (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '用户-物品推荐队列表';

-- 推荐评估结果表
create table if not exists `recommender_evalutor` (
    `id` bigint not null auto_increment comment 'ID',
    `gmt_create`   timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间戳',
    `gmt_modified` timestamp NULL     DEFAULT CURRENT_TIMESTAMP  COMMENT '最近修改时间戳',
    `deleted`      varchar(1)         default '0'  COMMENT '是否删除：0未删除，1已删除',
    `score` decimal(10,4) null comment '评分',
    `precision` decimal(10,4) null comment '查准率',
    `recall` decimal(10,4) null comment '召回率',
    `type` int null comment '推荐类型',
    primary key (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '推荐评估结果表';






---------------------------------------
-- 分析数据
---------------------------------------

-- JDATA行为表
create table if not exists `jdata_action` (
    `id` bigint not null auto_increment comment 'ID',
    `user_id` bigint comment '用户ID',
    `sku_id` bigint comment '商品编号',
    `time` timestamp comment '行为时间',
    `model_id` int comment '点击模块编号',
    `type` int comment '行为类型',
    `cate` bigint comment '品类ID',
    `brand` bigint comment '品牌ID',
    primary key (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = 'JDATA行为表';

-- mahout 数据集
create table if not exists `data_mahout` (
    `user_id` bigint comment '用户ID',
    `item_id` bigint comment '商品编号',
    `score` bigint comment '评分'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = 'mahout 数据表';
