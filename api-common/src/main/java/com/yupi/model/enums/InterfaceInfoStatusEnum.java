package com.yupi.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum InterfaceInfoStatusEnum {
    OFFLINE("关闭", 0),
    ONLINE("开启", 1);

    private final String text;

    private final int value;

    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }
}
