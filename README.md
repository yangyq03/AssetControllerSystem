# AssetControllerSystem 资产管理系统

## 使用指南

- 确保电脑拥有 java 以及 maven 环境，jdk版本最好是21以上


- 克隆仓库，或者下载源码自行解压


- 通过init-database.sql脚本创建所需要的数据库以及表


- 修改application.yml文件的spring.datasource.password属性为自己数据库的密码


- 运行```/AssetControllerSystem/Redis-x64-3.2.100/redis-server.exe```文件启动Redis


- 打开终端（cmd）


- cd 到项目根目录(```/AssetControllerSystem```)下


- 运行命令```mvn clean package```打包项目


- cd 到```/AssetControllerSystem/target```目录下


- 运行命令```java -jar ./AssetControllerSystem-0.0.1-SNAPSHOT.jar```


- 服务器启动成功，默认端口为8080

## 接口文档

[接口文档](./Doc.md)
