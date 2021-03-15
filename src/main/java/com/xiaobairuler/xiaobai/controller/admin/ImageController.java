package com.xiaobairuler.xiaobai.controller.admin;

import com.xiaobairuler.xiaobai.po.Image;
import com.xiaobairuler.xiaobai.po.Music;
import com.xiaobairuler.xiaobai.service.ImageService;
import com.xiaobairuler.xiaobai.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @Author 小白
 * @create 2020/4/25 1:10
 */
@Controller
@RequestMapping("/admin")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UploadService uploadService;

    @GetMapping("/images")
    public String lists(Model model) {
        model.addAttribute("images", imageService.listImages());
        return "admin/image_admin";
    }

    @GetMapping("/image/inputs")
    public String toinput() {
        return "admin/image_input";
    }

    @PostMapping("/image/inputs")
    public String input(MultipartFile[] files, HttpServletRequest request, RedirectAttributes attributes) throws IOException {

        int flag = uploadService.uploadFiles(files, request, "/imageFile");
        for (MultipartFile file : files) {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            //request.getServerName()
            String url = request.getScheme() + "://" + "106.52.223.207"
                    + ":" + "8080" + "/image/" + file.getOriginalFilename();
            String urls = URLDecoder.decode(url, "UTF-8");
            image.setUrl(urls);
            imageService.saveImage(image);
        }
        if (flag == 1) {

            attributes.addFlashAttribute("message", "上传成功");
        } else {
            attributes.addFlashAttribute("message", "上传失败");
        }

        return "redirect:/admin/images";
    }
}
