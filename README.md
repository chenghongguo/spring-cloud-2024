## 服务启动
## consul
- 启动命令
> ./consul agent -server -ui -bind=127.0.0.1 -client=0.0.0.0 -bootstrap-expect 1 -data-dir=/Users/hongguo_cheng/Server/consul/data

- 访问地址
> http://127.0.0.1:8500

- 失败记录
  - consul[11880]: 2024-05-12T08:37:51.095-0400 [ERROR] agent: startup error: error="refusing to rejoin cluster because server has been offline for more than the configured server_rejoin_age_max (168h0m0s) - consider wiping your data dir"
  - 解决方案：删除 consul/data/server_metadata.json 文件后重新启动服务

## zipkin
- 启动命令
> java -jar zipkin.jar

- 访问地址
> http://127.0.0.1:9411/zipkin/