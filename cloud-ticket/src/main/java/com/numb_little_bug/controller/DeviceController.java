package com.numb_little_bug.controller;

import com.alibaba.fastjson.JSONObject;
import com.numb_little_bug.entity.Device;
import com.numb_little_bug.entity.ResultDevice;
import com.numb_little_bug.mapper.DeviceMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DeviceController {
    private final DeviceMapper deviceMapper;

    public DeviceController(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @PostMapping("/device")
    public JsonResult addDevice(@RequestBody Map<String, String> device) {
        Device device1 = new Device();
        device1.setType(device.get("type"));
        device1.setLights(device.get("lights"));
        device1.setStraps(device.get("straps"));
        device1.setSwitches(device.get("switches"));
        deviceMapper.addDevice(device1);
        return new JsonResult(0, null, "添加成功", "success");
    }

    @GetMapping("/device/{id}")
    public JsonResult queryDeviceById(@PathVariable("id") Integer id) {
        Device device = deviceMapper.queryDeviceById(id);
        if (device == null) {
            return new JsonResult(400, null, "设备不存在", "failed");
        }
        ResultDevice res = new ResultDevice();
        res.setId(device.getId());
        res.setType(device.getType());
        res.setLights(JSONObject.parseObject(device.getLights()));
        res.setStraps(JSONObject.parseObject(device.getStraps()));
        res.setSwitches(JSONObject.parseObject(device.getSwitches()));
        return new JsonResult(0, res, "查询成功", "success");
    }

    @GetMapping("/device")
    public JsonResult queryAllDevice() {
        Device[] devices = deviceMapper.queryAllDevice();
        ResultDevice[] res = new ResultDevice[devices.length];
        for (int i = 0; i < devices.length; i++) {
            res[i] = new ResultDevice();
            res[i].setId(devices[i].getId());
            res[i].setType(devices[i].getType());
            res[i].setLights(JSONObject.parseObject(devices[i].getLights()));
            res[i].setStraps(JSONObject.parseObject(devices[i].getStraps()));
            res[i].setSwitches(JSONObject.parseObject(devices[i].getSwitches()));
        }
        return new JsonResult(0, res, "查询成功", "success");
    }

    @GetMapping("/device/items/{id}")
    public JsonResult queryDeviceItemsById(@PathVariable("id") Integer id) {
        Device device = deviceMapper.queryDeviceById(id);
        JSONObject res = new JSONObject();
        JSONObject lights = JSONObject.parseObject(device.getLights());
        JSONObject straps = JSONObject.parseObject(device.getStraps());
        JSONObject switches = JSONObject.parseObject(device.getSwitches());
        for(Map.Entry<String, Object> entry : lights.entrySet()) {
            for(Map.Entry<String, Object> entry1 : ((JSONObject) entry.getValue()).entrySet()) {
                res.put(entry1.getKey(), entry1.getValue());
            }
        }
        for(Map.Entry<String, Object> entry : straps.entrySet()) {
            for(Map.Entry<String, Object> entry1 : ((JSONObject) entry.getValue()).entrySet()) {
                res.put(entry1.getKey(), entry1.getValue());
            }
        }
        for(Map.Entry<String, Object> entry : switches.entrySet()) {
            for(Map.Entry<String, Object> entry1 : ((JSONObject) entry.getValue()).entrySet()) {
                res.put(entry1.getKey(), entry1.getValue());
            }
        }
        return new JsonResult(0, res, "查询成功", "success");
    }
}
