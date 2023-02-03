package com.numb_little_bug.controller;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpHeaders;
import com.numb_little_bug.config.VideoConfig;
import com.numb_little_bug.entity.Video;
import com.numb_little_bug.mapper.VideoMapper;
import com.numb_little_bug.utils.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    @PostMapping("/video/{video_index}")
    public JsonResult upload(@PathVariable("video_index") Integer video_index, MultipartFile file, Integer siteId, HttpServletRequest request) {
        try {
            if (file.isEmpty()) {
                return new JsonResult(500, null, "上传失败", "failed");
            }
            String originalFilename = file.getOriginalFilename();
            // 文件后缀
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            // 根路径，在 resources/static/upload
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";
            // 新的文件名，使用uuid生成文件名
            String fileName = uuid + fileSuffix;
            File fileExist = new File(basePath);
            // 文件夹不存在，则新建
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
            // 获取文件对象
            File fileObject = new File(basePath, fileName);
            // 完成文件的上传
            file.transferTo(fileObject);
            // HTTP访问路径
            String httpPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + fileName;
            // 更新数据库
            Video video = new Video();
            video.setName(originalFilename);
            video.setUrl(httpPath);
            video.setSite_id(siteId);
            //调用provider的服务
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8082/video?site_id="+ siteId + "&video_index=" + video_index;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("video1", httpPath);
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            String result = restTemplate.postForObject(url, entity ,String.class);
            JSONObject jo = JSONObject.parseObject(result);
            if(jo.get("code").equals(0)){
                videoMapper.addVideo(video);
                return new JsonResult(0, httpPath, "上传成功", "success");
            }else{
                return new JsonResult(500, null, "上传失败", "failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(500, null, "上传失败", "failed");
    }

    @DeleteMapping("/video/{id}")
    public JsonResult deleteVideo(@PathVariable("id") Integer id) {
        try{
            videoMapper.deleteVideoById(id);
            return new JsonResult(0, null, "删除成功", "success");
        } catch (Exception e) {
            return new JsonResult(500, null, "删除失败", "error");
        }
    }

    @DeleteMapping("/site/{id}")
    public JsonResult deleteSite(@PathVariable("id") Integer id) {
        try{
            videoMapper.deleteVideoBySiteId(id);
            return new JsonResult(0, null, "删除成功", "success");
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

    @DeleteMapping("/video")
    public JsonResult deleteVideoByUrl(@RequestParam("url") String url) {
        try{
            videoMapper.deleteVideoByUrl(url);
            // 删除文件
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            File file = new File(basePath, fileName);
            if (file.exists()) {
                file.delete();
            }
            return new JsonResult(0, null, "删除成功", "success");
        } catch (Exception e) {
            return new JsonResult(500, null, "删除失败", "error");
        }
    }
}
