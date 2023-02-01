package com.numb_little_bug.controller;

import com.numb_little_bug.config.VideoConfig;
import com.numb_little_bug.entity.Video;
import com.numb_little_bug.mapper.VideoMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class VideoController {
    private final VideoMapper videoMapper;

    @Autowired
    public VideoController(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @GetMapping("/video")
    public JsonResult queryAllVideo() {
        List<Video> videos = videoMapper.queryAllVideo();
        if (videos.size() > 0) {
            return new JsonResult(200, videos, "查询成功", "success");
        } else {
            return new JsonResult(500, null, "查询失败", "error");
        }
    }

    @GetMapping("/video/{id}")
    public JsonResult queryVideoById(@PathVariable("id") Integer id) {
        Video video = videoMapper.queryVideoById(id);
        if (video != null) {
            return new JsonResult(200, video, "查询成功", "success");
        } else {
            return new JsonResult(500, null, "查询失败", "error");
        }
    }

    //上传视频文件
    @PostMapping("/upload")
    public JsonResult upload(MultipartFile uploadFile, HttpServletRequest request) {
        /*
        定义文件的存储路径,如下，是在linux和mac上定义的文件路径
        /private/var/folders/8x/4zvnbqmj1w33cqmzrpygzbth0000gn/T/tomcat-docbase.5206733816001100271.8080/uploadFile
        */
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
        try {
            String filename = uploadFile.getOriginalFilename();
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            uploadFile.transferTo(fileServer);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + "/uploadFile/" + filename;
            //将文件信息保存到数据库
            Video video = new Video();
            video.setName(filename);
            video.setUrl(filePath);
            videoMapper.addVideo(video);
            return new JsonResult(200, filePath, "上传成功", "success");
            //3，返回可供访问的网络路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonResult(500, null, "上传失败", "error");
    }

    @DeleteMapping("/video/{id}")
    public JsonResult deleteVideo(@PathVariable("id") Integer id) {
        try{
            videoMapper.deleteVideoById(id);
            return new JsonResult(200, null, "删除成功", "success");
        } catch (Exception e) {
            return new JsonResult(500, null, "删除失败", "error");
        }
    }

    @PutMapping("/video")
    public JsonResult updateVideo(@RequestBody Video video) {
        try{
            videoMapper.updateVideoById(video);
            return new JsonResult(200, video, "更新成功", "success");
        } catch(Exception e) {
            return new JsonResult(500, null, "更新失败", "error");
        }
    }
}
