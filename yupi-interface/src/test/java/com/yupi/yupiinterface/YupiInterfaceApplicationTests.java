package com.yupi.yupiinterface;

import com.yupi.yuapiclientsdk.client.YuApiClient;
import com.yupi.yuapiclientsdk.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YupiInterfaceApplicationTests {

    @Resource
    private YuApiClient yuApiClient;
    @Test
    void contextLoads() {
        System.out.println(yuApiClient.getNameByGet("yupi"));
        System.out.println(yuApiClient.getNameByPost("yupi"));
        User user = new User();
        user.setUsername("yupi");
        System.out.println(yuApiClient.getUsernameByPost(user));
    }

}
