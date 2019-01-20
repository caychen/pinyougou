package com.pinyougou.shop.controller;

import com.pinyougou.result.JsonResult;
import com.pinyougou.util.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.shop.controller.UploadController
 * Date:         2018/12/2
 * Desc:
 */
@Slf4j
@RestController
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String fileServerUrl;

    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) {

        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fastdfs_client.conf");

            //原名
            String originalFilename = file.getOriginalFilename();
            //扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //
            String fileId = fastDFSClient.uploadFile(file.getBytes(), extName);

            //返回图片的完整地址
            return JsonResult.ok(null, fileServerUrl + fileId);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败，原因：", e);
            return JsonResult.fail("上传失败，原因：" + e.getMessage());
        }

    }
}
