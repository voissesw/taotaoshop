package com.voissesw.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by hasee on 2017/11/9.
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
