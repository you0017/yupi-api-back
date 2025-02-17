package com.yupi.gateway.config;


import com.yupi.yuapiclientsdk.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private static final List<String> IP_WHITE_LIST = List.of("127.0.0.1");
    private static final long FIVE_MINUTE = 60 * 5L;

    /**
     * 全局过滤
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.请求日志
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory  = response.bufferFactory();
        log.info("请求唯一标识" + request.getId());
        log.info("请求路径：" + request.getPath());
        log.info("请求方法：" + request.getMethod());
        log.info("请求参数：" + request.getQueryParams());
        String sourceAddress = request.getLocalAddress().getHostName();
        log.info("请求来源地址：" + sourceAddress);
        log.info("请求来源地址：" + request.getRemoteAddress());
        //2.访问控制 白名单
        if (IP_WHITE_LIST.contains(sourceAddress)) {
            log.error("非法请求");
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        //3.用户鉴权 ak/sk 是否合格
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        //TODO 实际情况应该是去数据库查是否已分配给用户
        if ("yupi".equals(accessKey)) {
            return handleNoAuth(response);
        }
        if (nonce != null && Long.parseLong(nonce) > 10000L) {
            return handleNoAuth(response);
        }
        long currentTime = System.currentTimeMillis() / 1000;
        if (timestamp != null && Math.abs(currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTE) {
            return handleNoAuth(response);
        }
        String serverSign = SignUtil.getSign(body, "abcd");
        if (serverSign.equals(sign)){
            return handleNoAuth(response);
        }
        //4.请求接口是否存在
        //todo 从数据库中查询模拟接口是否存在，以及请求方法是否匹配（还可以校验请求参数


        //对返回值做处理
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        //7.响应日志
                        log.info("请求响应：" + response.getStatusCode());

                        //8.调用成功，接口调用次数加一
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String s = new String(content, Charset.forName("UTF-8"));
                        //TODO，s就是response的值，想修改、查看就随意而为了
                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();

                        log.info("响应结果：" + s);

                        //9.调用失败，返回一个规范的错误码
                        if (!response.getStatusCode().equals(HttpStatus.OK)){
                            handleInvokeError(response);
                        }



                        return bufferFactory.wrap(uppedContent);
                    }));
                }else{
                    //8.调用失败，返回一个规范的错误码
                    return handleInvokeError(response);
                }
            }
        };

        //5.请求转发，调用模拟接口
        return chain.filter(exchange.mutate().response(decoratedResponse).build());

        //默认不对返回值处理
        //return chain.filter(exchange); //如果在这后面.then(Mono.fromRunnable(()->{}))可以对返回值处理
    }

    /**
     * 优先级
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    public Mono<Void> handleInvokeError(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}