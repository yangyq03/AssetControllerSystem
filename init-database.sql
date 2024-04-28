create schema if not exists asset_manager_db;

use asset_manager_db;

create table users
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    username    varchar(20)             not null comment '用户名',
    password    varchar(32)             null comment '密码',
    email       varchar(128) default '' null comment '邮箱',
    role        varchar(20)             not null comment '角色',
    create_time datetime                not null comment '创建时间',
    update_time datetime                not null comment '修改时间',
    constraint username
        unique (username)
)
    comment '用户表';

create table assets
(
    asset_number        int auto_increment comment '资产编号'
        primary key,
    asset_name          varchar(100)   null comment '资产名称',
    asset_category      varchar(50)    null comment '资产分类',
    asset_status        varchar(50)    null comment '资产状态',
    asset_brand         varchar(100)   null comment '资产品牌',
    asset_model         varchar(100)   null comment '资产型号',
    asset_quantity      int            null comment '资产数目',
    asset_specification varchar(255)   null comment '资产规格',
    asset_value         decimal(18, 2) null comment '资产价值',
    purchase_date       datetime       null comment '购置日期',
    entry_date          datetime       null comment '入库日期',
    department_used     varchar(100)   null comment '使用部门'
);
