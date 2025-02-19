package com.yupi.springbootinit.controller;

import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.model.vo.InterfaceInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分析
 */
@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final UserInterfaceInfoMapper userInterfaceInfoMapper;

    @GetMapping("/top/interface/invoke")
    //@AuthCheck(mustRole = "admin")
    public BaseResponse<List<Object>> getTopInvokeInterfaceInfo() {
        List<Map<String, Object>> maps = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        List<Object> interfaceInfoVOList = new ArrayList<>();
        List<Object> interfaceInfoIdList = new ArrayList<>();
        List<InterfaceInfoVO> list = new ArrayList<>();
        maps.forEach(map->{
            interfaceInfoIdList.add(map.get("interfaceInfoId").toString());
            list.add(InterfaceInfoVO.builder()
                    .value(Integer.parseInt(map.get("totalNum").toString()))
                    .name(map.get("interfaceInfoId").toString())
                    .build());
        });
        interfaceInfoVOList.add(interfaceInfoIdList);
        interfaceInfoVOList.add(list);
        return ResultUtils.success(interfaceInfoVOList);
    }
}
