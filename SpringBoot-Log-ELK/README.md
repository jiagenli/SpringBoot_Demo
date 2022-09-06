### 在docker-compose脚本执行之前要执行的命令
在主机的终端中输入：
```shell
vi /tmp/reagan/elk/logstash/pipeline/logstash.conf
```
在vim页面中保存下面脚本
```shell
input {
  tcp {
    mode => "server"
    host => "0.0.0.0"
    port => 4560
    codec => json_lines
  }
}
output {
  elasticsearch {
    hosts => "es:9200"
    index => "reagan-%{type}-%{+YYYY.MM.dd}"
  }
}
```

### 启动docker
```shell
docker-compose up -d --build
```

### 安装插件
#### ik分词器
进入docker执行命令
```shell
docker exec -it elasticsearch /bin/bash
```
在线下载并安装
```shell
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.17.2/elasticsearch-analysis-ik-7.17.2.zip
```
这里要注意，es的版本非常重要，不要改动compose中的7.17.2，否则会造成ik分词器版本与es版本不一致，升降级非常麻烦。

#### logstash-codec-json_lines 插件
进入容器
```shell
docker exec -it logstash /bin/bash
```
```shell
logstash-plugin install logstash-codec-json_lines
```

### 查看是否安装成功
#### elasticsearch
http://[ip]:9200
#### kibana
http://[ip]:5601


