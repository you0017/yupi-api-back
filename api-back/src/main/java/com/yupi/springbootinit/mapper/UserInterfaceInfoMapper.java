package com.yupi.springbootinit.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author 0.0
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Mapper
* @createDate 2025-02-14 11:11:26
* @Entity com.yupi.springbootinit.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    @Select("select interface_info_id as interfaceInfoId, sum(total_num) as totalNum from user_interface_info group by interfaceInfoId order by totalNum desc limit #{limit}")
    List<Map<String, Object>> listTopInvokeInterfaceInfo(int limit);
}




