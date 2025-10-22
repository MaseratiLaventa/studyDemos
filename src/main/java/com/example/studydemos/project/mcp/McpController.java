package com.example.studydemos.project.mcp;

import com.example.studydemos.mode.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testMcp")
public class McpController {

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) {
        System.out.println("创建用户操作.." +  user.getName());
        return "成功";
    }
}
