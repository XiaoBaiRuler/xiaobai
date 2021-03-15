package com.xiaobairuler.xiaobai.controller.admin;

import com.xiaobairuler.xiaobai.po.Music;
import com.xiaobairuler.xiaobai.service.MusicService;
import com.xiaobairuler.xiaobai.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * @Author 小白
 * @create 2020/4/22 22:05
 */

@Controller
@RequestMapping("/admin")
public class FileController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private UploadService uploadService;

    @PostMapping("/musics/inputs")
    public String httpUpload(MultipartFile[] files, HttpServletRequest request, RedirectAttributes attributes) throws IOException {

        int flag = uploadService.uploadFiles(files, request, "/musicFile");
        for (MultipartFile file : files) {
            Music music = new Music();
            String[] str = Objects.requireNonNull(file.getOriginalFilename()).split("-");
            music.setArtist(str[0].trim());
            music.setName(str[1].trim());
            music.setCover("/images/logo.png");
            //request.getServerName()
            String url = request.getScheme() + "://" + "106.52.223.207"
                    + ":" + "8080" + "/music/" + file.getOriginalFilename();
            System.out.println(url);
            String urls = URLDecoder.decode(url, "UTF-8");
            music.setUrl(urls);
            if (musicService.getMusicByUrl(urls) == null) {
                musicService.saveMusic(music);
            }
        }
        if (flag == 1) {

            attributes.addFlashAttribute("message", "上传成功");
        } else {
            attributes.addFlashAttribute("message", "上传失败");
        }
        return "redirect:/admin/musics";
    }

}
