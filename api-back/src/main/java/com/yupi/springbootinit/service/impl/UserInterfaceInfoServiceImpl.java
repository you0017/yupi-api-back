package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 0.0
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service实现
* @createDate 2025-02-14 11:11:26
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(userInterfaceInfo.getUserId() == null || userInterfaceInfo.getUserId() <= 0 || userInterfaceInfo.getInterfaceInfoId() == null || userInterfaceInfo.getInterfaceInfoId() <= 0, ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (userInterfaceInfo.getLeftNum() == null || userInterfaceInfo.getLeftNum() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return this.update(null,new LambdaUpdateWrapper<UserInterfaceInfo>()
                .eq(UserInterfaceInfo::getInterfaceInfoId,interfaceInfoId)
                .eq(UserInterfaceInfo::getUserId,userId)
                .gt(UserInterfaceInfo::getLeftNum,0)
                .setSql("leftNum = leftNum - 1,totalNum = totalNum + 1"));
    }
}




