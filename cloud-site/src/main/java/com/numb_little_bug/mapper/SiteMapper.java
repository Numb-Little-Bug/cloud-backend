package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Site;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SiteMapper {
    @Select("select * from site")
    List<Site> queryAllSite();

    @Select("select * from site where id = #{id}")
    Site querySiteById(Integer id);

    @Select("select * from site where name = #{name}")
    Site querySiteByName(String name);

    @Select("insert into site (id, name) values (#{id}, #{name})")
    void addSite(Site site);

    @Select("delete from site where id = #{id}")
    void deleteSiteById(Integer id);

    @Select("update site set name = #{name} where id = #{id}")
    void updateSiteById(Site site);
}
