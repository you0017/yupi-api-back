package com.yupi.yupiinterface.controller;

import com.yupi.yuapiclientsdk.client.YuApiClient;
import com.yupi.yuapiclientsdk.model.User;
import com.yupi.yuapiclientsdk.utils.SIgnUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/name")
@RequiredArgsConstructor
public class NameController {

    private final YuApiClient yuApiClient;
    @GetMapping
    public String getNameByGet(String name) {
        return "GET 你的名字:"+name;
    }

    @PostMapping
    public String getNameByPost(String name) {
        return "POST 你的名字:"+name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        if (accessKey == null || !accessKey.equals("yupi")){
            return "无权限";
        }
        if (nonce == null || Long.parseLong(nonce) > 100000){
            return "无权限";
        }
        if (timestamp == null || Math.abs(Long.parseLong(timestamp) - System.currentTimeMillis()) > 1000 * 60 * 5){
            return "无权限";
        }
        //TODO 实际情况是从数据库中获取secretKey
        String serverSign = SIgnUtil.getSign(body, "abcd");
        if (!sign.equals(serverSign)){
            return "无权限";
        }
        return "POST 你的名字:"+user.getUsername();
    }
}
