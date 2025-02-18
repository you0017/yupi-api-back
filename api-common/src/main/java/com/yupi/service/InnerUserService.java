package com.yupi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.model.entity.User;

public interface InnerUserService extends IService<User> {
    User getInvokeUser(String accessKey);
}
