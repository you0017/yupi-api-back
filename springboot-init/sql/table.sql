use yupi;
create table if not exists interface_info(
    id bigint primary key auto_increment comment '主键',
    name varchar(256) not null comment '名称',
    description varchar(256) null comment '描述',
    url varchar(256) not null comment '接口地址',
    requestHeader text null comment '请求头',
    responseHeader text null comment '响应头',
    status int default 0 not null comment '接口状态（0-关闭，1-开启）',
    method varchar(256) not null comment '请求类型',
    userId bigint not null comment '创建人',
    createTime datetime default current_timestamp not null comment '创建时间',
    updateTime datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    isDelete tinyint default 0 not null comment '是否删除'
) comment '接口信息';

INSERT INTO interface_info (
    id, name, description, url, requestHeader, responseHeader, status, method, userId, createTime, updateTime, isDelete
) VALUES
      (1, '接口A', '这是一个示例接口', 'http://example.com/api/a', '{"Content-Type": "application/json"}', '{"Content-Type": "application/json"}', 1, 'GET', 1001, NOW(), NOW(), 0),
      (2, '接口B', '这是另一个示例接口', 'http://example.com/api/b', '{"Authorization": "Bearer token123"}', '{"Content-Type": "application/json"}', 0, 'POST', 1002, NOW(), NOW(), 0),
      (3, '接口C', '这是第三个示例接口', 'http://example.com/api/c', '{"Content-Type": "application/json"}', '{"Content-Type": "application/json"}', 1, 'PUT', 1001, NOW(), NOW(), 0),
      (4, '接口D', '这是第四个示例接口', 'http://example.com/api/d', '{"Authorization": "Bearer token456"}', '{"Content-Type": "application/json"}', 1, 'DELETE', 1003, NOW(), NOW(), 0);
