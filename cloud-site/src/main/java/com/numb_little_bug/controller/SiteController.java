package com.numb_little_bug.controller;

import com.numb_little_bug.entity.Site;
import com.numb_little_bug.mapper.SiteMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class SiteController {
    private final SiteMapper siteMapper;

    @Autowired
    public SiteController(SiteMapper siteMapper) {
        this.siteMapper = siteMapper;
    }

    @PostMapping("/site")
    public JsonResult addSite(@RequestBody Site site) {
        try{
            siteMapper.addSite(site);
            return new JsonResult(0, site, "添加成功", "success");
        } catch (Exception e) {
            return new JsonResult(500, null, "添加失败", "error");
        }
    }

    @DeleteMapping("/site/{id}")
    public JsonResult deleteSite(@PathVariable("id") Integer id) {
        try{
            siteMapper.deleteSiteById(id);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://localhost:8085/site/" + id);
            return new JsonResult(0, null, "删除成功", "success");
        } catch (Exception e) {
            return new JsonResult(500, null, "删除失败", "error");
        }
    }

    @PutMapping("/site")
    public JsonResult updateSite(@RequestBody Site site) {
        try{
            siteMapper.updateSiteById(site);
            return new JsonResult(0, site, "更新成功", "success");
        } catch(Exception e) {
            return new JsonResult(500, null, "更新失败", "error");
        }
    }

    @GetMapping("/site")
    public JsonResult queryAllSite() {
        List<Site> sites = siteMapper.queryAllSite();
        if (sites.size() > 0) {
            return new JsonResult(0, sites, "查询成功", "success");
        } else {
            return new JsonResult(500, null, "查询失败", "error");
        }
    }


    @GetMapping("/site/{id}")
    public JsonResult querySiteById(@PathVariable("id") Integer id) {
        Site site = siteMapper.querySiteById(id);
        if (site != null) {
            return new JsonResult(0, site, "查询成功", "success");
        } else {
            return new JsonResult(500, null, "查询失败", "error");
        }
    }

    @PostMapping("/video")
    public JsonResult addVideo(@RequestParam("site_id") Integer id, @RequestBody Map<String, String> map) {
        System.out.println(id);
        System.out.println(map.get("video1"));
        try{
            siteMapper.addVideo(id, map.get("video1"));
            return new JsonResult(0, null, "添加成功", "success");
        } catch (Exception e) {
            System.out.println(e);
            return new JsonResult(500, null, "添加失败", "error");
        }
    }

}
