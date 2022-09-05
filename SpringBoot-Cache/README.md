## Mysql docker启动前的配置
进入主机自定义的/mysql/conf文件夹下，创建my.cnf文件
```shell
vi my.cnf
```
在vim界面中填入以下内容
```shell
[mysqld]
user=mysql
default-storage-engine=INNODB
character-set-server=utf8
character-set-client-handshake=FALSE
collation-server=utf8_unicode_ci
init_connect='SET NAMES utf8'
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
```
然后就可以在docker-compose中启动了。

## 建表
```roomsql
CREATE TABLE `sys_user` (
                            `user_id` varchar(64) NOT NULL,
                            `username` varchar(64) NOT NULL,
                            PRIMARY KEY (`user_id`),
                            KEY `user_idx1_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';
```
