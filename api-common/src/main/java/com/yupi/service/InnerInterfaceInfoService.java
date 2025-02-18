package com.yupi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.model.entity.InterfaceInfo;

public interface InnerInterfaceInfoService extends IService<InterfaceInfo> {
    InterfaceInfo getInterfaceInfo(String url, String method);
}
