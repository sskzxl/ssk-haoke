package com.ssk.haoke.im.controller;

import com.ssk.haoke.im.pojo.Message;
import com.ssk.haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    /**
     *
     * @param fromId
     * @param toId
     * @param page
     * @param rows
     * @return
     */
    @GetMapping
    public List<Message> queryMessageList(@RequestParam("fromId") Long fromId,
                                          @RequestParam("toId") Long toId,
                                          @RequestParam(value = "page",
                                                  defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows",
                                                  defaultValue = "100") Integer rows){
            return this.messageService.queryMessageList(fromId,toId,page,rows);
    }
}
