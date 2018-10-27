package com.pinyougou.manager.controller;

import com.google.common.collect.Maps;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.manager.controller.LoginController
 * Date:         2018/10/27
 * Desc:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/name")
    public Map<String, Object> name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> result = Maps.newHashMap();
        result.put("loginName", name);

        return result;
    }
}
