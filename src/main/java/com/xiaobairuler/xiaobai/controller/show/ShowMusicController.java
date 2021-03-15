package com.xiaobairuler.xiaobai.controller.show;

import com.xiaobairuler.xiaobai.po.Music;
import com.xiaobairuler.xiaobai.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * @Author 小白
 * @create 2020/4/22 21:23
 */
@Controller
public class ShowMusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 跳转到音乐页面
     *
     * @return
     */
    @GetMapping("/music")
    public String toMusic() {
        return "show/music";
    }

    /**
     * 获取音乐列表数据，json类型返回
     *
     * @return
     */
    @RequestMapping(value = "/musics.action", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ArrayList<Music> getMusics() throws UnsupportedEncodingException {
        ArrayList<Music> musics = musicService.getAllMusics();

        for (Music music : musics) {
            String urls = URLDecoder.decode(music.getUrl(), "gbk");
            System.out.println(urls);
            music.setUrl(urls);
        }
        return musics;
    }
}
