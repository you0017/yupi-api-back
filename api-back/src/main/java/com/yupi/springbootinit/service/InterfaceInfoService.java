package com.yupi.springbootinit.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.model.entity.InterfaceInfo;

/**
* @author 0.0
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-02-03 19:52:48
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo post, boolean b);
}
