package com.yupi.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.model.entity.InterfaceInfo;
import com.yupi.model.entity.User;
import com.yupi.model.entity.UserInterfaceInfo;

/**
* @author 0.0
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
* @createDate 2025-02-14 11:11:26
*/
public interface InnerUserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo post, boolean b);

    //1.数据库查是否已经给用户分配密钥(accessKey,secretKey,布尔)
    User getInvokeUser(String accessKey, String secretKey);

    //2.从数据库中查询模拟接口是否存在(请求路径，请求方法，请求参数)
    InterfaceInfo getInterfaceInfo(String url, String method);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    boolean getInvokeCount(Long interfaceInfoId, Long userId);
}
