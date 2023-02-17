package com.numb_little_bug.controller;


import com.numb_little_bug.entity.Ticket;
import com.numb_little_bug.mapper.TicketMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
public class TicketController {
    private final TicketMapper ticketMapper;

    public TicketController(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    /**
     * 根据操作票id查询单个操作票
     * @return JsonResult
     */
    @GetMapping("/ticket/{id}")
    public JsonResult queryTicketById(@PathVariable("id") Integer id) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, ticket, "查询成功", "success");
    }

    /**
     * 根据现场侧id查询操作票列表
     * @return JsonResult
     */
    @GetMapping("/ticket/siteId/{siteId}")
    public JsonResult queryTicketBySiteId(@PathVariable("siteId") Integer siteId) {
        Ticket[] tickets = ticketMapper.queryTicketBySiteId(siteId);
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    /**
     * 根据操作票名称查询操作票
     * @return JsonResult
     */
    @GetMapping("/ticket/name/{name}")
    public JsonResult queryTicketByName(@PathVariable("name") String name) {
        Ticket ticket = ticketMapper.queryTicketByName(name);
        if (ticket == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, ticket, "查询成功", "success");
    }

    /**
     * 查询所有操作票
     * @return JsonResult
     */
    @GetMapping("/ticket")
    public JsonResult queryAllTicket() {
        Ticket[] tickets = ticketMapper.queryAllTicket();
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    /**
     * 根据唱票人姓名查询操作票
     * @return JsonResult
     */
    @GetMapping("/ticket/teller/{teller}")
    public JsonResult queryTicketByType(@PathVariable("teller") Integer teller) {
        Ticket[] tickets = ticketMapper.queryTicketByTeller(teller);
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    /**
     * 根据操作人ID查询操作票
     * @return JsonResult
     */
    @GetMapping("/ticket/operator/{operator}")
    public JsonResult queryTicketByOperator(@PathVariable("operator") Integer operator) {
        Ticket[] tickets = ticketMapper.queryTicketByOperator(operator);
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    /**
     * 根据操作票发布人姓名查询操作票
     * @return JsonResult
     */
    @GetMapping("/ticket/publisher/{publisher}")
    public JsonResult queryTicketByPublisher(@PathVariable("publisher") Integer publisher) {
        Ticket[] tickets = ticketMapper.queryTicketByPublisher(publisher);
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    /**
     * 根据操作票状态查询操作票
     * 0：已发布, 未执行
     * 1：正在执行
     * 2：已完成
     * 3：已取消
     *
     * @return JsonResult
     */
    @GetMapping("/ticket/status/{status}")
    public JsonResult queryTicketByStatus(@PathVariable("status") String status) {
        Ticket[] tickets = ticketMapper.queryTicketByStatus(status);
        if (tickets == null) {
            return new JsonResult(404, null, "查询结果为空", "failed");
        }
        return new JsonResult(0, tickets, "查询成功", "success");
    }

    /**
     * 添加操作票
     * @param ticket 操作票
     * @return JsonResult
     */
    @PostMapping("/ticket")
    public JsonResult addTicket(@RequestBody Ticket ticket) {
        System.out.println(ticket);
        //查询是否存在同一时间同一名称的操作票
        System.out.println("name:" +ticket.getName());
        String ticketName = ticket.getName();
        //System.out.println('n' + ticketName + 'n' + ticket.getStartTime().toString() + 'n' + ticket.getEndTime().toString() + 'n');
        Ticket ticket1 = ticketMapper.queryTicketByName(ticketName);
        if(ticket1 != null) {
            return new JsonResult(500, null, "添加失败，已存在同一时间同一名称的操作票", "failed");
        }
        try {
            ticketMapper.addTicket(ticket);
            // 从数据库返回这一次操作的id
            Integer id = ticketMapper.queryTicketByName(ticketName).getId();
            return new JsonResult(0, id, "添加成功", "success");
        } catch (Exception e) {
            System.out.println(e);
            return new JsonResult(500, null, "添加失败", "failed");
        }
    }

    /**
     * 更新操作票
     * @param ticket 操作票
     * @return JsonResult
     */
    @PutMapping("/ticket")
    public JsonResult updateTicket(@RequestBody Ticket ticket) {
        try {
            ticketMapper.updateTicket(ticket);
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id删除操作票
     * @return JsonResult
     */
    @DeleteMapping("/ticket/{id}")
    public JsonResult deleteTicket(@PathVariable("id") Integer id) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "删除失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.deleteTicket(id);
        } catch (Exception e) {
            System.out.println(e);
            return new JsonResult(500, null, "删除失败", "failed");
        }
        return new JsonResult(0, null, "删除成功", "success");
    }

    /**
     * 根据操作票id更新操作票状态
     * @param map 请求体的键值对；键为status，值为操作票状态
     * @return JsonResult
     */
    @PutMapping("/ticket/status/{id}")
    public JsonResult toggleTicketStatus(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketStatus(map.get("status"), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票名称
     * @param map 请求体的键值对；键为name，值为操作票名称
     * @return JsonResult
     */
    @PutMapping("/ticket/name/{id}")
    public JsonResult updateTicketName(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketName(map.get("name"), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票唱票人
     * @param map 请求体的键值对；键为teller，值为唱票人姓名
     * @return JsonResult
     */
    @PutMapping("/ticket/teller/{id}")
    public JsonResult updateTicketTeller(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketTeller(parseInt(map.get("tellerId")), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票操作人
     * @param map 请求体的键值对；键为operator，值为操作人姓名
     * @return JsonResult
     */
    @PutMapping("/ticket/operator/{id}")
    public JsonResult updateTicketOperator(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketOperator(parseInt(map.get("operatorId")), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票操作注意事项
     * @param map 请求体的键值对；键为notice，值为注意事项内容
     * @return JsonResult
     */
    @PutMapping("/ticket/notice/{id}")
    public JsonResult updateTicketNotice(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketNotice(map.get("notice"), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票操作备注
     * @param map 请求体的键值对；键为remark，值为备注内容
     * @return JsonResult
     */
    @PutMapping("/ticket/remark/{id}")
    public JsonResult updateTicketRemark(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketRemark(map.get("remark"), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票对应的现场侧
     * @param map 请求体的键值对；键为siteId，值为现场侧具体id
     * @return JsonResult
     */
    @PutMapping("/ticket/siteId/{id}")
    public JsonResult updateTicketSiteId(@PathVariable("id") Integer id, @RequestBody Map<String,Integer> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketSiteId(map.get("siteId"), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

    /**
     * 根据操作票id更新操作票的操作时间
     * @param map 请求体的键值对；第一个键为startTime，值为操作开始时间；第二个键为endTime，值为操作结束时间
     * @return JsonResult
     */
    @PutMapping("/ticket/time/{id}")
    public JsonResult updateTicketTime(@PathVariable("id") Integer id, @RequestBody Map<String,String> map) {
        Ticket ticket = ticketMapper.queryTicketById(id);
        if (ticket == null) {
            return new JsonResult(500, null, "更新失败，未查询到相关结果", "failed");
        }
        try {
            ticketMapper.updateTicketTime(map.get("startTime"), map.get("endTime"), ticket.getId());
        } catch (Exception e) {
            return new JsonResult(500, null, "更新失败", "failed");
        }
        return new JsonResult(0, null, "更新成功", "success");
    }

}
