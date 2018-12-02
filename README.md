### 在单台服务器的情况下，当Centos7的ip发生变化之后，需要注意的：

* pinyougou-common里的zookeeper.properties里的zookeeper地址需要相应进行改变

* FastDFS配置需要改变：[Centos7配置FastDFS](https://blog.csdn.net/caychen/article/details/84680519)

  * 修改 /etc/fdfs/storage.conf

    > tracker_server=具体tracker_server的ip地址

  * 重启storage：

    > /usr/bin/fdfs_storaged /etc/fdfs/storage.conf restart

  * 修改 /etc/fdfs/client.conf（用于自带的工具测试上传，如果不使用，可以忽略）

    > tracker_server=具体tracker_server的ip地址

  * 修改 /etc/fdfs/mod_fastdfs.conf 

    > tracker_server=具体tracker_server的ip地址

  * 修改 /usr/local/nginx/conf/nginx.conf

    > 将server节点下的server_name修改为tracker_server的ip地址

  * 重启nginx:

    > /usr/local/nginx/sbin/nginx -s reload
    
    
---    

本地安装fastdfs-client-java:

```
mvn install:install-file -DgroupId=org.csource -DartifactId=fastdfs-client-java -Dversion=1.27-RELEASE -Dpackaging=jar -Dfile=C:\Users\Cay\Desktop\fastdfs-client-java-1.27-RELEASE.jar
```

**在不关闭防火墙的情况下，需要打开22122和23000端口**