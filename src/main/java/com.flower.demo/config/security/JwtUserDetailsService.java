package com.flower.demo.config.security;

import com.flower.demo.entity.UserInfo;
import com.flower.demo.service.UserInfoService;
import com.flower.demo.service.UserRoleService;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserRoleService userRoleService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("JwtUserDetailsService:" + s);
        UserInfo user = userInfoService.getUserInfoByPhone(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Integer role = user.getRoleId();
        String roleName = userRoleService.getRoleNameById(role);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        //如果是管理员添加审批组权限，
        authorityList.add(new SimpleGrantedAuthority("GROUP_" + roleName));
        authorityList.add(new SimpleGrantedAuthority("GROUPS_" + roleName));

        return new SecurityUserDetails(s, user.getUserPhone(), authorityList);
    }

}
