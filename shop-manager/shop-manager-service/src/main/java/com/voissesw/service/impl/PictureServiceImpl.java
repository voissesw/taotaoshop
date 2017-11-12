package com.voissesw.service.impl;

import com.voissesw.common.ftp.FtpUtil;
import com.voissesw.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hasee on 2017/11/9.
 */
@Service
public class PictureServiceImpl implements PictureService {

    //    FTP.ADDRESS=ftp.voissesw.com
//    FTP.PORT=21
//    FTP.USERNAME=ftpuser
//    FTP.PASSWORD=admin
//    FTP.BASEPATH=/home/ftpuser/upload
    @Value("${FTP.ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP.PORT}")
    private Integer FTP_PORT;
    @Value("${FTP.USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP.PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP.BASEPATH}")
    private String FTP_BASEPATH;
    @Value("${IMAGE.BASE_URL}")
    private String IMAGE_BASE_URL;


    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        try {
            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf('.'));
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, imagePath, newName, uploadFile.getInputStream());
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", IMAGE_BASE_URL + imagePath + '/' + newName);
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传失败");
            return resultMap;
        }
    }
}
