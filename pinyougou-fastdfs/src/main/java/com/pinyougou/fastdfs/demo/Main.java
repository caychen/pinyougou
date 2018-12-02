package com.pinyougou.fastdfs.demo;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.fastdfs.demo.Main
 * Date:         2018/12/2
 * Desc:
 */

public class Main {

    public static void main(String[] args) throws IOException, MyException {
        //1、初始化全局配置。加载一个配置文件。
        ClientGlobal.init("G:\\Code\\Maven\\pinyougou\\pinyougou-fastdfs\\src\\main\\resources\\fastdfs_client.conf");

        //2、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();

        //3、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();

        //4、声明一个StorageServer对象，null。
        StorageServer storageServer = null;

        //5、获取存取服务器的客户端对象
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);

        //6、直接调用StorageClient对象方法上传文件
        String[] strings = storageClient.upload_file("G:\\Code\\Maven\\pinyougou\\pinyougou-fastdfs\\src\\main\\resources\\b3.png", "png", null);

        //7、显示上传文件的结果:file_id
        System.out.println("/" + strings[0] + "/" + strings[1]);
    }
}
