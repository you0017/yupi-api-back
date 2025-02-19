package com.yupi.springbootinit.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口统计试图
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 **/
@Data
@Builder
public class InterfaceInfoVO implements Serializable {

    private Integer value;
    /**
     *
     */
    private String name;

    private static final long serialVersionUID = 1L;
}