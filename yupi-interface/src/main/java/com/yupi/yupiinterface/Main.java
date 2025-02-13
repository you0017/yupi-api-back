package com.yupi.yupiinterface;


import com.yupi.yuapiclientsdk.client.YuApiClient;
import com.yupi.yuapiclientsdk.model.User;

public class Main {
    public static void main(String[] args) {
        String accessKey="yupi";
        String secretKey="abcd";
        YuApiClient yuApiClient = new YuApiClient(accessKey, secretKey);
        System.out.println(yuApiClient.getNameByGet("yupi"));
        System.out.println(yuApiClient.getNameByPost("yupi"));
        User user = new User();
        user.setUsername("yupi");
        System.out.println(yuApiClient.getUsernameByPost(user));
    }
}
