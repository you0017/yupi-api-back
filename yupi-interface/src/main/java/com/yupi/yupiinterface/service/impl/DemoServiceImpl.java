package com.yupi.yupiinterface.service.impl;

import com.yupi.yuapiclientsdk.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", loadbalance = "roundrobin", retries = 3, timeout = 3000)//将这个类提供的方法对外发布，将访问的ip,端口，路径注册到注册中心中
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}
