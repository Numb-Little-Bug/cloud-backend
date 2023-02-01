package com.numb_little_bug.controller;


import com.numb_little_bug.entity.Operation;
import com.numb_little_bug.mapper.OperationMapper;
import com.numb_little_bug.mapper.TicketMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperationController {
    private final OperationMapper operationMapper;

    public OperationController(OperationMapper operationMapper) {
        this.operationMapper = operationMapper;
    }

    @GetMapping("operation/{id}")
    public JsonResult queryOperationById(@PathVariable("id") Integer id) {
        return new JsonResult(0, operationMapper.queryOperationById(id), "查询成功", "success");
    }

    @GetMapping("operation/stepNumber/{stepNumber}")
    public JsonResult queryOperationByStepNumber(@PathVariable("stepNumber") Integer stepNumber) {
        return new JsonResult(0, operationMapper.queryOperationByStepNumber(stepNumber), "查询成功", "success");
    }

    @GetMapping("operation/ticket/{ticketId}")
    public JsonResult queryOperationByTicketId(@PathVariable("ticketId") Integer ticketId) {
        return new JsonResult(0, operationMapper.queryOperationByTicketId(ticketId), "查询成功", "success");
    }

    @PostMapping("operation")
    public JsonResult addOperation(@RequestBody Operation operation) {
        try {
            operationMapper.addOperation(operation);
        } catch (Exception e) {
            return new JsonResult(500, null, "添加失败", "failed");
        }
        return new JsonResult(0, null, "添加成功", "success");
    }

    @PutMapping("operation")
    public JsonResult updateOperation(@RequestBody Operation operation) {
        try {
            operationMapper.updateOperation(operation);
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    @DeleteMapping("operation/{id}")
    public JsonResult deleteOperation(@PathVariable("id") Integer id) {
        try {
            operationMapper.deleteOperation(id);
        } catch (Exception e) {
            return new JsonResult(500, null, "删除失败", "failed");
        }
        return new JsonResult(0, null, "删除成功", "success");
    }

}