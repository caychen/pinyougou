package com.pinyougou.shop.service;

import com.google.common.collect.Lists;
import com.pinyougou.enums.StatusEnum;
import com.pinyougou.entity.TbSeller;
import com.pinyougou.sellergoods.service.ISellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.shop.service.UserDetailsServiceImpl
 * Date:         2018/10/28
 * Desc:         认证类
 */
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private ISellerService sellerService;

    public void setSellerService(ISellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("开始认证: [{}]", username);

        //构建角色列表
        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_SELLER"));

        TbSeller tbSeller = sellerService.findOne(username);
        log.info("查询到商家数据：[{}]", tbSeller);
        if (tbSeller != null) {
            if (StatusEnum.SUCCESS.getCode().equals(tbSeller.getStatus())) {
                return new User(username, tbSeller.getPassword(), grantedAuthorityList);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
