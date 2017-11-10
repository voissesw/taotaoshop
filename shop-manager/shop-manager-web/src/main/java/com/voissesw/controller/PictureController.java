package com.voissesw.controller;

import com.voissesw.common.json.JsonUtils;
import com.voissesw.pojo.TbItem;
import com.voissesw.service.ItemService;
import com.voissesw.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String PictureUpload(MultipartFile uploadFile) {
        Map map = pictureService.uploadPicture(uploadFile);
        return JsonUtils.objectToJson(map);
    }
}
