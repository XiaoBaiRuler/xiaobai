package com.xiaobairuler.xiaobai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Author 小白
 * @create 2020/4/22 22:12
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public int uploadFiles(MultipartFile[] files, HttpServletRequest request, String fileDir) throws IOException {

        String realPath = request.getSession().getServletContext().getRealPath(fileDir);
        File newfile = new File(realPath);
        if (!newfile.isDirectory()) {
            newfile.mkdirs();
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                try {
                    assert fileName != null;
                    file.transferTo(new File(newfile, fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return 0;
            }
        }
        return 1;
    }
}
