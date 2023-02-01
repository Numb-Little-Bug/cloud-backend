package com.numb_little_bug.controller;


import com.numb_little_bug.entity.Ticket;
import com.numb_little_bug.mapper.TicketMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    private final TicketMapper ticketMapper;

    public TicketController(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    @GetMapping("/ticket/{id}")
    public JsonResult queryTicketById(@PathVariable("id") Integer id) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, ticket, "查询成功", "success");
    }

    @GetMapping("/ticket")
    public JsonResult queryAllTicket() {
        Ticket[] tickets = ticketMapper.queryAllTicket();
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    @GetMapping("/ticket/teller/{teller}")
    public JsonResult queryTicketByType(@PathVariable("teller") String teller) {
        Ticket[] tickets = ticketMapper.queryTicketByTeller(teller);
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    @PostMapping("/ticket")
    public JsonResult addTicket(@RequestBody Ticket ticket) {
        try {
            ticketMapper.addTicket(ticket);
        } catch (Exception e) {
            return new JsonResult(500, null, "添加失败", "failed");
        }
        return new JsonResult(0, null, "添加成功", "success");
    }

    @PutMapping("/ticket")
    public JsonResult updateTicket(@RequestBody Ticket ticket) {
        try {
            ticketMapper.updateTicket(ticket);
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    @DeleteMapping("/ticket/{id}")
    public JsonResult deleteTicket(@PathVariable("id") Integer id) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "删除失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.deleteTicket(id);
        } catch (Exception e) {
            return new JsonResult(500, null, "删除失败", "failed");
        }
        return new JsonResult(0, null, "删除成功", "success");
    }
}
