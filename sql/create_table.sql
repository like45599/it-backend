# 数据库初始化
# @author <a href="https://github.com/liyupi">程序员鱼皮</a>
# @from <a href="https://yupi.icu">编程导航知识星球</a>

-- 创建库
create database if not exists my_db;
create database if not exists smart_traffic_assessment;

-- 创建场景表
CREATE TABLE if not exists Scenarios (
                           scenario_id INT AUTO_INCREMENT PRIMARY KEY,
                           scenario_name VARCHAR(255) NOT NULL
);

-- 创建问题及解决方案表
CREATE TABLE if not exists Issues_Solutions (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  scenario_id INT,
                                  root_cause TEXT,
                                  issue_description TEXT,
                                  solution_description TEXT,
                                  FOREIGN KEY (scenario_id) REFERENCES Scenarios(scenario_id)
);

CREATE TABLE `form_data` (
                             `id` VARCHAR(36) NOT NULL, -- 假设使用UUID作为主键
                             `session_id` VARCHAR(36) NOT NULL,
                             `data` TEXT, -- 存储序列化的表单数据
                             `file_references` TEXT, -- 存储序列化的文件引用信息
                             `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP, -- 记录数据创建时间
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `file_info` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT, -- 自增主键
                             `session_id` VARCHAR(36), -- 存储会话的UUID
                             `form_data_id` VARCHAR(36), -- 关联的FormData记录的ID
                             `original_file_name` VARCHAR(255), -- 文件的原始名称
                             `file_path` VARCHAR(255), -- 文件存储的路径或URL
                             `file_type` VARCHAR(50), -- 文件类型
                             `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP, -- 记录文件信息创建时间
                             PRIMARY KEY (`id`),
                             INDEX `idx_form_data_id` (`form_data_id`), -- 可根据需要添加对form_data_id的索引
                             INDEX `idx_session_id` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 切换库
use my_db;
use smart_traffic_assessment;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';
