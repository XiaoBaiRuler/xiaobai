package com.xiaobairuler.xiaobai.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author 小白
 * @create 2020/4/22 22:09
 */
public interface UploadService {

    /**
     * 上传文件
     */
    int uploadFiles(MultipartFile[] files, HttpServletRequest request, String fileDir) throws IOException;
}
