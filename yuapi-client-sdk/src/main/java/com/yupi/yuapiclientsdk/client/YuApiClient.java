package com.yupi.yuapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.yupi.yuapiclientsdk.model.User;
import com.yupi.yuapiclientsdk.utils.SignUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YuApiClient {
    private static final String GATEWAY_HOST = "http://localhost:8080";
    private String accessKey;
    private String secretKey;
    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result3= HttpUtil.get(GATEWAY_HOST + "/api/name", paramMap);
        return result3;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result3= HttpUtil.post(GATEWAY_HOST + "/api/name", paramMap);
        return result3;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        //密钥不能发给服务端
        //hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(5));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hashMap.put("sign", SignUtil.getSign(body, secretKey));
        hashMap.put("Content-Type", "application/json; charset=UTF-8");
        return hashMap;
    }



    public String getUsernameByPost(User user) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        Gson gson = new Gson();
        String json = gson.toJson(user);
        //String result3= HttpUtil.post("http://localhost:8080/name/user", json);
        HttpResponse execute = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .charset("UTF-8")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        return execute.body();
    }
}
