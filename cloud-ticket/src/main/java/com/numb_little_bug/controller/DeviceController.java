package com.numb_little_bug.controller;

import com.alibaba.fastjson.JSONObject;
import com.numb_little_bug.entity.Device;
import com.numb_little_bug.entity.Item;
import com.numb_little_bug.entity.ResultDevice;
import com.numb_little_bug.mapper.DeviceMapper;
import com.numb_little_bug.mapper.ItemMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DeviceController {
    private final DeviceMapper deviceMapper;
    private final ItemMapper ItemMapper;

    public DeviceController(DeviceMapper deviceMapper, ItemMapper ItemMapper) {
        this.deviceMapper = deviceMapper;
        this.ItemMapper = ItemMapper;
    }

    @PostMapping("/item/{id}")
    public JsonResult addItem(@RequestBody Item[] items, @PathVariable("id") Integer id){
        System.out.println(JSONObject.toJSONString(items));
        for (Item item : items) {
            item.setDeviceId(id);
            try {
                ItemMapper.addItem(item);
            } catch (Exception e) {
                System.out.println(e);
                return new JsonResult(1, null, "添加失败", "failed");
            }
        }
        return new JsonResult(0, null, "添加成功", "success");
    }

    @PostMapping("/device")
    public JsonResult addDevice(@RequestBody Device device) {
        deviceMapper.addDevice(device);
        queryAllDevice();
        Integer id = deviceMapper.queryAllDevice()[deviceMapper.queryAllDevice().length - 1].getId();
        return new JsonResult(0, id, "添加成功", "success");
    }

    @GetMapping("/device/{id}")
    public JsonResult queryDeviceById(@PathVariable("id") Integer id) {
        return new JsonResult(0, deviceMapper.queryDeviceById(id), "查询成功", "success");
    }

    @GetMapping("/device")
    public JsonResult queryAllDevice() {
        return new JsonResult(0, deviceMapper.queryAllDevice(), "查询成功", "success");
    }
}
