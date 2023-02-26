package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeviceMapper {
    @Select("select * from device where id = #{id}")
    Device queryDeviceById(Integer id);

    @Select("select * from device where name = #{name}")
    Device queryDeviceByName(String type);

    //添加设备
    @Select("insert into device(name, siteId) values(#{name}, #{siteId})")
    void addDevice(Device device);

    @Select("select * from device")
    Device[] queryAllDevice();
}
