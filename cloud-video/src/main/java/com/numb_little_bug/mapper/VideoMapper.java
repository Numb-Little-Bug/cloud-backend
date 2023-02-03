package com.numb_little_bug.mapper;


import com.numb_little_bug.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoMapper {
    @Select("select * from video")
    List<Video> queryAllVideo();

    @Select("select * from video where id = #{id}")
    Video queryVideoById(Integer id);

    @Select("select * from video where name = #{name}")
    Video queryVideoByName(String name);

    @Select("insert into video (id, name, url, site_id) values (#{id}, #{name}, #{url}, #{site_id})")
    void addVideo(Video video);

    @Select("delete from video where id = #{id}")
    void deleteVideoById(Integer id);

    @Select("delete from video where site_id = #{siteId}")
    void deleteVideoBySiteId(Integer siteId);

    @Select("update video set name = #{name}, url = #{url}, site_id = #{site_id} where id = #{id}")
    void updateVideoById(Video video);
}
