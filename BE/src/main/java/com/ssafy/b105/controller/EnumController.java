package com.ssafy.b105.controller;

import com.ssafy.b105.constant.EnumMapper;
import com.ssafy.b105.constant.EnumValue;
import com.ssafy.b105.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EnumController {

    private final EnumMapper enumMapper;

    @GetMapping("/enum")
    public Map<String, Object> getEnum() {
        Map<String, Object> enums = new LinkedHashMap<>();
        Class userRole = UserRole.class;
        enums.put("userRole", userRole.getEnumConstants());

        return enums;
    }

    @GetMapping("/mapper")
    public Map<String, List<EnumValue>> getMapper() {

        return enumMapper.getAll();
    }
}
