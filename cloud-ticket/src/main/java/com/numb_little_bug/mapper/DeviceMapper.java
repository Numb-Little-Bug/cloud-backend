package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeviceMapper {
    @Select("select * from device where id = #{id}")
    Device queryDeviceById(Integer id);

    @Select("select * from device where type = #{type}")
    Device queryDeviceByType(String type);

    //添加设备
    @Select("insert into device(type, lights, straps, switches) values(#{type}, #{lights}, #{straps}, #{switches})")
    void addDevice(Device device);

    @Select("select * from device")
    Device[] queryAllDevice();
}
