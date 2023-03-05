package com.numb_little_bug.controller;

import com.alibaba.fastjson.JSONObject;
import com.numb_little_bug.entity.Device;
import com.numb_little_bug.entity.Item;
import com.numb_little_bug.entity.ResultDevice;
import com.numb_little_bug.mapper.DeviceMapper;
import com.numb_little_bug.mapper.ItemMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        Device device = deviceMapper.queryDeviceById(id);
        if (device == null) {
            return new JsonResult(400, null, "设备不存在", "failed");
        }
        ResultDevice res = new ResultDevice();
        res.setId(device.getId());
        res.setType(device.getName());
        Item[] lights = ItemMapper.queryComponentByTypeAndDeviceId("light", device.getId());
        // 先按line排序，再按column排序
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights.length - i - 1; j++) {
                if (lights[j].getLine() > lights[j + 1].getLine()) {
                    Item temp = lights[j];
                    lights[j] = lights[j + 1];
                    lights[j + 1] = temp;
                } else if (lights[j].getLine() == lights[j + 1].getLine()) {
                    if (lights[j].getCol() > lights[j + 1].getCol()) {
                        Item temp = lights[j];
                        lights[j] = lights[j + 1];
                        lights[j + 1] = temp;
                    }
                }
            }
        }
        ArrayList<ArrayList<JSONObject>> lights_line = new ArrayList<>();
        for (Item light : lights) {
            JSONObject singleLight = new JSONObject();
            singleLight.put("name", light.getName());
            if (light.getCol() == 1) {
                lights_line.add(new ArrayList<JSONObject>());
                lights_line.get(lights_line.size() - 1).add(singleLight);
            }
        }
        res.setLights(lights_line);
        Item[] straps = ItemMapper.queryComponentByTypeAndDeviceId("strap", device.getId());
        // 先按line排序，再按column排序
        for (int i = 0; i < straps.length; i++) {
            for (int j = 0; j < straps.length - i - 1; j++) {
                if (straps[j].getLine() > straps[j + 1].getLine()) {
                    Item temp = straps[j];
                    straps[j] = straps[j + 1];
                    straps[j + 1] = temp;
                } else if (straps[j].getLine() == straps[j + 1].getLine()) {
                    if (straps[j].getCol() > straps[j + 1].getCol()) {
                        Item temp = straps[j];
                        straps[j] = straps[j + 1];
                        straps[j + 1] = temp;
                    }
                }
            }
        }
        System.out.println("Straps: " + Arrays.toString(straps));
        ArrayList<ArrayList<JSONObject>> straps_line = new ArrayList<>();
        for (Item strap : straps) {
            JSONObject singleStrap = new JSONObject();
            singleStrap.put("name", strap.getName());
            if (strap.getCol() == 1) {
                straps_line.add(new ArrayList<JSONObject>());
            }
            straps_line.get(straps_line.size() - 1).add(singleStrap);
        }
        res.setStraps(straps_line);
        Item[] switches = ItemMapper.queryComponentByTypeAndDeviceId("switch", device.getId());
        // 先按line排序，再按column排序
        for (int i = 0; i < switches.length; i++) {
            for (int j = 0; j < switches.length - i - 1; j++) {
                if (switches[j].getLine() > switches[j + 1].getLine()) {
                    Item temp = switches[j];
                    switches[j] = switches[j + 1];
                    switches[j + 1] = temp;
                } else if (switches[j].getLine() == switches[j + 1].getLine()) {
                    if (switches[j].getCol() > switches[j + 1].getCol()) {
                        Item temp = switches[j];
                        switches[j] = switches[j + 1];
                        switches[j + 1] = temp;
                    }
                }
            }
        }
        ArrayList<ArrayList<JSONObject>> switches_line = new ArrayList<>();
        for (Item switch1 : switches) {
            JSONObject singleSwitch = new JSONObject();
            singleSwitch.put("name", switch1.getName());
            if (switch1.getCol() == 1) {
                switches_line.add(new ArrayList<JSONObject>());
            }
            switches_line.get(switches_line.size() - 1).add(singleSwitch);
        }
        res.setSwitches(switches_line);
        System.out.println(res);
        return new JsonResult(0, res, "查询成功", "success");
    }

    @GetMapping("/device")
    public JsonResult queryAllDevice() {
        return new JsonResult(0, deviceMapper.queryAllDevice(), "查询成功", "success");
    }

    @GetMapping("/item/device/{id}")
    public JsonResult queryItemByDeviceId(@PathVariable("id") Integer id) {
        return new JsonResult(0, ItemMapper.queryItemByDeviceId(id), "查询成功", "success");
    }

    @GetMapping("/item/item/{id}")
    public JsonResult queryItemNameByDeviceId(@PathVariable("id") Integer id) {
        Item item = ItemMapper.queryItemById(id);
        return new JsonResult(0, item, "查询成功", "success");
    }
}
