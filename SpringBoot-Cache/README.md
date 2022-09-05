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

## 可能会遇到的问题
报错信息1
```
java.sql.SQLException: null,  message from server: "Host '172.27.0.1' is not allowed to connect to this MySQL server"
```
问题原因：

mysql中的root用户不允许远程登陆，可以通过以下命令看到：
```roomsql
use mysql;
select host, user from user;
```
解决方案：

将root用户设置为所有ip都可连接。
```roomsql
update user set host = '%' where user = 'root';
```

重启docker解决问题。

